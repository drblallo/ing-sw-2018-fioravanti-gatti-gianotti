package progetto.integration.server;

import progetto.controller.IGameController;
import progetto.integration.client.ClientGame;
import progetto.integration.client.IExecutibleGameFactory;
import progetto.network.NetworkServer;
import progetto.network.localconnection.LocalConnectionClient;
import progetto.network.localconnection.LocalConnectionModule;
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
	public IGameController getGame() {
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
