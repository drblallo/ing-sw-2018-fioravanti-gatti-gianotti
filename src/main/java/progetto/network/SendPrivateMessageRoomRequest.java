package progetto.network;

final class SendPrivateMessageRoomRequest implements AbstractRoomRequest {
	private String message;
	private int targetID;

	SendPrivateMessageRoomRequest(String message, int targetID) {
		this.message = message;
		this.targetID = targetID;
	}

	public void execute(AbstractRoom room, ServerConnection serverConnection)
	{
		room.sendMessage(targetID, message);
	}
}
