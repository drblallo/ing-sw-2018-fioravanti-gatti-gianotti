package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

final class EncapsulationRoomRequest extends AbstractRoomRequest
{
	private final AbstractServerRequest request;
	private static final Logger LOGGER = Logger.getLogger(EncapsulationRoomRequest.class.getName());

	EncapsulationRoomRequest(AbstractServerRequest request)
	{
		this.request = request;
		LOGGER.log(Level.INFO, "sending over encapsulated request {0}", request.getClass().getName());
	}

	public void execute(AbstractRoom room, ServerConnection serverConnection)
	{
		LOGGER.log(Level.INFO, "Handing over encapsulated request {0}", request.getClass().getName());
		request.setAuthor(serverConnection);
		room.offerRequest(request);
	}

}
