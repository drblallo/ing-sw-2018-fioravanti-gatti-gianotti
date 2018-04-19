package progetto.network.rmi;

import progetto.network.AbstractRoomRequest;
import progetto.utils.Callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

final class RMIRemoteSession extends UnicastRemoteObject implements IRemoteSession {
	private static final Logger LOGGER = Logger.getLogger(RMIRemoteSession.class.getName());
	private final transient Callback<AbstractRoomRequest> requestCallback = new Callback<>();
	private final transient Callback<RMIRemoteSession> connectionClosedCallback = new Callback<>();

	protected RMIRemoteSession() throws RemoteException {
	}

	public void sayGoodBye() {
		final RMIRemoteSession s = this;
		LOGGER.fine("tried to disconnect");
		connectionClosedCallback.call(s);
	}

	public void sendRequest(final AbstractRoomRequest request) {
		LOGGER.log(Level.FINE, "sending request {0}", request.getClass().getName());
		requestCallback.call(request);
	}

	public Callback<AbstractRoomRequest> getRequestCallback() {
		return requestCallback;
	}

	public Callback<RMIRemoteSession> getConnectionClosedCallback() {
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
