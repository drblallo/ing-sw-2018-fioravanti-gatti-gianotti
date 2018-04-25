package progetto.network;

/**
 * Request sent when the player wishes to send a message to another player inside the room
 */
final class SendPrivateMessageRoomRequest implements IRoomRequest {
	private String message;
	private int targetID;

	/**
	 * Builds the requests
	 * @param message the message that must be sent to the player
	 * @param targetID the id of the player that must receive the message.
	 */
	SendPrivateMessageRoomRequest(String message, int targetID) {
		this.message = message;
		this.targetID = targetID;
	}

	/**
	 * tries to send the message, notice that send message depends on the implementation of AbstractRoom,
	 * therefore a room could refuse to send the message
	 * @param room The room where the player is located.
	 * @param serverConnection the connection that sent the message
	 */
	public void execute(AbstractRoom room, ServerConnection serverConnection)
	{
		room.sendMessage(targetID, message);
	}
}
