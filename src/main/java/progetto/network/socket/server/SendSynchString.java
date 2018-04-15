package progetto.network.socket.server;

import progetto.network.socket.AbstractNetworkCommand;

/**
 * used to send a single command
 */
public class SendSynchString extends AbstractNetworkCommand<ClientHandler>
{
	private String toSend;

	public SendSynchString(String s)
	{
		toSend = s;
	}


	protected void execute(ClientHandler mng) {
		mng.getManager().processCommand(toSend, mng.getRoomID(), mng.getPlayerID());
	}
}
