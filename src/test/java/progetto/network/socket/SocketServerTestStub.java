package progetto.network.socket;

import progetto.network.ClientConnection;
import progetto.network.NetworkServer;
import progetto.network.SyncFactoryStub;
import progetto.network.SyncStub;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SocketServerTestStub {

	public SyncFactoryStub syncFactoryStub = new SyncFactoryStub();
	public NetworkServer networkServer = new NetworkServer(syncFactoryStub);
	public SocketServer socketServer = new SocketServer(8527);
	public CountDownLatch latch = new CountDownLatch(1);
	public SocketClient scl;
	public ClientConnection socketClient;
	public ClientConnection socketClient2;

	public void startServer()
	{
		networkServer.addModules(socketServer);
		networkServer.start();
	}

	public SocketServerTestStub()
	{
	}

	public void startClient()
	{
		scl = new SocketClient("127.0.0.1", 8527);
		socketClient = new ClientConnection(scl, new SyncStub());
	}
	public void startClient2()
	{
		socketClient2 = new ClientConnection(new SocketClient("127.0.0.1",8527), new SyncStub());
	}

	public void tearDown()
	{
		if (socketClient != null) {
			socketClient.disconnect();
			socketClient = null;
		}
		if (socketClient2 != null) {
			socketClient2.disconnect();
			socketClient2 = null;
		}
		networkServer.stop();
	}

	public void wait(int milliseconds) {
		try {
			latch.await(milliseconds, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {

		}
	}

}
