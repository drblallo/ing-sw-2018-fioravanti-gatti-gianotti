package progetto.network.socket.client;

import progetto.network.socket.AbstractNetworkCommand;

/**
 * updates the player id
 */
public class SetPlayerIDCommand extends AbstractNetworkCommand<SocketClient>
{
	private int playerID;

	public SetPlayerIDCommand(int id)
	{
		playerID = id;
	}


	protected void execute(SocketClient mng) {
		mng.setPlayerID(playerID);
	}
}
