package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

final class SetPlayerInfoEnforce implements IEnforce
{

	private static final Logger LOGGER = Logger.getLogger(SetReadyRoomRequest.class.getName());
	private int playerID;

	SetPlayerInfoEnforce(int id) {
		playerID = id;
	}

	public void execute(ClientConnection c)
	{
		LOGGER.log(Level.FINE, "setting player id {0}", playerID);
		c.setPlayerID(playerID);
	}

}
