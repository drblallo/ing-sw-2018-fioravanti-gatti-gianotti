package progetto.network.rmi;

import progetto.network.AbstractRoomRequest;
import progetto.utils.Callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

final class RMIRemoteSession extends UnicastRemoteObject implements IRemoteSession {
	private static final Logger LOGGER = Logger.getLogger(RMIRemoteSession.class.getName());
	private final transient Callback<String> messageCallback = new Callback<String>();
	private final transient Callback<AbstractRoomRequest> requestCallback = new Callback<AbstractRoomRequest>();
	private final transient Callback<RMIRemoteSession> connectionClosedCallback = new Callback<RMIRemoteSession>();

	protected RMIRemoteSession() throws RemoteException {
	}

	public void sayGoodBye() {
		final RMIRemoteSession s = this;
		LOGGER.info("tried to disconnect");
		connectionClosedCallback.call(s);
	}

	public void sendRequest(final AbstractRoomRequest request) {
		LOGGER.log(Level.INFO, "sending request {0}", request.getClass().getName());
		requestCallback.call(request);
	}

	public Callback<String> getMessageCallback() {
		return messageCallback;
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
