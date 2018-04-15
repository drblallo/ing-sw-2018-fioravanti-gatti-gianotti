package progetto.network.socket.server;

import progetto.network.connectionstate.Room;
import progetto.network.socket.AbstractNetworkCommand;

/**
 * tries to pick a chair for the player
 */
public class PickChairCommand extends AbstractNetworkCommand<ClientHandler>
{
	private int chair;

	public PickChairCommand(int chairID)
	{
		chair = chairID;
	}


	protected void execute(ClientHandler mng) {
		Room r = mng.getServerState().getRoom(mng.getRoomID());
		if (r != null)
			r.setPlayerChair(mng.getPlayerID(), chair);
	}
}
