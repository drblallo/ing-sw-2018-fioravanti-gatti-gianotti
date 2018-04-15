package progetto.network.socket.server;

import progetto.network.connectionstate.PlayerInfo;
import progetto.network.socket.AbstractNetworkCommand;

/**
 * tries to set a player as ready
 */
public final class SetReadyCommand extends AbstractNetworkCommand<ClientHandler> {
	private boolean isReady;

	public SetReadyCommand(boolean ready)
	{
		isReady = ready;
	}


	protected void execute(ClientHandler mng) {
		PlayerInfo info = mng.getServerState().getPlayer(mng.getPlayerID());
		if (info != null)
			info.setReady(isReady);
	}
}
