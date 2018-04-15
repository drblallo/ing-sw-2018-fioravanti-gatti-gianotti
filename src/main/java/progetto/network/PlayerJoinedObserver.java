package progetto.network;

import progetto.utils.IObserver;

class PlayerJoinedObserver implements IObserver<INetworkClientHandler> {

	private NetworkServer server;

	PlayerJoinedObserver(NetworkServer s, INetworkModule module)
	{
		server = s;
		module.getPlayerJoinedCallback().addObserver(this);
	}

	public void notifyChange(INetworkClientHandler ogg) {
		server.getConnectionsManager().add(ogg);
		ogg.setID(server.getServerState().getUnusedID());
	}
}
