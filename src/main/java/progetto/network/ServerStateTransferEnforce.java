package progetto.network;

final class ServerStateTransferEnforce extends AbstractEnforce {

	private ServerStateView state;

	ServerStateTransferEnforce(ServerStateView state)
	{
		this.state = state;
	}

	public void execute(ClientConnection c) {
		c.setServerState(state);
	}
}
