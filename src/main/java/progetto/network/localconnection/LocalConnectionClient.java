package progetto.network.localconnection;

import progetto.network.AbstractEnforce;
import progetto.network.AbstractRoomRequest;
import progetto.network.INetworkClient;
import progetto.utils.Callback;

public class LocalConnectionClient implements INetworkClient
{
	private boolean isRunning = true;
	private LocalConnectionHandler otherSide;
	private final Callback<String> messageCallback = new Callback<String>();
	private final Callback<AbstractEnforce> enforceCallback = new Callback<AbstractEnforce>();

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

	public Callback<AbstractEnforce> getEnforceCallback()
	{
		return enforceCallback;
	}

	public void sendRequest(AbstractRoomRequest request)
	{
		otherSide.getRequestCallback().call(request);
	}
}
