package progetto.integration.server;

import progetto.network.ISync;
import progetto.network.ISyncFactory;

public class ServerGameFactory implements ISyncFactory
{

	@Override
	public ISync create() {
		return new ServerGame();
	}
}
