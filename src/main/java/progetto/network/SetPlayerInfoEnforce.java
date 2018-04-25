package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * And enforce that is sent when the server wants to notify that the player id is changed.
 */
final class SetPlayerInfoEnforce implements IEnforce
{

	private static final Logger LOGGER = Logger.getLogger(SetReadyRoomRequest.class.getName());
	private int playerID;

	/**
	 * builds this enforce
	 * @param id the id that must be sent to the player
	 */
	SetPlayerInfoEnforce(int id) {
		playerID = id;
	}

	/**
	 * forces the player to change his id
	 * @param c the client connection that must receive this update
	 */
	public void execute(ClientConnection c)
	{
		LOGGER.log(Level.FINE, "setting player id {0}", playerID);
		c.setPlayerID(playerID);
	}

}
