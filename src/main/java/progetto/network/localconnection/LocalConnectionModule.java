package progetto.network.localconnection;

import progetto.network.INetworkHandler;
import progetto.network.INetworkModule;
import progetto.utils.Callback;

/**
 * An implementations of INetworkModule that does not go throught the network
 */
public final class LocalConnectionModule implements INetworkModule
{
	private boolean isRunning = false;
	private final Callback<INetworkHandler> playerJoinedCallback = new Callback<>();

	/**
	 * mark the connection module as stopped
	 */
	public void stop()
	{
		isRunning = false;
	}

	/**
	 *	mark the connection module as started
	 */
	public void start()
	{
		isRunning = true;
	}

	/**
	 *
	 * @return true if the connection has started.
	 */
	public boolean isRunning()
	{
		return isRunning;
	}

	/**
	 *
	 * @return the callback that is called every time player connects to this module
	 */
	public Callback<INetworkHandler> getPlayerJoinedCallback()
	{
		return playerJoinedCallback;
	}

	/**
	 * Spawn the server connection, attache the client and the server connection to one another
	 * calls the playerJoinedCallback
	 * @param c the player that is trying to join
	 */
	void connect(LocalConnectionClient c)
	{
		LocalConnectionHandler h = new LocalConnectionHandler(c);
		c.setConnection(h);
		playerJoinedCallback.call(h);
	}
}
