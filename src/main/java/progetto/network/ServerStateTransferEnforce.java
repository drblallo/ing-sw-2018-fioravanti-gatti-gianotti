package progetto.network;

final class ServerStateTransferEnforce implements IEnforce {

	private ServerStateView state;

	ServerStateTransferEnforce(ServerStateView state)
	{
		this.state = state;
	}

	public void execute(ClientConnection c) {
		c.setServerState(state);
	}
}
