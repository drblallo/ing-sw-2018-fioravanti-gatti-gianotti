package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

final class SetReadyRoomRequest implements AbstractRoomRequest
{

	private static final Logger LOGGER = Logger.getLogger(SetReadyRoomRequest.class.getName());
	private boolean isReady;

	SetReadyRoomRequest(boolean isReady) {
		this.isReady = isReady;
	}

	public void execute(AbstractRoom room, ServerConnection serverConnection)
	{
		LOGGER.log(Level.FINE, "Trying to set player to {0}", isReady);
		room.setPlayerReady(serverConnection.getPlayerID(), isReady);
	}
}
