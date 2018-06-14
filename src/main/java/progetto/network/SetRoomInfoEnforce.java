package progetto.network;

/**
 * Forces the client to update his room info
 */
final class SetRoomInfoEnforce implements IEnforce {

	private RoomView room;

	/**
	 * creates a new instance.
	 * @param r the room information that must be sent to the client.
	 */
	SetRoomInfoEnforce(RoomView r) {
		room = r;
	}

	/**
	 * forces the client to update his info
	 * @param mng the client that received this info.
	 */
	public void execute(ClientConnection mng) {
		mng.setRoomInfo(room);
	}
}
