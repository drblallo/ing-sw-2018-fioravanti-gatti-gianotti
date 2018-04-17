package progetto.network.socket;

import org.junit.Test;
import progetto.network.ClientConnection;
import progetto.network.SyncStub;
import progetto.utils.ObserverStub;
import progetto.utils.Waiter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SocketClientTest {

	@Test
	public void testConnectFailure() {
		ClientConnection c = new ClientConnection(new SocketClient("127.0.0.1", 8527), new SyncStub());
		c.sendPrivateMessage("test", 0);
		CountDownLatch latch = new CountDownLatch(1);
		try {
			latch.await(50, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {

		}
		assertEquals(false, c.isRunning());
		c.disconnect();
		try {
			latch.await(50, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {

		}
	}

	@Test
	public void testDisconnectCallback() {
		SocketServer server = new SocketServer(8527);
		Waiter simon = new Waiter();
		server.start();
		simon.wait(50);
		ObserverStub<AbstractSocket> obs = new ObserverStub<AbstractSocket>();
		SocketClient c = new SocketClient("127.0.0.1", 8527);
		obs.currentVal = null;
		c.getConnectionClosedCallback().addObserver(obs);

		c.disconnect(true);
		server.stop();
		simon.wait(100);
		assertNotNull(obs.currentVal);
		assertEquals(false, obs.currentVal.isRunning());
	}


}
