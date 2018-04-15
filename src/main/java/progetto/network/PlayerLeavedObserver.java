package progetto.network;

import progetto.utils.IObserver;

class PlayerLeavedObserver implements IObserver<INetworkClientHandler>
{
	private NetworkServer server;

	public PlayerLeavedObserver(NetworkServer s, INetworkModule mod)
	{
		server = s;
		mod.getPlayerLeavedCallback().addObserver(this);
	}

	public void notifyChange(INetworkClientHandler ogg) {
		server.getConnectionsManager().remove(ogg);
	}
}
