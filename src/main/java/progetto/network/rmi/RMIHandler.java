package progetto.network.rmi;

import progetto.network.IEnforce;
import progetto.network.INetworkHandler;
import progetto.network.IRoomRequest;
import progetto.network.NetworkSettings;
import progetto.utils.Callback;

import java.rmi.RemoteException;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * rmi implementation of INetworkHandler
 */
public final class RMIHandler implements INetworkHandler, Runnable {
	private static final Logger LOGGER = Logger.getLogger(RMIHandler.class.getName());
	private final IRemoteClientSession session;
	private boolean isDead = false;
	private Callback<IRoomRequest> requestCallback;
	private Queue<IEnforce> pendingEnforce = new ConcurrentLinkedQueue<>();
	private final Timer timer = new Timer();

	/**
	 * creates a new instance
	 * @param s the remote client session that is connected to this object
	 * @param local the remote server session that is connected to this object
	 */
	public RMIHandler(IRemoteClientSession s, RMIRemoteServerSession local) {
		LOGGER.log(Level.FINE, "starting client handler");
		session = s;
		requestCallback = local.getRequestCallback();
		local.getConnectionClosedCallback().addObserver (ogg -> {
				LOGGER.log(Level.FINE, "connection got closed, tearing it down");
				tearDown();
			}
		);
		new Thread(this).start();

	}

	private void startTimer()
	{
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				sendPing();
			}
		}, NetworkSettings.DEFAULT_TIME_TO_LIVE, NetworkSettings.DEFAULT_TIME_TO_LIVE);
	}

	private void sendPing()
	{
		try {
			LOGGER.log(Level.FINE, "ping");
			session.ping();
		} catch (RemoteException e) {
			LOGGER.log(Level.SEVERE, "Failed to ping {0}", e.getMessage());
			tearDown();
		}
	}

	/**
	 *
	 * @return true if the connection is still open
	 */
	public boolean isRunning() {
		return !isDead;
	}

	/**
	 * shuts down the connection, tears down all resources
	 * @param disconectGracefully true if the connection must be closed gracefully
	 */
	public synchronized void disconnect(boolean disconectGracefully) {
		LOGGER.log(Level.FINE,"disconnection ");
		isDead = true;
		if (disconectGracefully)
		{
			new Thread(() -> {
				try
				{
					LOGGER.log(Level.FINE, "sending good bye");
					session.sayGoodBye();
				}
				catch (RemoteException e) { LOGGER.log(Level.SEVERE, "Failed to disconnect: {0}", e.getMessage()); }

			}).start();
		}
		tearDown();
	}

	/**
	 * Tries to send a message
	 * @param message the string to be sent
	 */
	public synchronized void sendMessage(final String message) {
		new Thread(() -> {
			try
			{
				LOGGER.log(Level.FINE, "Sending message");
				session.sendMessage(message);
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Failed to send message: {0}", e.getMessage());
				tearDown();
			}

		}).start();

	}

	/**
	 * appends a enforce to the queue of enforces that must be sent
	 * @param enforce the enforce to be sent
	 */
	public void sendEnforce(final IEnforce enforce)
	{
		if (enforce == null)
			return;

		pendingEnforce.offer(enforce);
		synchronized (this)
		{
			notifyAll();
		}
	}

	/**
	 * mark this connection as dead
	 */
	private void tearDown() {
		isDead = true;
		timer.cancel();
		timer.purge();
	}

	/**
	 *
	 * @return the request callback that is called every time a request is received
	 */
	public Callback<IRoomRequest> getRequestCallback() {
		return requestCallback;
	}

	/**
	 * tries to send the first pending enforces
	 * if the queue of enforces is null the thread will go to sleep utils one is added to the queue.
	 */
	private void sendFirstPending()
	{
		LOGGER.log(Level.FINEST, "evalutaing enforces");
		while (pendingEnforce.peek() == null)
		{
			synchronized (this)
			{
				try
				{
					wait();
				}
				catch (InterruptedException e)
				{
					Thread.currentThread().interrupt();
				}
			}
		}

		try
		{
			LOGGER.log(Level.FINE, "sending enforce");
			session.sendEnforce(pendingEnforce.poll());
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE,"encountered error while sending enforce {0}", e.getMessage());
			e.printStackTrace();
			tearDown();
		}
	}

	/**
	 * while the connection is open it will keep sending all enforces
	 */
	public void run()
	{
		Thread.currentThread().setName(getClass().getName()+" Thread");
		LOGGER.log(Level.FINE, "Started rmi handler thread");
		startTimer();
		while (isRunning())
			sendFirstPending();

		LOGGER.fine("Natural end of run ");
	}
}
