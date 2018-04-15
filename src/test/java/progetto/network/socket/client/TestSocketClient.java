package progetto.network.socket.client;

import junit.framework.TestCase;
import progetto.network.ClientConnection;
import progetto.network.SyncStub;
import progetto.network.socket.SocketClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestSocketClient extends TestCase
{

	public void testConnectFailure()
	{
		ClientConnection c = new ClientConnection(new SocketClient("127.0.0.1", 8527), new SyncStub());
		c.sendPrivateMessage("test", 0);
		CountDownLatch latch = new CountDownLatch(1);
		try
		{
			latch.await(50, TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException e)
		{

		}
		assertEquals(c.isRunning(), false);
		c.disconnect();
		try
		{
			latch.await(50, TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException e)
		{

		}
	}
}
