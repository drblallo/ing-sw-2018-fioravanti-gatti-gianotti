package progetto.network;


/**
 * Request sent every time a client wishes to create a new room on the server.
 */
final class RoomCreationRequest extends AbstractServerRequest
{
	private String name;

	RoomCreationRequest(String name) {
		this.name = name;
	}

	/**
	 *
	 * @param state the server state that received this request.
	 * @param room the room where the player currently resides. IT'S NOT THE NEW ROOM
	 */
	void execute(ServerState state, AbstractRoom room)
	{
		state.createRoom(name);
	}
}
