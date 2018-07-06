package progetto.network.localconnection;

import org.junit.Assert;
import org.junit.Test;

public class LocalConnectionHandlerTest extends AbstractLocalConnectionTest
{

	@Test
	public void testDisconnectGracefully()
	{
		handler.disconnect(true);
		Assert.assertEquals(false, client.isRunning());
		Assert.assertFalse(handler.isRunning());
	}

	@Test
	public void testDisconnectUngracefully()
	{
		handler.disconnect(false);
		Assert.assertEquals(true, client.isRunning());
		Assert.assertFalse(handler.isRunning());
	}

}
