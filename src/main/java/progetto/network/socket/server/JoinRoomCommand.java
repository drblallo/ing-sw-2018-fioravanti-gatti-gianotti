package progetto.network.socket.server;

import progetto.network.socket.AbstractNetworkCommand;

public class JoinRoomCommand extends AbstractNetworkCommand<ClientHandler>
{
	private int roomID;
	private String player;

	public JoinRoomCommand(int room, String playerName)
	{
		roomID = room;
		player = playerName;
	}

	protected void execute(ClientHandler mng) {
		mng.getServerState().placePlayer(mng.getPlayerID(), player, roomID);
	}
}
