package progetto.network.localconnection;

import progetto.network.IEnforce;
import progetto.network.AbstractRoomRequest;
import progetto.network.INetworkClient;
import progetto.utils.Callback;

/**
 * A local client connection is a fake network connection that implements INetworkClient.
 * All calls are blocking until they are solved on the other side.
 * Calls are not parallel.
 * Messages are sent even if the connection is closed.
 */
public class LocalConnectionClient implements INetworkClient
{
	private boolean isRunning = true;
	private LocalConnectionHandler otherSide;
	private final Callback<String> messageCallback = new Callback<>();
	private final Callback<IEnforce> enforceCallback = new Callback<>();

	public LocalConnectionClient(LocalConnectionServer server)
	{
		server.connect(this);
	}

	void setConnection(LocalConnectionHandler h)
	{
		otherSide = h;
	}

	LocalConnectionHandler getOtherSide()
	{
		return otherSide;
	}

	public void disconnect(boolean signalGoodBye)
	{
		if (signalGoodBye)
			otherSide.disconnect(false);

		isRunning = false;
	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public Callback<String> getMessageCallback()
	{
		return messageCallback;
	}

	public Callback<IEnforce> getEnforceCallback()
	{
		return enforceCallback;
	}

	public void sendRequest(AbstractRoomRequest request)
	{
		otherSide.getRequestCallback().call(request);
	}
}
