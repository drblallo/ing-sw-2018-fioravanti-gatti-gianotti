package progetto.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A network server is the class that handles the entire state of the network on the server
 * @author Massimo
 */
public class NetworkServer
{

	private static final Logger LOGGER = Logger.getLogger(NetworkServer.class.getName());
	private List<INetworkModule> modules = Collections.synchronizedList(new ArrayList<INetworkModule>());
	private boolean running = false;
	private ISyncFactory factory;
	private ServerState serverState;

	/**
	 * creates a network server that is able to keep synchronized object of the types spawned by the sync factory
	 * @param fac the factory that will spawn new objects in each room.
	 */
	public NetworkServer(ISyncFactory fac)
	{
		factory = fac;
	}

	/**
	 * shutdows the server, disconnect every connection
	 */
	public synchronized void stop()
	{
		if (!isRunning())
		{
			LOGGER.log(Level.WARNING, "Tried to stop a already stopped server");
			return;
		}

		LOGGER.fine("Closing all handlers");
		serverState.stop();

		LOGGER.fine("Stopping all modules");
		for (INetworkModule module : modules)
			module.stop();

		running = false;
	}

	/**
	 * starts or restart the server
	 */
	public synchronized void start()
	{
		if (isRunning())
		{
			LOGGER.warning("Tried to start a already running server");
			return;
		}
		running = true;

		serverState = new ServerState(factory);
		Thread t = new Thread(serverState);
		t.setName("Sagrada server thread");
		t.start();

		LOGGER.fine("starting all modules");
		for (INetworkModule module : modules)
			module.start();

	}

	/**
	 * @return true if it's running
	 */
	public boolean isRunning() {
		return running;
	}


	/**
	 *
	 * @return the server state view of this server
	 */
	public ServerStateView getServerStateClone()
	{
		return serverState.getView();
	}

	/**
	 *
	 * @param roomID the id that must be returned
	 * @return the room view requested
	 */
	public RoomView getRoomView(int roomID)
	{
		if (serverState.getRoom(roomID) == null)
			return null;
		return serverState.getRoom(roomID).getView();
	}

	/**
	 * send message to all connected users
	 *
	 * @param message the message that must be sent to every player
	 */
	public synchronized void broadcastMessage(String message)
	{
		if (isRunning())
			serverState.broadcast(message);
	}


	/**
	 * Attach a module to this server
	 */
	public synchronized void addModules(INetworkModule module)
	{
		if (modules.contains(module))
		{
			LOGGER.warning("Trying to add a already present module to the network");
			return;
		}
		LOGGER.fine("adding a module to the network");
		final NetworkServer s = this;

		module.getPlayerJoinedCallback().addObserver
				(ogg -> s.serverState.placePlayer("noName", -1, new ServerConnection(ogg)));

		modules.add(module);

		if (isRunning())
		{
			LOGGER.fine("starting the module");
			module.start();
		}
		else
		{
			LOGGER.fine("stopping the module");
			module.stop();
		}
	}

}
