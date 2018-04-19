package progetto.network.rmi;

import progetto.network.IEnforce;
import progetto.utils.Callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

final class RMIRemoteClientSession extends UnicastRemoteObject implements IRemoteClientSession {
	private static final Logger LOGGER = Logger.getLogger(RMIRemoteClientSession.class.getName());
	private final transient Callback<String> messageCallback = new Callback<>();
	private final transient Callback<IEnforce> enforceCallback = new Callback<>();
	private final transient Callback<IRemoteClientSession> connectionClosedCallback = new Callback<>();

	protected RMIRemoteClientSession() throws RemoteException {
	}

	public void sayGoodBye() {
		LOGGER.fine("tried to disconnect");
		getConnectionLostCallback().call(this);
	}

	public void sendMessage(final String message) {
		LOGGER.fine("received message ");
		messageCallback.call(message);
	}

	public void sendEnforce(final IEnforce enforce) {
		LOGGER.log(Level.FINE, "received enforce: {0}", enforce.getClass().getName());
		enforceCallback.call(enforce);
	}

	public Callback<IRemoteClientSession> getConnectionLostCallback() {
		return connectionClosedCallback;
	}

	public Callback<IEnforce> getEnforceCallback() {
		return enforceCallback;
	}

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
