package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Request that is used to try to get a particular chair inside a server room
 */
final class PickChairRoomRequest implements IRoomRequest
{
	private static final Logger LOGGER = Logger.getLogger(PickChairRoomRequest.class.getName());
	private int charID;

	PickChairRoomRequest(int charID) {
		this.charID = charID;
	}

	/**
	 * Tries to set the player chair to the one provided
	 * @param room the room where the player that sent this request is located.
	 * @param serverConnection the serverConnection that sent this request
	 */
	public void execute(AbstractRoom room, ServerConnection serverConnection)
	{
		LOGGER.log(Level.FINE, "player is trying to pick chair {0}", charID);
		room.setPlayerChair(serverConnection.getPlayerID(), charID);
	}
}
