package progetto.network;

import progetto.network.*;
import progetto.utils.Waiter;

public class SocketServerTestStub {

	public class ClientData
	{
		private ClientConnection clientConnection;

		public ClientConnection getClientConnection() {
			return clientConnection;
		}


		public INetworkClient getSocketClient() {
			return socketClient;
		}


		private INetworkClient socketClient;

		public void startClient(INetworkClient c)
		{
			socketClient = c;
			clientConnection = new ClientConnection(socketClient, new SyncStub());
		}

		public void tearDown()
		{
			if (clientConnection != null) {
				clientConnection.disconnect();
				clientConnection = null;
			}
		}

	}

	public interface INetworkModuleFactory
	{
		INetworkModule getINetworkModule();
	}

	public interface INetworkClientFactory
	{
		INetworkClient getINetworkClient();
	}

	public SyncFactoryStub syncFactoryStub = new SyncFactoryStub();
	public NetworkServer networkServer = new NetworkServer(syncFactoryStub);
	public INetworkModule socketServer;
	private ClientData[] clientData = new ClientData[2];
	private Waiter paul = new Waiter();

	private final INetworkModuleFactory moduleFactory;
	private final INetworkClientFactory clientFactiry;

	public SocketServerTestStub(INetworkModuleFactory moduleF, INetworkClientFactory clientF)
	{
		moduleFactory = moduleF;
		clientFactiry = clientF;
		socketServer = moduleF.getINetworkModule();
	}

	public void wait(int milliseconds)
	{
		paul.wait(milliseconds);
	}

	public void startServer()
	{
		networkServer.addModules(socketServer);
		networkServer.start();
	}

	public void startClient(int target)
	{
		clientData[target] = new ClientData();
		clientData[target].startClient(clientFactiry.getINetworkClient());
	}

	public INetworkClient getClientSocket(int target)
	{
		return clientData[target].getSocketClient();
	}

	public ClientConnection getClientConnection(int target)
	{
		return clientData[target].getClientConnection();
	}

	public void tearDown()
	{
		for (ClientData c : clientData)
			if (c != null)
				c.tearDown();
		networkServer.stop();
	}


}
