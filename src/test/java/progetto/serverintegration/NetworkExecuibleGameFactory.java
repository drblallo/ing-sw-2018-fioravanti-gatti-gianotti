package progetto.serverintegration;

import progetto.clientintegration.ClientGame;
import progetto.clientintegration.IExecutibleGameFactory;
import progetto.game.IExecuibleGame;
import progetto.network.NetworkServer;
import progetto.network.localconnection.LocalConnectionClient;
import progetto.network.localconnection.LocalConnectionModule;
import progetto.serverintegration.ServerGame;
import progetto.utils.Waiter;

public class NetworkExecuibleGameFactory extends IExecutibleGameFactory {

	private Waiter paul = new Waiter();
	private NetworkServer server = new NetworkServer(new ServerGameFactory());
	private LocalConnectionModule module = new LocalConnectionModule();
	private ClientGame client;

	public NetworkExecuibleGameFactory()
	{
		server.addModules(module);
	}

	@Override
	public IExecuibleGame getGame() {
		server.stop();
		if (client != null)
			client.getClientConnection().disconnect();

		paul.wait(50);
		server.start();
		paul.wait(50);
		client = new ClientGame(new LocalConnectionClient(module));
		client.getClientConnection().createGame("room");
		client.getClientConnection().joinGame(0,"test");
		paul.wait(100);


		return client;
	}
}
