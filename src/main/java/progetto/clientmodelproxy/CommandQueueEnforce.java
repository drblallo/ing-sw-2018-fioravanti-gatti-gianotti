package progetto.clientmodelproxy;

import progetto.game.CommandQueueData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

public class CommandQueueEnforce implements IEnforce
{
	private final CommandQueueData data;

	@Override
	public void execute(ClientConnection c) {
		c.getProxy().getCommandQueue().setData(data);
	}

	public CommandQueueEnforce(CommandQueueData data) {
		this.data = data;
	}
}
