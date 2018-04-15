package progetto.network.socket.server;

import progetto.network.INetworkClientHandler;
import progetto.network.socket.AbstractNetworkCommand;

/**
 * tries to send a message to a particular player
 */
public class PrivateMessageCommand extends AbstractNetworkCommand<ClientHandler>
{
	private String auth;
	private String msg;
	private int targetID;

	public PrivateMessageCommand(String author, String message, int recieverID)
	{
		auth = author;
		msg = message;
		targetID = recieverID;
	}


	protected void execute(ClientHandler mng) {
		INetworkClientHandler hand = mng.getManager().getHandlerOfPlayer(targetID);
		if (hand != null) {
			hand.sendMessage(auth +": "+ msg);
		}
	}
}
