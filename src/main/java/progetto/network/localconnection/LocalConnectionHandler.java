package progetto.network.localconnection;

import progetto.network.IEnforce;
import progetto.network.INetworkHandler;
import progetto.network.IRoomRequest;
import progetto.utils.Callback;

/**
 * An implementation of INetworkHandler that does not go through the network.
 * Calls are blocking, calls are not synchronized.
 * @author Massimo
 */
public final class LocalConnectionHandler implements INetworkHandler
{
	private boolean isRunning = true;
	private final Callback<IRoomRequest> requestCallback = new Callback<>();
	private final LocalConnectionClient otherSide;

	/**
	 * create a new instance
	 * @param c the client that will exchange messages with this object
	 */
	LocalConnectionHandler(LocalConnectionClient c)
	{
		otherSide = c;
	}

	/**
	 * Tears down the connection
	 * @param disconectGracefully true if the client should be informed that the connection is getting closed
	 */
	public void disconnect(boolean disconectGracefully)
	{
		if (disconectGracefully)
			otherSide.disconnect(false);

		isRunning = false;
	}

	/**
	 *
	 * @param message the string to be sent
	 */
	public void sendMessage(String message)
	{
		otherSide.getMessageCallback().call(message);
	}

	/**
	 *
	 * @param enforce the enforce to be sent
	 */
	public void sendEnforce(IEnforce enforce)
	{
		otherSide.addEnforce(enforce);
	}

	/**
	 *
	 * @return true if the connection did not got closed
	 */
	public boolean isRunning()
	{
		return isRunning;
	}

	/**
	 *
	 * @return the callback that is called every time a request is received
	 */
	public Callback<IRoomRequest> getRequestCallback()
	{
		return requestCallback;
	}
}
