package progetto.network;

final class JoinRoomRequest extends AbstractRequest {

	private int roomID;
	private String playerName;

	JoinRoomRequest(int room, String playerN) {
		playerName = playerN;
		roomID = room;

	}

	public void execute(ConnectionsManager manager, ServerConnection serverConnection) {
		manager.getServerState().placePlayer(serverConnection.getPlayerID(), playerName, roomID);
	}
}
