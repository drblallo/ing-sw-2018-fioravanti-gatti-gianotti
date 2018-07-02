package progetto.network.rmi;

import progetto.network.IRoomRequest;
import progetto.network.NetworkSettings;
import progetto.utils.Callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * implementation of IRemoteServerSession
 */
final class RMIRemoteServerSession extends UnicastRemoteObject implements IRemoteServerSession {
	private static final Logger LOGGER = Logger.getLogger(RMIRemoteServerSession.class.getName());
	private final transient Callback<IRoomRequest> requestCallback = new Callback<>();
	private final transient Callback<RMIRemoteServerSession> connectionClosedCallback = new Callback<>();
	private long pings;
	Timer timer = new Timer();

	RMIRemoteServerSession() throws RemoteException
	{
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run()
			{
				pings++;
				if (pings > NetworkSettings.MAX_TIME_TO_LIVE_SKIPPED)
					close();
			}
		},
		NetworkSettings.DEFAULT_TIME_TO_LIVE,
		NetworkSettings.DEFAULT_TIME_TO_LIVE);
	}

	private void close()
	{
		timer.cancel();
		timer.purge();
		connectionClosedCallback.call(this);
	}


	public void ping()
	{
		pings = 0;
	}

	/**
	 * notifies the client that the connection is getting closed
	 */
	public void sayGoodBye() {
		LOGGER.fine("tried to disconnect");
		close();
	}

	/**
	 * tries to send a request
	 * @param request the request to be sent
	 */
	public void sendRequest(final IRoomRequest request) {
		LOGGER.log(Level.FINE, "sending request {0}", request.getClass().getName());
		requestCallback.call(request);
	}

	/**
	 *
	 * @return the callback that is called every time a request is received
	 */
	public Callback<IRoomRequest> getRequestCallback() {
		return requestCallback;
	}

	/**
	 *
	 * @return the callback that is called when the connection is closed
	 */
	public Callback<RMIRemoteServerSession> getConnectionClosedCallback() {
		return connectionClosedCallback;
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
