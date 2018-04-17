package progetto.network;

import org.junit.Test;
import progetto.network.socket.SocketServer;
import progetto.utils.ObserverStub;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public abstract class NetworkTest extends SocketServerTestStub {
	public NetworkTest(INetworkModuleFactory m, INetworkClientFactory f) {
		super(m, f);
	}

	@Test
	public void testJoin() {
		wait(MEDIUM_WAIT);
		startServer();
		wait(MEDIUM_WAIT);
		startClient(0);
		wait(MEDIUM_WAIT);
		assertEquals(true, socketServer.isRunning());
		ClientConnection s = getClientConnection(0);
		assertEquals(true, s.isRunning());
		assertEquals(true, socketServer.isRunning());
		s.disconnect();
		s.sendPrivateMessage("", 0);
		socketServer.stop();

		wait(SHORT_WAIT);
		assertEquals(false, s.isRunning());
		assertEquals(false, socketServer.isRunning());
		tearDown();
		wait(SHORT_WAIT);
		assertEquals(null, networkServer.getServerStateClone().getRoomOfPlayer(-1));
	}

	@Test
	public void testOpenAndClose() {
		wait(MEDIUM_WAIT);
		startServer();
		startServer();
		networkServer.start();
		assertEquals(true, networkServer.isRunning());
		networkServer.stop();
		networkServer.stop();
		assertEquals(false, networkServer.isRunning());
		networkServer.start();
		assertEquals(true, networkServer.isRunning());
		tearDown();
		wait(MEDIUM_WAIT);
	}

	@Test
	public void testAddModuleWhileRunning() {
		wait(MEDIUM_WAIT);
		NetworkServer stock = new NetworkServer(new SyncFactoryStub());
		stock.start();
		SocketServer server = new SocketServer(8527);
		stock.addModules(server);
		stock.addModules(server);
		assertEquals(true, server.isRunning());
		stock.stop();
		CountDownLatch latch = new CountDownLatch(1);
		try {
			latch.await(SHORT_WAIT, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
		}
		wait(MEDIUM_WAIT);

	}

	@Test
	public void testSendMessage() {
		wait(MEDIUM_WAIT);
		networkServer.broadcastMessage("testMessage");
		startServer();
		wait(LONG_WAIT);
		assertEquals(true, socketServer.isRunning());

		startClient(0);

		ClientConnection s = getClientConnection(0);

		wait(SHORT_WAIT);
		assertEquals(true, s.isRunning());
		ObserverStub<String> observerStub = new ObserverStub<String>();
		s.getMessageCallback().addObserver(observerStub);
		observerStub.currentVal = "NONE";
		assertEquals(true, s.isRunning());
		assertEquals(true, socketServer.isRunning());

		networkServer.broadcastMessage("testMessage");
		wait(SHORT_WAIT);
		assertEquals("testMessage", observerStub.currentVal);

		s.disconnect();
		wait(SHORT_WAIT);
		tearDown();
		wait(SHORT_WAIT);
	}

	@Test
	public void testAutoDisconnect()
	{

		startServer();
		wait(LONG_WAIT);
		assertEquals(true, socketServer.isRunning());

		startClient(0);
		networkServer.stop();
	}
}
