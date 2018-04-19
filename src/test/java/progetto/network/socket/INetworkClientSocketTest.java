package progetto.network.socket;


import org.junit.Assert;
import org.junit.Test;
import progetto.network.AbstractINetowrkClientTest;
import progetto.network.NetworkSettings;

public class INetworkClientSocketTest extends AbstractINetowrkClientTest{

	public INetworkClientSocketTest() {
		super(new ClientSocketFactory(), new SocketServerFactory());
	}


	@Test
	public void testTimeout()
	{
		getHandler().disconnect(false);
		wait(NetworkSettings.DEFAULT_TIME_TO_LIVE * (NetworkSettings.MAX_TIME_TO_LIVE_SKIPPED  - 1));
		Assert.assertTrue(getClient().isRunning());
		wait(NetworkSettings.DEFAULT_TIME_TO_LIVE * 2);
		Assert.assertFalse(getClient().isRunning());
	}
}
