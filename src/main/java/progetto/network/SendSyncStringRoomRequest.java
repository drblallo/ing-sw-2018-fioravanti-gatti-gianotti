package progetto.network;

/**
 * Request sent when the player desires to make a modification to the sync object inside that room.
 */
final class SendSyncStringRoomRequest implements IRoomRequest
{
	private String command;

	/**
	 * Builds the command
	 * @param command the command that must be applied to the sync object.
	 */
	SendSyncStringRoomRequest(String command)
	{
		this.command = command;
	}

	/**
	 * Tries to process the command. This done by the actual implementation of Abstract room, therefore
	 * the room could refuse to do so.
	 * @param room the room where the player issuing this request is located.
	 * @param serverConnection the player issuing this request.
	 */
	public void execute(AbstractRoom room, ServerConnection serverConnection)
	{
		room.processCommand(command, serverConnection.getPlayerID());
	}
}
