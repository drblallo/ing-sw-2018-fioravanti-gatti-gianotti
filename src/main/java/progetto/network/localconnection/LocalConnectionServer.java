package progetto.network.localconnection;

import progetto.network.INetworkClientHandler;
import progetto.network.INetworkModule;
import progetto.utils.Callback;

public class LocalConnectionServer implements INetworkModule
{
	private boolean isRunning = false;
	private final Callback<INetworkClientHandler> playerJoinedCallback = new Callback<INetworkClientHandler>();

	public void stop()
	{
		isRunning = false;
	}

	public void start()
	{
		isRunning = true;
	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public Callback<INetworkClientHandler> getPlayerJoinedCallback()
	{
		return playerJoinedCallback;
	}

	void connect(LocalConnectionClient c)
	{
		LocalConnectionHandler h = new LocalConnectionHandler(c);
		c.setConnection(h);
		playerJoinedCallback.call(h);
	}
}
