package progetto.network.socket.client;

import progetto.network.socket.AbstractNetworkCommand;

/**
 * sends the new command that the client must process.
 */
public class SyncClientCommand extends AbstractNetworkCommand<SocketClient>
{
	private String command;

	public SyncClientCommand(String syncCommand)
	{
		command = syncCommand;
	}

	protected void execute(SocketClient mng) {
		mng.processSyncCommand(command);
	}
}
