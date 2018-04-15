package progetto.network;

import java.util.List;

final class SyncStateEnforce extends AbstractEnforce
{
	SyncStateEnforce(ISync ogg) {

		this.allCommands = ogg.getAllString();
		this.currHash = ogg.getHash(ogg.getStringCount());

	}

	private List<String> allCommands;
	private String currHash;

	public void execute(ClientConnection c) {
		c.setFullState(allCommands);
		c.checkSync(allCommands.size(), currHash);
	}
}
