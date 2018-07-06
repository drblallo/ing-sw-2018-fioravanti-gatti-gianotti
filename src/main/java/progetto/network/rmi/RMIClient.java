package progetto.network.rmi;

import progetto.Settings;
import progetto.network.IEnforce;
import progetto.network.INetworkClient;
import progetto.network.IRoomRequest;
import progetto.network.NetworkSettings;
import progetto.utils.Callback;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The rmi implementation of INetworkClient.
 *
 */
public final class RMIClient implements INetworkClient, Runnable{
	private static final Logger LOGGER = Logger.getLogger(RMIClient.class.getName());
	private IRemoteServerSession session;
	private boolean isAlive = true;
	private Callback<String> messageCallback;
	private Queue<IRoomRequest> pendingRequests = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<IEnforce> enforces = new ConcurrentLinkedQueue<>();
	private final Timer timer = new Timer();

	/**
	 * Creates the client from the provided ip
	 * @param ip the ip where the server is located.
	 */
	public RMIClient(String ip, int port) {
		try {
			LOGGER.log(Level.FINE, "creating module");
			System.setProperty("java.rmi.server.hostname", Settings.getSettings().getMyIP());
			Registry registry = LocateRegistry.getRegistry(ip, port);

			LOGGER.log(Level.FINE, "fetched registry on ip: {0}", ip);
			IRemoteLogger stub = (IRemoteLogger) registry.lookup("test");
			LOGGER.log(Level.FINE, "found stub");

			RMIRemoteClientSession local = new RMIRemoteClientSession();
			local.getConnectionLostCallback().addObserver(ogg -> teardown());
			local.getEnforceCallback().addObserver(enforces::offer);
			messageCallback = local.getMessageCallback();

			LOGGER.log(Level.FINE, "Trying to login in {0}", stub);

			session = stub.login(local);
			LOGGER.log(Level.FINE, "login success");
			new Thread(this).start();

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "FAILED TO CREATE RMI NETWORK CLIENT: {0}", e.getMessage());
			teardown();
		}
		LOGGER.log(Level.FINE, "created connection");
	}

	/**
	 * stats the ping timer
	 */
	private void startTimer()
	{
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run()
			{
				pingOtherSide();
			}
		},
				NetworkSettings.DEFAULT_TIME_TO_LIVE,
				NetworkSettings.DEFAULT_TIME_TO_LIVE);
	}

	/**
	 * tries to reset other side time to live
	 */
	private void pingOtherSide()
	{
		try
		{
			LOGGER.log(Level.FINE, "ping");
			session.ping();
		}
		catch (RemoteException e)
		{
			LOGGER.log(Level.WARNING, "received exception {0}", e.getMessage());
			teardown();
		}
	}

	/**
	 * tears down the connection
	 */
	private void teardown() {
		LOGGER.log(Level.FINE, "tearing down ");
		isAlive = false;
		timer.purge();
		timer.cancel();

		synchronized (this)
		{
			notifyAll();
		}
	}

	/**
	 * closes the connection and tears down all resources
	 * @param signalGoodBye true the connection must be closed gracefully.
	 */
	public void disconnect(boolean signalGoodBye) {
		if (signalGoodBye) {

			new Thread(() -> {
				try
				{
					LOGGER.log(Level.FINE, "signaling goodbye ");
					session.sayGoodBye();
				}
				catch (Exception e)
				{
					LOGGER.log(Level.SEVERE, "Failed to send message: {0}", e.getMessage());
				}

			}).start();
		}
		teardown();
	}

	/**
	 *
	 * @return true if the connection is still open
	 */
	public boolean isRunning() {
		return isAlive;
	}

	/**
	 *
	 * @return the callback that is called every time a message is received
	 */
	public Callback<String> getMessageCallback() {
		return messageCallback;
	}

	@Override
	public ConcurrentLinkedQueue<IEnforce> getEnforceList() {
		return enforces;
	}

	/**
	 * appends the request to the queue of requests to be sent
	 * @param request request to be sent
	 */
	public void sendRequest(final IRoomRequest request) {
		if (request == null)
			return;

		pendingRequests.offer(request);
		synchronized (this)
		{
			notifyAll();
		}
	}

	/**
	 * sends the first pending request. If there is no pending request then the thread will sleep until
	 * a new request is added
	 */
	private void sendFirstPending()
	{
		while (pendingRequests.peek() == null)
		{
			if (!isRunning())
				return;

			try
			{
				synchronized (this)
				{
					wait();
				}
			}
			catch (InterruptedException e)
			{
				LOGGER.log(Level.WARNING, "some how I woke up: {0}", e.getMessage());
				Thread.currentThread().interrupt();
			}
		}


		try {
			LOGGER.log(Level.FINE, "sending request ");
			session.sendRequest(pendingRequests.poll());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to send message: {0}", e.getMessage());
			teardown();
		}
	}

	/**
	 * while the connection is open it will keep trying to send request
	 */
	public void run()
	{
		Thread.currentThread().setName("RMI Thread");
		LOGGER.log(Level.FINE, "Starting rmi thread");
		startTimer();
		while (isRunning())
			sendFirstPending();
	}
}
