package progetto.network.proxy;

import progetto.network.ClientConnection;
import progetto.network.IEnforce;

public class DoneEnforce implements IEnforce
{

	@Override
	public void execute(ClientConnection c) {
		c.onActionResolved();
	}
}
