package progetto;

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
		sendString(new SetSeedAction(0));
	}

}
