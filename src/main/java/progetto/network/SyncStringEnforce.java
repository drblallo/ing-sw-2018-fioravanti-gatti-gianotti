package progetto.network;

/**
 * Forces the player to append a sync string command to his sync object
 */
final class SyncStringEnforce implements IEnforce {
	private final String action;

	/**
	 *
	 * @param action the action that must be sent across the network
	 */
	SyncStringEnforce(String action) {
		this.action = action;
	}

	/**
	 * forces the player to update his sync object
	 * @param c the connection that received this enforce.
	 */
	public void execute(ClientConnection c) {
		c.processSyncCommand(action);
	}
}
