package progetto.network.rmi;

import progetto.network.INetworkClientHandler;
import progetto.network.INetworkModule;
import progetto.utils.Callback;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RMIServer implements INetworkModule {

	public static final int RMI_PORT = 8528;
	private static final Logger LOGGER = Logger.getLogger(RMIServer.class.getName());
	private static Registry registry = null;
	private final RMIRemoteLogger rmilogger = new RMIRemoteLogger(this);
	private final Callback<INetworkClientHandler> palyerJoinedCallback = new Callback<INetworkClientHandler>();
	private boolean isRunning = false;

	private synchronized Registry getRegistry() throws RemoteException {
		if (registry == null) {
			registry = LocateRegistry.createRegistry(RMI_PORT);
		}
		return registry;
	}

	public synchronized void stop() {

		if (!isRunning) {
			return;
		}
		isRunning = false;
		try {
			getRegistry().unbind("test");
			UnicastRemoteObject.unexportObject(rmilogger, true);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "failed to unbind, are we already running {0}", e.getMessage());
		}
	}


	public synchronized void start() {
		if (isRunning)
			return;

		isRunning = true;
		try {
			IRemoteLogger stub = (IRemoteLogger) UnicastRemoteObject.exportObject(rmilogger, RMI_PORT);
			getRegistry().rebind("test", stub);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "FAILED TO START RMI NETWORK MODULE: {0}", e.getMessage());
		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public Callback<INetworkClientHandler> getPlayerJoinedCallback() {
		return palyerJoinedCallback;
	}
}
