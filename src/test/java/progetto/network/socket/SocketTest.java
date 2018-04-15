package progetto.network.socket;

import org.junit.Test;
import progetto.network.NetworkServer;
import progetto.network.SyncFactoryStub;
import progetto.network.SyncStub;
import progetto.network.socket.client.SocketClient;
import progetto.network.socket.server.SocketServer;
import progetto.utils.ObserverStub;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SocketTest
{
	@Test
	public void testJoin()
	{
		SocketServerTestStub stub = new SocketServerTestStub();
		stub.startServer();
		stub.wait(50);
		assertEquals(true, stub.socketServer.isRunning());

		SocketClient s = new SocketClient(new SyncStub(), "127.0.0.1", 8527);
		assertEquals(true, s.isRunning());
		assertEquals(true, stub.socketServer.isRunning());
		s.disconnect(true);
		s.sendPrivateMessage("", 0);
		stub.socketServer.stop();

		stub.wait(50);
		assertEquals(false, s.isRunning());
		assertEquals(false, stub.socketServer.isRunning());
		stub.tearDown();

		assertEquals(null, stub.networkServer.getConnectionsManager().getHandlerOfPlayer(-1));
	}

	@Test
	public void testAddModuleWhileRunning()
	{
		NetworkServer stock = new NetworkServer(new SyncFactoryStub());
		stock.start();
		SocketServer server = new SocketServer(8527, stock);
		assertEquals(true, server.isRunning());
		stock.stop();
		CountDownLatch latch = new CountDownLatch(1);
		try {
			latch.await(50, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
		}

	}

	@Test
	public void testSendMessage()
	{
		SocketServerTestStub stub = new SocketServerTestStub();
		stub.networkServer.broadcastMessage("testMessage");
		stub.startServer();
		assertEquals(true, stub.socketServer.isRunning());

		SocketClient s = new SocketClient(new SyncStub(), "127.0.0.1", 8527);

		stub.wait(50);
		assertEquals(true, s.isRunning());
		ObserverStub<String> observerStub = new ObserverStub<String>();
		s.getMessageCallback().addObserver(observerStub);
		observerStub.currentVal = "NONE";
		assertEquals(true, s.isRunning());
		assertEquals(true, stub.socketServer.isRunning());

		stub.networkServer.broadcastMessage("testMessage");
		stub.wait(50);
		assertEquals("testMessage", observerStub.currentVal);

		s.disconnect(true);
		stub.wait(50);
		stub.tearDown();
		stub.wait(50);
	}

}
