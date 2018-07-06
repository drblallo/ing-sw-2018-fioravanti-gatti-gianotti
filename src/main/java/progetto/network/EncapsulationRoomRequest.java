package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Encapsulate a server request in a room requests, so that it can be carried over to the higher level
 * @author Massimo
 */
final class EncapsulationRoomRequest implements IRoomRequest
{
	private final AbstractServerRequest request;
	private static final Logger LOGGER = Logger.getLogger(EncapsulationRoomRequest.class.getName());

	/**
	 * Creates a encapsulation room request
	 * @param request the abstract server request that must be sent to the server
	 */
	EncapsulationRoomRequest(AbstractServerRequest request)
	{
		this.request = request;
		LOGGER.log(Level.FINE, "sending over encapsulated request {0}", request.getClass().getName());
	}

	/**
	 * offers the server request to the room.
	 * @param room the room that received the request
	 * @param serverConnection the serverConnection that sent the request
	 */
	public void execute(AbstractRoom room, ServerConnection serverConnection)
	{
		LOGGER.log(Level.FINE, "Handing over encapsulated request {0}", request.getClass().getName());
		request.setAuthor(serverConnection);
		room.offerRequest(request);
	}

}
