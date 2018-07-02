package progetto.network.rmi;

import progetto.network.IEnforce;
import progetto.network.NetworkSettings;
import progetto.utils.Callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * skeleton of IRemoteClientSession
 */
final class RMIRemoteClientSession extends UnicastRemoteObject implements IRemoteClientSession {
	private static final Logger LOGGER = Logger.getLogger(RMIRemoteClientSession.class.getName());
	private final transient Callback<String> messageCallback = new Callback<>();
	private final transient Callback<IEnforce> enforceCallback = new Callback<>();
	private final transient Callback<IRemoteClientSession> connectionClosedCallback = new Callback<>();
	private final Timer timer = new Timer();
	private int pings = 0;

	RMIRemoteClientSession() throws RemoteException {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				pings++;
				if (pings >= NetworkSettings.MAX_TIME_TO_LIVE_SKIPPED)
					close();
			}
		}, NetworkSettings.DEFAULT_TIME_TO_LIVE, NetworkSettings.DEFAULT_TIME_TO_LIVE);
	}

	public void ping()
	{
		pings = 0;
	}

	/**
	 * notifies that the connection is getting closed
	 */
	public void sayGoodBye() {
		LOGGER.fine("tried to disconnect");
		close();
	}

	/**
	 * tries to send a message
	 * @param message the message that must be sent
	 */
	public void sendMessage(final String message) {
		LOGGER.fine("received message ");
		messageCallback.call(message);
	}

	/**
	 * tears down the connection
	 */
	private void close()
	{
		getConnectionLostCallback().call(this);
		timer.purge();
		timer.cancel();
	}

	/**
	 * tries to send a enforce
	 * @param enforce the enforce that must be sent
	 */
	public void sendEnforce(final IEnforce enforce) {
		LOGGER.log(Level.FINE, "received enforce: {0}", enforce.getClass().getName());
		enforceCallback.call(enforce);
	}

	/**
	 *
	 * @return the callback that is called every time a message is received
	 */
	public Callback<IRemoteClientSession> getConnectionLostCallback() {
		return connectionClosedCallback;
	}

	/**
	 *
	 * @return the callback that is called every time a enforce is received
	 */
	public Callback<IEnforce> getEnforceCallback() {
		return enforceCallback;
	}

	/**
	 *
	 * @return the message callback that is called every time a message is received
	 */
	public Callback<String> getMessageCallback() {
		return messageCallback;
	}

	@Override
	public boolean equals(Object ogg)
	{
		return super.equals(ogg);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

}
