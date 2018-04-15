package progetto.network;


import progetto.network.connectionstate.PlayerInfo;
import progetto.network.connectionstate.Room;
import progetto.utils.IObserver;

final class RoomChangedObserver implements IObserver<Room> {

	private ConnectionsManager manager;

	RoomChangedObserver(ConnectionsManager m)
	{
		manager = m;
	}

	public void notifyChange(Room ogg) {
		for (PlayerInfo p : ogg.getPlayers())
			manager.getHandlerOfPlayer(p.getPlayerID()).onRoomModified(ogg);
	}
}
