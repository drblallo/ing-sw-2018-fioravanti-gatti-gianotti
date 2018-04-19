package progetto.network.rmi;

import java.rmi.RemoteException;
import java.util.logging.Logger;

final class RMIRemoteLogger implements IRemoteLogger {
	private static final Logger LOGGER = Logger.getLogger(RMIRemoteLogger.class.getName());
	private final RMIServer server;

	RMIRemoteLogger(RMIServer server) {
		this.server = server;
	}

	public IRemoteSession login(final IRemoteClientSession remote) throws RemoteException {

		LOGGER.fine("Player tries to log in");
		final RMIRemoteSession local = new RMIRemoteSession();

		new Thread(() -> {
			RMIClientHandler handler = new RMIClientHandler(remote, local);
			server.getPlayerJoinedCallback().call(handler);
		}).start();

		return local;
	}

}
