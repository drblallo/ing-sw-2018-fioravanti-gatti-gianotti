package progetto.network.socket.server;

import progetto.utils.IObserver;

class PlayerChangedRoomObserver implements IObserver<Integer>
{
	private ClientHandler handler;

	PlayerChangedRoomObserver(ClientHandler g)
	{
		handler = g;
	}

	public void notifyChange(Integer ogg) {
		if (ogg == handler.getPlayerID()) {
			handler.replaceObservers();
			handler.sendRoomInfo();
		}
	}
}
