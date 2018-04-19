package progetto.network.rmi;

import progetto.network.IEnforce;
import progetto.network.AbstractRoomRequest;
import progetto.network.INetworkClient;
import progetto.utils.Callback;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RMIClient implements INetworkClient, Runnable{
	private static final Logger LOGGER = Logger.getLogger(RMIClient.class.getName());
	private IRemoteSession session;
	private boolean isAlive = true;
	private Callback<IEnforce> enforceCallback;
	private Callback<String> messageCallback;
	private Queue<AbstractRoomRequest> pendingRequests = new ConcurrentLinkedQueue<>();

	public RMIClient(String ip) {
		try {
			LOGGER.log(Level.FINE, "creating module");
			Registry registry = LocateRegistry.getRegistry(ip, RMIServer.RMI_PORT);

			IRemoteLogger stub = (IRemoteLogger) registry.lookup("test");
			RMIRemoteClientSession local = new RMIRemoteClientSession();
			local.getConnectionLostCallback().addObserver(ogg -> teardown());
			enforceCallback = local.getEnforceCallback();
			messageCallback = local.getMessageCallback();

			session = stub.login(local);
			new Thread(this).start();

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "FAILED TO CREATE RMI NETWORK MODULE: {0}", e.getMessage());
			teardown();
		}
	}

	private void teardown() {
		LOGGER.log(Level.FINE, "tearing down ");
		isAlive = false;

		synchronized (this)
		{
			notifyAll();
		}
	}

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

	public boolean isRunning() {
		return isAlive;
	}

	public Callback<String> getMessageCallback() {
		return messageCallback;
	}

	public Callback<IEnforce> getEnforceCallback() {
		return enforceCallback;
	}

	public void sendRequest(final AbstractRoomRequest request) {
		if (request == null)
			return;

		pendingRequests.offer(request);
		synchronized (this)
		{
			notifyAll();
		}
	}

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

	public void run()
	{
		Thread.currentThread().setName(getClass().getName()+" Thread");
		while (isRunning())
			sendFirstPending();
	}
}
