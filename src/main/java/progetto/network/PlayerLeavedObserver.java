package progetto.network;

import progetto.utils.IObserver;

/**
 * this is the class that is attached by the network server to a module
 * this is called when a player drops out and removes it from the connections manager
 */
final class PlayerLeavedObserver implements IObserver<ServerConnection>
{
	private ConnectionsManager server;

	public PlayerLeavedObserver(ConnectionsManager s, ServerConnection con)
	{
		server = s;
		con.getConnectionLostCallback().addObserver(this);
	}

	public void notifyChange(ServerConnection ogg) {
		server.remove(ogg);
	}
}
