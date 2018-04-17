package progetto.network.rmi;

import progetto.network.AbstractEnforce;
import progetto.network.AbstractRequest;
import progetto.network.INetworkClientHandler;
import progetto.utils.Callback;
import progetto.utils.IObserver;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RMIClientHandler implements INetworkClientHandler {
	private static final Logger LOGGER = Logger.getLogger(RMIClientHandler.class.getName());
	private final IRemoteClientSession session;
	private boolean isDead = false;
	private Callback<String> messageCallback;
	private Callback<AbstractRequest> requestCallback;

	public RMIClientHandler(IRemoteClientSession s, RMIRemoteSession local) {
		LOGGER.log(Level.FINE, "starting client handler");
		session = s;
		requestCallback = local.getRequestCallback();
		messageCallback = local.getMessageCallback();
		local.getConnectionClosedCallback().addObserver(new IObserver<RMIRemoteSession>() {
			public void notifyChange(RMIRemoteSession ogg) {
				tearDown();
			}
		});
	}

	public boolean isRunning() {
		return !isDead;
	}

	public synchronized void disconnect(boolean disconectGracefully) {
		isDead = true;
		if (disconectGracefully) {
			try {
				LOGGER.log(Level.FINE, "sending good bye");
				session.sayGoodBye();
			} catch (RemoteException e) {
				LOGGER.log(Level.SEVERE, "Failed to send goodbye: {0}", e.getMessage());
			}
		}
		tearDown();
	}

	public synchronized void sendMessage(final String message) {
		try {
			LOGGER.log(Level.INFO, "Sending message");
			session.sendMessage(message);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to send message: {0}", e.getMessage());
			tearDown();
		}

	}

	public synchronized void sendEnforce(final AbstractEnforce enforce) {
		try {
			LOGGER.log(Level.FINE, "sending enforce");
			session.sendEnforce(enforce);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to send enforce: {0}", e.getMessage());
			tearDown();
		}
	}

	private synchronized void tearDown() {
		isDead = true;
	}

	public synchronized Callback<String> getMessageCallback() {
		return messageCallback;
	}

	public synchronized Callback<AbstractRequest> getRequestCallback() {
		return requestCallback;
	}
}
