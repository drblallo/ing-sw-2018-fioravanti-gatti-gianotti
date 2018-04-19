package progetto.network.localconnection;

import progetto.network.IEnforce;
import progetto.network.AbstractRoomRequest;
import progetto.network.INetworkClientHandler;
import progetto.utils.Callback;

/**
 * An implementation of INetworkClientHandler that does not go through the network.
 * Calls are blocking, calls are not synchronized.
 */
public class LocalConnectionHandler implements INetworkClientHandler
{
	private boolean isRunning = true;
	private final Callback<AbstractRoomRequest> requestCallback = new Callback<>();
	private final LocalConnectionClient otherSide;

	LocalConnectionHandler(LocalConnectionClient c)
	{
		otherSide = c;
	}

	public void disconnect(boolean disconectGracefully)
	{
		if (disconectGracefully)
			otherSide.disconnect(false);

		isRunning = false;
	}

	public void sendMessage(String message)
	{
		otherSide.getMessageCallback().call(message);
	}

	public void sendEnforce(IEnforce enforce)
	{
		otherSide.getEnforceCallback().call(enforce);
	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public Callback<AbstractRoomRequest> getRequestCallback()
	{
		return requestCallback;
	}
}
