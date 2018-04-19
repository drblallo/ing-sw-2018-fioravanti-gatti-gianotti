package progetto.network;

import progetto.utils.IObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkServer
{

	private static final Logger LOGGER = Logger.getLogger(NetworkServer.class.getName());
	private List<INetworkModule> modules = Collections.synchronizedList(new ArrayList<INetworkModule>());
	private boolean running = false;
	private ISyncFactory factory;
	private ServerState serverState;

	public NetworkServer(ISyncFactory fac)
	{
		factory = fac;
		serverState = new ServerState(factory);
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
		for (int a = 0; a < modules.size(); a++)
			modules.get(a).stop();

		running = false;
	}

	public synchronized void start()
	{
		if (isRunning())
		{
			LOGGER.warning("Tried to start a already running server");
			return;
		}
		running = true;

		serverState = new ServerState(factory);
		new Thread(serverState).start();

		LOGGER.info("starting all modules");
		for (int a = 0; a < modules.size(); a++)
			modules.get(a).start();

	}

	/**
	 * @return true if it's running
	 */
	public boolean isRunning() {
		return running;
	}


	public ServerStateView getServerStateClone()
	{
		return serverState.getView();
	}

	public RoomView getRoomView(int roomID)
	{
		if (serverState.getRoom(roomID) == null)
			return null;
		return serverState.getRoom(roomID).getView();
	}

	/**
	 * send message to all connected users
	 *
	 * @param message
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
		LOGGER.info("adding a module to the network");
		final NetworkServer s = this;

		module.getPlayerJoinedCallback().addObserver
				(new IObserver<INetworkClientHandler>()
				{
					public void notifyChange(INetworkClientHandler ogg)
					{
						s.serverState.placePlayer("noName", -1, new ServerConnection(ogg));
					}
				});

		modules.add(module);

		if (isRunning())
		{
			LOGGER.info("starting the module");
			module.start();
		}
		else
		{
			LOGGER.info("stopping the module");
			module.stop();
		}
	}

}
