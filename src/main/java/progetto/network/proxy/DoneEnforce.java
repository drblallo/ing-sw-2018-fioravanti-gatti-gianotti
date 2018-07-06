package progetto.network.proxy;

import progetto.network.ClientConnection;
import progetto.network.IEnforce;

/**
 * @author Massimo
 * called every time a action is fully resolved so that the client can be aware of the end of it
 */
public class DoneEnforce implements IEnforce
{

	/**
	 *
	 * @param c the client connection that received this enforce
	 */
	@Override
	public void execute(ClientConnection c) {
		c.onActionResolved();
	}
}
