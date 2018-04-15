package progetto.network.socket.server;

import progetto.network.connectionstate.Room;
import progetto.utils.IObserver;

class RoomObserver implements IObserver<Room> {

	private ClientHandler handler;

	RoomObserver(ClientHandler h)
	{
		handler = h;
	}

	public void notifyChange(Room ogg)
	{
		handler.sendRoomInfo();

		if (ogg.getRoomID() != handler.getRoomID())
			ogg.getChangeEvent().removeObserver(this);
	}
}
