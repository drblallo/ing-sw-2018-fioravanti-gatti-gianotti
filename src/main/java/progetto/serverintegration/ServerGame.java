package progetto.serverintegration;

import progetto.clientintegration.AbstractGameSync;
import progetto.game.*;
import progetto.network.ISync;


public class ServerGame extends AbstractGameSync implements  ISync
{

	ServerGame()
	{
		clear();
	}

	public void clear() {
		super.clear();
		sendItem(new SetSeedAction(0));
	}

}
