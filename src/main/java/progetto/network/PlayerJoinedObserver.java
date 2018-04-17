package progetto.network;

import progetto.utils.IObserver;

/**
 * attached to new modules
 * when a player joins the game it gets addedd to the connection manager
 */
final class PlayerJoinedObserver implements IObserver<INetworkClientHandler> {

	private NetworkServer server;

	PlayerJoinedObserver(NetworkServer s, INetworkModule module) {
		server = s;
		module.getPlayerJoinedCallback().addObserver(this);
	}

	public void notifyChange(INetworkClientHandler ogg) {
		server.getConnectionsManager().add(ogg);
	}
}
