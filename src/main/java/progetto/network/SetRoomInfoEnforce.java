package progetto.network;

final class SetRoomInfoEnforce implements IEnforce {
	private RoomView room;

	SetRoomInfoEnforce(RoomView r) {
		room = r;
	}

	public void execute(ClientConnection mng) {
		mng.setRoomInfo(room);
	}
}
