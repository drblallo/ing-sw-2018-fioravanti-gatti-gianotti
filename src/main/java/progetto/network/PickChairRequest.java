package progetto.network;

import progetto.network.connectionstate.Room;

import java.util.logging.Level;
import java.util.logging.Logger;

final class PickChairRequest extends AbstractRequest {
	private static final Logger LOGGER = Logger.getLogger(PickChairRequest.class.getName());
	private int charID;

	PickChairRequest(int charID) {
		this.charID = charID;
	}

	public void execute(ConnectionsManager manager, ServerConnection serverConnection) {
		Room r = manager.getServerState().getRoomOfPlayer(serverConnection.getPlayerID());
		if (r != null) {
			LOGGER.log(Level.INFO, "player is trying to pick chair {0}", charID);
			r.setPlayerChair(serverConnection.getPlayerID(), charID);
		} else {
			LOGGER.log(Level.INFO, "player is not in a room and cannot pick a chair");
		}
	}
}
