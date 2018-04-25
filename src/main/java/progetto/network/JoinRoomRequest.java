package progetto.network;

/**
 * Request that is sent when the player wishes to switch room.
 */
final class JoinRoomRequest extends AbstractServerRequest
{

	private int roomID;
	private String playerName;

	/**
	 * Creates the request
	 * @param room the room that the player wishes to join
	 * @param playerN the name with which the player will be know as.
	 */
	JoinRoomRequest(int room, String playerN) {
		playerName = playerN;
		roomID = room;

	}

	/**
	 *
	 * @param state the server state that received this request.
	 * @param room the room that received this request
	 */
	void execute(ServerState state, AbstractRoom room)
	{
		state.placePlayer(playerName, roomID, getAuthor());
	}
}
