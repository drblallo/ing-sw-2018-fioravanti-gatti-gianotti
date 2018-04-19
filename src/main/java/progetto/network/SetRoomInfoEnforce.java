package progetto.network;

final class SetRoomInfoEnforce extends AbstractEnforce {
	private RoomView room;

	SetRoomInfoEnforce(RoomView r) {
		room = r;
	}

	public void execute(ClientConnection mng) {
		mng.setRoomInfo(room);
	}
}
