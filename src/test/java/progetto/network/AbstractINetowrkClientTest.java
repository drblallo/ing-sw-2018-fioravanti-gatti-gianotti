package progetto.network;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractINetowrkClientTest extends AbstractNetworkTest
{
	public AbstractINetowrkClientTest(INetworkClientFactory c, INetworkModuleFactory f) {
		super(c, f);
	}

	@Test
	public void testDisconnectGracefully()
	{
		Assert.assertTrue(getClient().isRunning());
		getClient().disconnect(true);
		wait(MEDIUM_WAIT);
		Assert.assertFalse(getClient().isRunning());
		getClient().disconnect(true);
		wait(MEDIUM_WAIT);
		Assert.assertFalse(getClient().isRunning());
	}

	@Test
	public void testDisconnectUngracefully()
	{
		Assert.assertTrue(getClient().isRunning());
		getClient().disconnect(false);
		wait(MEDIUM_WAIT);
		Assert.assertFalse(getClient().isRunning());
		getClient().disconnect(true);
		wait(MEDIUM_WAIT);
		Assert.assertFalse(getClient().isRunning());
	}

	@Test
	public void testLoseConnectionGracefully()
	{
		Assert.assertTrue(getClient().isRunning());
		getHandler().disconnect(true);
		wait(MEDIUM_WAIT);
		Assert.assertFalse(getClient().isRunning());
		getHandler().disconnect(true);
		wait(MEDIUM_WAIT);
		Assert.assertFalse(getClient().isRunning());
	}

	@Test
	public void testLoseConnectionUngracefully()
	{
		Assert.assertTrue(getClient().isRunning());
		getHandler().disconnect(false);
		wait(SHORT_WAIT);
		getHandler().disconnect(false);
		wait(SHORT_WAIT);
	}

	@Test
	public void testSendMessage()
	{
		getHandler().sendMessage("TEST");
		wait(LONG_WAIT);
		Assert.assertEquals("TEST", getMessage());
	}

	@Test
	public void testSendRequest()
	{
		getClient().sendRequest(new SendPrivateMessageRoomRequest("message", -1));
		wait(LONG_WAIT);
		Assert.assertEquals(SendPrivateMessageRoomRequest.class, getRequest().getClass());
		getClient().sendRequest(null);
	}

	@Test
	public void testReceiveEnforce()
	{
		getHandler().sendEnforce(new SetPlayerInfoEnforce(-1));
		wait(LONG_WAIT);
		Assert.assertEquals(SetPlayerInfoEnforce.class, getClient().getEnforceList().poll().getClass());
	}

}
