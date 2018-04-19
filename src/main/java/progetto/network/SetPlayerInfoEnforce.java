package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

final class SetPlayerInfoEnforce extends AbstractEnforce
{

	private static final Logger LOGGER = Logger.getLogger(SetReadyRoomRequest.class.getName());
	private int playerID;

	SetPlayerInfoEnforce(int id) {
		playerID = id;
	}

	public void execute(ClientConnection c)
	{
		LOGGER.log(Level.INFO, "setting player id {0}", playerID);
		c.setPlayerID(playerID);
	}

}
