package progetto.network;

import progetto.network.connectionstate.ServerState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkServer {

	private static final Logger LOGGER = Logger.getLogger(NetworkServer.class.getName());
	private List<INetworkModule> modules = Collections.synchronizedList(new ArrayList<INetworkModule>());
	private boolean running = false;
	private ConnectionsManager connectionsManager;
	private ISyncFactory factory;

	public NetworkServer(ISyncFactory fac)
	{
		factory = fac;
		connectionsManager = new ConnectionsManager(factory);
	}

	/**
	 * shutdows the server, disconnect every connection
	 */
	public synchronized void stop()
	{
		if (!isRunning()) {
			LOGGER.log(Level.WARNING, "Tried to stop a already stopped server");
			return;
		}

		LOGGER.fine("Closing all handlers");
		for (INetworkClientHandler h : connectionsManager.getHandlers())
			h.disconnect(true);

		LOGGER.fine("Stopping all modules");
		for (int a = 0; a < modules.size(); a++)
			modules.get(a).stop();

		running = false;
	}

	public synchronized void start()
	{
		if (isRunning()) {
			LOGGER.warning("Tried to start a already running server");
			return;
		}

		connectionsManager = new ConnectionsManager(factory);
		LOGGER.info("starting all modules");
		for (int a = 0; a < modules.size(); a++)
			modules.get(a).start();

		running = true;
	}

	/**
	 *
	 * @return true if it's running
	 */
	public boolean isRunning()
	{
		return running;
	}


	/**
	 * gets connectionManager
	 */
	public ConnectionsManager getConnectionsManager() {
		return connectionsManager;
	}

	public ServerState getServerState()
	{
		return connectionsManager.getServerState();
	}

	/**
	 * send message to all connected users
	 * @param message
	 */
	public synchronized void broadcastMessage(String message)
	{
		if (isRunning())
		{
			List<INetworkClientHandler> ls = connectionsManager.getHandlers();
			for (int a = 0; a < ls.size(); a++)
				ls.get(a).sendMessage(message);
		}
	}


	/**
	 * Attach a module to this server
	 */
	public synchronized void addModules(INetworkModule module)
	{
		if (modules.contains(module)) {
			LOGGER.warning("Trying to add a already present module to the network");
			return;
		}
		LOGGER.info("adding a module to the network");
		new PlayerJoinedObserver(this, module);
		new PlayerLeavedObserver(this, module);

		modules.add(module);
		if (isRunning())
		{
			LOGGER.info("starting the module");
			module.start();
		}
		else {
			LOGGER.info("stopping the module");
			module.stop();
		}
	}

}
