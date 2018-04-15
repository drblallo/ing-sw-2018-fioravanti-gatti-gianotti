package progetto.network.socket;

import progetto.network.NetworkServer;
import progetto.network.SyncFactoryStub;
import progetto.network.SyncStub;
import progetto.network.socket.client.SocketClient;
import progetto.network.socket.server.SocketServer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SocketServerTestStub {

	public SyncFactoryStub syncFactoryStub = new SyncFactoryStub();
	public NetworkServer networkServer = new NetworkServer(syncFactoryStub);
	public SocketServer socketServer = new SocketServer(8527, networkServer);
	public CountDownLatch latch = new CountDownLatch(1);
	public SocketClient socketClient;
	public SocketClient socketClient2;

	public void startServer()
	{
		networkServer.start();
	}

	public SocketServerTestStub()
	{
	}

	public void startClient()
	{
		socketClient= new SocketClient(new SyncStub(),"127.0.0.1",8527);
	}
	public void startClient2()
	{
		socketClient2= new SocketClient(new SyncStub(),"127.0.0.1",8527);
	}

	public void tearDown()
	{
		if (socketClient != null) {
			socketClient.disconnect(true);
			socketClient = null;
		}
		if (socketClient2 != null) {
			socketClient2.disconnect(true);
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
