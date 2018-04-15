package progetto.network.socket;

import progetto.network.AbstractEnforce;
import progetto.network.AbstractRequest;
import progetto.network.INetworkClientHandler;
import progetto.utils.Callback;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the INetworkClientHandler for the socket implementation
 */
final class ClientHandler extends AbstractSocketManager implements INetworkClientHandler
{

	private static final Logger LOGGER = Logger.getLogger( ClientHandler.class.getName() );

	private final Callback<INetworkClientHandler> connectionClosedCallbakc = new Callback<INetworkClientHandler>();
	private final Callback<AbstractRequest> requestCallback = new Callback<AbstractRequest>();

	ClientHandler(Socket s)
	{
		super(s);
	}

	/**
	 *
	 * @param message that must be sent to the player
	 */
	public void sendMessage(String message)
	{
		LOGGER.log(Level.FINE, "Sending message {0}", message);
		sendCommand(new MessageCommand(message));
	}

	/**
	 *
	 * @return the callback that is called when the connection is lost and the socket gets closed
	 */
	public Callback<INetworkClientHandler> getConnectionLostCallback() {
		return connectionClosedCallbakc;
	}

	/**
	 * force the client to do something
	 * @param enforce what must be done by the player
	 */
	public void sendEnforce(AbstractEnforce enforce) {
		sendCommand(new EnforceCommand(enforce));
	}

	/**
	 *
	 * @return the callback that is called when a request is received
	 */
	public Callback<AbstractRequest> getRequestCallback() {
		return requestCallback;
	}

	/**
	 * called when the connection is closing
	 */
	protected void onTearDown() {
		connectionClosedCallbakc.call(this);
	}
}
