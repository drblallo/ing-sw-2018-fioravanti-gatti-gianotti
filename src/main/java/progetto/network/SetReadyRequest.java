package progetto.network;

import progetto.network.connectionstate.PlayerInfo;

import java.util.logging.Level;
import java.util.logging.Logger;

final class SetReadyRequest extends AbstractRequest {

	private static final Logger LOGGER = Logger.getLogger(SetReadyRequest.class.getName());
	private boolean isReady;

	SetReadyRequest(boolean isReady) {
		this.isReady = isReady;
	}

	public void execute(ConnectionsManager manager, ServerConnection serverConnection) {
		LOGGER.log(Level.INFO, "Trying to set player to {0}", isReady);
		PlayerInfo info = manager.getServerState().getPlayer(serverConnection.getPlayerID());
		if (info != null)
			info.setReady(isReady);
	}
}
