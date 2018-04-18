package progetto.network.rmi;

import progetto.network.AbstractEnforce;
import progetto.utils.Callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

final class RMIRemoteClientSession extends UnicastRemoteObject implements IRemoteClientSession {
	private static final Logger LOGGER = Logger.getLogger(RMIRemoteClientSession.class.getName());
	private final transient Callback<String> messageCallback = new Callback<String>();
	private final transient Callback<AbstractEnforce> enforceCallback = new Callback<AbstractEnforce>();
	private final transient Callback<IRemoteClientSession> connectionClosedCallback = new Callback<IRemoteClientSession>();

	protected RMIRemoteClientSession() throws RemoteException {
	}

	public void sayGoodBye() {
		LOGGER.info("tried to disconnect");
		getConnectionLostCallback().call(this);
	}

	public void sendMessage(final String message) {
		LOGGER.info("received message ");
		messageCallback.call(message);
	}

	public void sendEnforce(final AbstractEnforce enforce) {
		LOGGER.log(Level.INFO, "received enforce: {0}", enforce.getClass().getName());

		enforceCallback.call(enforce);
	}

	public Callback<IRemoteClientSession> getConnectionLostCallback() {
		return connectionClosedCallback;
	}

	public Callback<AbstractEnforce> getEnforceCallback() {
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
