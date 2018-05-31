package progetto.network.localconnection;

import progetto.network.IEnforce;
import progetto.network.INetworkClient;
import progetto.network.IRoomRequest;
import progetto.utils.Callback;

/**
 * A local client connection is a fake network connection that implements INetworkClient.
 * All calls are blocking until they are solved on the other side.
 * Calls are not parallel.
 * Messages are sent even if the connection is closed.
 */
public final class LocalConnectionClient implements INetworkClient
{
	private boolean isRunning = true;
	private LocalConnectionHandler otherSide;
	private final Callback<String> messageCallback = new Callback<>();
	private final Callback<IEnforce> enforceCallback = new Callback<>();

	/**
	 * builds a new localClientConnection
	 * @param server the server this connection will be attached to
	 */
	public LocalConnectionClient(LocalConnectionModule server)
	{
		server.connect(this);
	}

	/**
	 * sets the handler that the server will use to exchange messages with this object.
	 * @param h the handler
	 */
	void setConnection(LocalConnectionHandler h)
	{
		otherSide = h;
	}

	/**
	 * @return the handler that is exchanging messages with this object.
	 */
	LocalConnectionHandler getOtherSide()
	{
		return otherSide;
	}

	/**
	 * tears down the connection
	 * @param signalGoodBye true the connection must be closed gracefully.
	 */
	public void disconnect(boolean signalGoodBye)
	{
		if (signalGoodBye)
			otherSide.disconnect(false);

		isRunning = false;
	}

	/**
	 *
	 * @return true if the connection is still open
	 */
	public boolean isRunning()
	{
		return isRunning;
	}

	/**
	 *
	 * @return the callback that is called every time a message is received.
	 */
	public Callback<String> getMessageCallback()
	{
		return messageCallback;
	}

	/**
	 *
	 * @return the callback that is called every time a enforce is received
	 */
	public Callback<IEnforce> getEnforceCallback()
	{
		return enforceCallback;
	}

	/**
	 * sends a request to the handler connected to this object.
	 * @param request request to be sent
	 */
	public void sendRequest(IRoomRequest request)
	{
		otherSide.getRequestCallback().call(request);
	}
}
