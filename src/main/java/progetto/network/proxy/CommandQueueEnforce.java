package progetto.network.proxy;

import progetto.model.CommandQueueData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

/**
 * @author Massimo
 * This enforce is used to update the state of the command queue on the proxy model
 */
public class CommandQueueEnforce implements IEnforce
{
	private final CommandQueueData data;

	/**
	 * update the command queue
	 * @param c the client connection that received this enforce
	 */
	@Override
	public void execute(ClientConnection c) {
		c.getProxy().getCommandQueue().setData(data);
	}

	/**
	 * creates the enforce to be sent
	 * @param data the command queue data that will be dispatched to the client
	 */
	public CommandQueueEnforce(CommandQueueData data) {
		this.data = data;
	}
}
