package progetto.network.socket;

import progetto.network.IEnforce;
import progetto.network.IRoomRequest;
import progetto.network.INetworkHandler;
import progetto.utils.Callback;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the INetworkHandler for the socket implementation
 */
final class SocketHandler extends AbstractSocket implements INetworkHandler {

	private static final Logger LOGGER = Logger.getLogger(SocketHandler.class.getName());

	private final Callback<IRoomRequest> requestCallback = new Callback<>();

	SocketHandler(Socket s) {
		super(s);
	}

	/**
	 * @param message that must be sent to the player
	 */
	public void sendMessage(String message) {
		LOGGER.log(Level.FINE, "Sending message {0}", message);
		sendCommand(new MessageCommand(message));
	}


	/**
	 * force the client to do something
	 *
	 * @param enforce what must be done by the player
	 */
	public void sendEnforce(IEnforce enforce) {
		sendCommand(new EnforceCommand(enforce));
	}

	/**
	 * @return the callback that is called when a request is received
	 */
	public Callback<IRoomRequest> getRequestCallback() {
		return requestCallback;
	}

	/**
	 * called when the connection is closing
	 */
	protected void onTearDown()
	{
		//nothing to do here
	}
}
