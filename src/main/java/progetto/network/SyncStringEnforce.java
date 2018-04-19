package progetto.network;

final class SyncStringEnforce implements IEnforce {
	private final String action;

	SyncStringEnforce(String action) {
		this.action = action;
	}

	public void execute(ClientConnection c) {
		c.processSyncCommand(action);
	}
}
