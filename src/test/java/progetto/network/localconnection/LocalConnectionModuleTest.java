package progetto.network.localconnection;

import org.junit.Assert;
import org.junit.Test;

public class LocalConnectionModuleTest extends AbstractLocalConnectionTest
{

	@Test
	public void testStartStop()
	{
		server.start();
		Assert.assertEquals(true, server.isRunning());
		server.start();
		Assert.assertEquals(true, server.isRunning());
		server.stop();
		Assert.assertEquals(false, server.isRunning());
		server.stop();
		Assert.assertEquals(false, server.isRunning());
		server.start();
		Assert.assertEquals(true, server.isRunning());
	}


	@Test
	public void testPlayerJoined()
	{
		Assert.assertNotNull(handler);
	}
}
