package progetto.network.socket.client;

import progetto.network.connectionstate.ServerState;
import progetto.network.socket.AbstractNetworkCommand;

/**
 * Sends the state of the server to the client
 */
public class ServerStateTransferCommand extends AbstractNetworkCommand<SocketClient>
{

	private ServerState state;

	public ServerStateTransferCommand(ServerState st)
	{
		state = st.deepCopy();
	}

	protected void execute(SocketClient mng) {
		mng.setServerState(state);
	}
}
