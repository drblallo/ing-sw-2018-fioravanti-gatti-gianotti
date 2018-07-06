package progetto;

import progetto.network.ISync;
import progetto.network.ISyncFactory;

/**
 * this is the class that provides the games to the server
 */
public class ServerGameFactory implements ISyncFactory
{

	@Override
	public ISync create() {
		return new ServerGame();
	}
}
