package progetto.network;

/**
 * This enforce is sent to the client when the server wishes to notify that
 * the server state is changed.
 *
 * Server state updates are not provided by default and must be requested by the client.
 * @author Massimo
 */
final class ServerStateTransferEnforce implements IEnforce {

	private ServerStateView state;

	/**
	 * builds a new enforce
	 * @param state the server state that must be sent
	 */
	ServerStateTransferEnforce(ServerStateView state)
	{
		this.state = state;
	}

	/**
	 * set the client knowledge of the server state
	 * @param c the client connection that should receive the update
	 */
	public void execute(ClientConnection c) {
		c.setServerState(state);
	}
}
