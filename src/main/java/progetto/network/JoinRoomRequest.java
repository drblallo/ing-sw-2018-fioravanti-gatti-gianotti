package progetto.network;

final class JoinRoomRequest extends AbstractServerRequest
{

	private int roomID;
	private String playerName;

	JoinRoomRequest(int room, String playerN) {
		playerName = playerN;
		roomID = room;

	}

	public void execute(ServerState state, AbstractRoom room)
	{
		state.placePlayer(playerName, roomID, getAuthor());
	}
}
