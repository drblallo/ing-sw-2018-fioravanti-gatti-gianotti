package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

final class PickChairRoomRequest extends AbstractRoomRequest
{
	private static final Logger LOGGER = Logger.getLogger(PickChairRoomRequest.class.getName());
	private int charID;

	PickChairRoomRequest(int charID) {
		this.charID = charID;
	}

	public void execute(AbstractRoom room, ServerConnection serverConnection)
	{
		LOGGER.log(Level.INFO, "player is trying to pick chair {0}", charID);
		room.setPlayerChair(serverConnection.getPlayerID(), charID);
	}
}
