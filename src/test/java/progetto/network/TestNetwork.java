package progetto.network;

import org.junit.Test;
import progetto.network.socket.ClientSocketFactory;
import progetto.network.socket.SocketClient;
import progetto.network.socket.SocketServer;
import progetto.network.socket.SocketServerFactory;
import progetto.utils.ObserverStub;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public abstract class TestNetwork extends SocketServerTestStub
{
	@Test
	public void testJoin()
	{
		startServer();
		wait(50);
		assertEquals(true, socketServer.isRunning());

		ClientConnection s = new ClientConnection(new SocketClient("127.0.0.1", 8527), new SyncStub());
		assertEquals(true, s.isRunning());
		assertEquals(true, socketServer.isRunning());
		s.disconnect();
		s.sendPrivateMessage("", 0);
		socketServer.stop();

		wait(50);
		assertEquals(false, s.isRunning());
		assertEquals(false, socketServer.isRunning());
		tearDown();

		assertEquals(null, networkServer.getConnectionsManager().getHandlerOfPlayer(-1));
	}

	public TestNetwork(INetworkModuleFactory m, INetworkClientFactory f)
	{
		super(m, f);
	}

	@Test
	public void testOpenAndClose()
	{
		startServer();
		networkServer.start();
		assertEquals(true, networkServer.isRunning());
		networkServer.stop();
		networkServer.stop();
		assertEquals(false, networkServer.isRunning());
		networkServer.start();
		assertEquals(true, networkServer.isRunning());
		tearDown();
	}

	@Test
	public void testAddModuleWhileRunning()
	{
		NetworkServer stock = new NetworkServer(new SyncFactoryStub());
		stock.start();
		SocketServer server = new SocketServer(8527);
		stock.addModules(server);
		stock.addModules(server);
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
		networkServer.broadcastMessage("testMessage");
		startServer();
		assertEquals(true, socketServer.isRunning());

		startClient(0);

		ClientConnection s = getClientConnection(0);

		wait(50);
		assertEquals(true, s.isRunning());
		ObserverStub<String> observerStub = new ObserverStub<String>();
		s.getMessageCallback().addObserver(observerStub);
		observerStub.currentVal = "NONE";
		assertEquals(true, s.isRunning());
		assertEquals(true, socketServer.isRunning());

		networkServer.broadcastMessage("testMessage");
		wait(50);
		assertEquals("testMessage", observerStub.currentVal);

		s.disconnect();
		wait(50);
		tearDown();
		wait(50);
	}

}
