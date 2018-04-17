package progetto.network;

import progetto.utils.IObserver;

/**
 * attached to new modules
 * when a player joins the game it gets addedd to the connection manager
 */
final class PlayerJoinedObserver implements IObserver<INetworkClientHandler> {

	private ConnectionsManager manager;

	PlayerJoinedObserver(ConnectionsManager m, INetworkModule module) {
		manager = m;
		module.getPlayerJoinedCallback().addObserver(this);
	}

	public void notifyChange(INetworkClientHandler ogg) {
		manager.add(ogg);
	}
}
