package progetto.network.socket.client;

import progetto.network.socket.AbstractNetworkCommand;

/**
 * check if the client is still in sync
 */
public class SyncCheckCommand extends AbstractNetworkCommand<SocketClient>
{
	private int state;
	private String h;

	public SyncCheckCommand(int stateIndex, String hash)
	{
		state = stateIndex;
		h = hash;
	}

	protected void execute(SocketClient mng) {
		mng.checkSync(state, h);
	}
}
