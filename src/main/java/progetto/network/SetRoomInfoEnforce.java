package progetto.network;

import progetto.network.connectionstate.Room;

final class SetRoomInfoEnforce extends AbstractEnforce
{
	private Room room = null;

	SetRoomInfoEnforce(Room r)
	{
		room = r;
	}

	public void execute(ClientConnection mng) {
		mng.setRoomInfo(room);
	}
}
