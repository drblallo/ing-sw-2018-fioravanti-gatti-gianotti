package progetto.network.rmi;

import progetto.network.IEnforce;
import progetto.network.AbstractRoomRequest;
import progetto.network.INetworkClientHandler;
import progetto.utils.Callback;

import java.rmi.RemoteException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RMIClientHandler implements INetworkClientHandler, Runnable {
	private static final Logger LOGGER = Logger.getLogger(RMIClientHandler.class.getName());
	private final IRemoteClientSession session;
	private boolean isDead = false;
	private Callback<AbstractRoomRequest> requestCallback;
	private Queue<IEnforce> pendingEnforce = new ConcurrentLinkedQueue<>();

	public RMIClientHandler(IRemoteClientSession s, RMIRemoteSession local) {
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

	public boolean isRunning() {
		return !isDead;
	}

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

	private void tearDown() {
		isDead = true;
	}


	public Callback<AbstractRoomRequest> getRequestCallback() {
		return requestCallback;
	}

	private void sendFirstPending()
	{
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
			tearDown();
		}
	}

	public void run()
	{
		Thread.currentThread().setName(getClass().getName()+" Thread");
		while (isRunning())
			sendFirstPending();

		LOGGER.fine("Natural end of run ");
	}
}
