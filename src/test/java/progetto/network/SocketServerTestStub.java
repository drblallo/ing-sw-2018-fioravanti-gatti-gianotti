package progetto.network;

import progetto.utils.Waiter;

public class SocketServerTestStub {

	public static final int SHORT_WAIT = 100;
	public static final int MEDIUM_WAIT = 300;
	public static final int LONG_WAIT = 500;
	public final SyncFactoryStub syncFactoryStub = new SyncFactoryStub();
	public final NetworkServer networkServer = new NetworkServer(syncFactoryStub);
	public final INetworkModule socketServer;
	private final ClientData[] clientData = new ClientData[2];
	private final Waiter paul = new Waiter();
	private final INetworkClientFactory clientFactiry;
	public int timeModifier = 1;
	public SocketServerTestStub(INetworkModuleFactory moduleF, INetworkClientFactory clientF) {
		clientFactiry = clientF;
		socketServer = moduleF.getINetworkModule();
	}

	public void wait(int milliseconds) {
		paul.wait(milliseconds * timeModifier);
	}

	public void startServer() {
		networkServer.addModules(socketServer);
		networkServer.start();
	}

	public void startClient(int target) {
		clientData[target] = new ClientData();
		clientData[target].startClient(clientFactiry.getINetworkClient());
	}

	public INetworkClient getClientSocket(int target) {
		return clientData[target].getSocketClient();
	}

	public ClientConnection getClientConnection(int target) {
		return clientData[target].getClientConnection();
	}

	public void tearDown() {
		for (ClientData c : clientData)
			if (c != null)
				c.tearDown();
		networkServer.stop();
	}

	public interface INetworkModuleFactory {
		INetworkModule getINetworkModule();
	}

	public interface INetworkClientFactory {
		INetworkClient getINetworkClient();
	}

	public class ClientData {
		private ClientConnection clientConnection;
		private INetworkClient socketClient;

		public ClientConnection getClientConnection() {
			return clientConnection;
		}

		public INetworkClient getSocketClient() {
			return socketClient;
		}

		public void startClient(INetworkClient c) {
			socketClient = c;
			clientConnection = new ClientConnection(socketClient, new SyncStub());
		}

		public void tearDown() {
			if (clientConnection != null) {
				clientConnection.disconnect();
				clientConnection = null;
			}
		}

	}


}
