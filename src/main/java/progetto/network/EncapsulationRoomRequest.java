package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

final class EncapsulationRoomRequest implements AbstractRoomRequest
{
	private final AbstractServerRequest request;
	private static final Logger LOGGER = Logger.getLogger(EncapsulationRoomRequest.class.getName());

	EncapsulationRoomRequest(AbstractServerRequest request)
	{
		this.request = request;
		LOGGER.log(Level.FINE, "sending over encapsulated request {0}", request.getClass().getName());
	}

	public void execute(AbstractRoom room, ServerConnection serverConnection)
	{
		LOGGER.log(Level.FINE, "Handing over encapsulated request {0}", request.getClass().getName());
		request.setAuthor(serverConnection);
		room.offerRequest(request);
	}

}
