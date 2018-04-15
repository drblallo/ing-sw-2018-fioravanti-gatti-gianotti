package progetto.network.socket.server;

import progetto.network.socket.AbstractNetworkCommand;

/**
 * tries to create a room on the server
 */
public class CreateRoomCommand extends AbstractNetworkCommand<ClientHandler>
{

	private String room;

	public CreateRoomCommand(String roomName)
	{
		room = roomName;
	}

	protected void execute(ClientHandler mng) {
		mng.getServerState().createRoom(room);
	}
}
