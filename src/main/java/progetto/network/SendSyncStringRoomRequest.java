package progetto.network;

final class SendSyncStringRoomRequest extends AbstractRoomRequest
{
	private String command;

	SendSyncStringRoomRequest(String command)
	{
		this.command = command;
	}

	public void execute(AbstractRoom room, ServerConnection serverConnection)
	{
		room.processCommand(command, serverConnection.getPlayerID());
	}
}
