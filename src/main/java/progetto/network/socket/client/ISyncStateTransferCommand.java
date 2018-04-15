package progetto.network.socket.client;

import progetto.network.ISync;
import progetto.network.socket.AbstractNetworkCommand;

import java.util.ArrayList;

/**
 * Force the client to reset the state of the syncobject
 */
public class ISyncStateTransferCommand extends AbstractNetworkCommand<SocketClient>
{
	private ArrayList<String> allCommands = new ArrayList<String>();
	private String currHash;

	public ISyncStateTransferCommand(ISync sync)
	{
		for (int a = 0; a < sync.getStringCount(); a++)
			allCommands.add(sync.getString(a));
		currHash = sync.getHash(sync.getStringCount());
	}


	protected void execute(SocketClient mng) {
		mng.setFullState(allCommands);
		mng.checkSync(allCommands.size(), currHash);
	}
}
