package progetto.network;

import progetto.network.connectionstate.ServerState;

final class ServerStateTransferEnforce extends AbstractEnforce {

	private ServerState state;

	public void execute(ClientConnection c) {
		c.setServerState(state);
	}

	ServerStateTransferEnforce(ServerState state) {
		this.state = state;
	}
}
