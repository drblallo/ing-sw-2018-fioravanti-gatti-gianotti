package progetto.network;

import java.util.List;

/**
 * forces the player ro reset the sync object state and to add all commands
 */
final class SyncStateEnforce implements IEnforce {
	private List<String> allCommands;
	private String currHash;

	/**
	 * @param ogg the sync object on the server side that will be used to synchronize the server
	 */
	SyncStateEnforce(ISync ogg) {
		this.allCommands = ogg.getAllString();
		this.currHash = ogg.getHash(ogg.getStringCount());

	}


	/**
	 * Forces the client to reset the state and to check synchronization
	 * @param c the client that received this enforce.
	 */
	public void execute(ClientConnection c) {
		c.setFullState(allCommands);
		c.checkSync(allCommands.size(), currHash);
	}
}
