package progetto.network;

import java.util.List;

final class SyncStateEnforce extends AbstractEnforce {
	private List<String> allCommands;
	private String currHash;
	SyncStateEnforce(ISync ogg) {

		this.allCommands = ogg.getAllString();
		this.currHash = ogg.getHash(ogg.getStringCount());

	}

	public void execute(ClientConnection c) {
		c.setFullState(allCommands);
		c.checkSync(allCommands.size(), currHash);
	}
}
