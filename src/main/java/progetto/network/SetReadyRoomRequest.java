package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A request sent every time the client wishes to change his ready status
 * @author Massimo
 */
final class SetReadyRoomRequest implements IRoomRequest
{

	private static final Logger LOGGER = Logger.getLogger(SetReadyRoomRequest.class.getName());
	private boolean isReady;

	/**
	 * builds a new SetReadyRoomRequest
	 * @param isReady the ready value that must be sent.
	 */
	SetReadyRoomRequest(boolean isReady) {
		this.isReady = isReady;
	}

	/**
	 * tries to set the client to ready. This behaviour is defined by the room implementation.
	 * @param room the room where is located the client that issued this request
	 * @param serverConnection the server connection that issued this request.
	 */
	public void execute(AbstractRoom room, ServerConnection serverConnection)
	{
		LOGGER.log(Level.FINE, "Trying to set player to {0}", isReady);
		room.setPlayerReady(serverConnection.getPlayerID(), isReady);
	}
}
