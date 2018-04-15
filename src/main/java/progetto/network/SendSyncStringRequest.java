package progetto.network;

import progetto.network.connectionstate.Room;

final class SendSyncStringRequest extends AbstractRequest
{
	private String command;

	SendSyncStringRequest(String command) {
		this.command = command;
	}

	public void execute(ConnectionsManager manager, ServerConnection serverConnection) {
		Room r = manager.getServerState().getRoomOfPlayer(serverConnection.getPlayerID());
		if (r != null)
			manager.processCommand(command, r.getRoomID(), serverConnection.getPlayerID());
	}
}
