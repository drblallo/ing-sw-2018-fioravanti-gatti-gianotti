package progetto.network.localconnection;

import org.junit.*;
import progetto.network.*;
import progetto.utils.IObserver;
import progetto.utils.ObserverStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LocalConnectionClientTest extends AbstractLocalConnectionTest
{


	@Test
	public void testIsRunning()
	{
		assertEquals(true, client.isRunning());
		client.disconnect(true);
		assertEquals(false, client.isRunning());
	}

	@Test
	public void testGetMessage()
	{
		ObserverStub<String> stub = new ObserverStub<String>();
		client.getMessageCallback().addObserver(stub);
		stub.currentVal = "NONE";
		handler.sendMessage("MESSAGE");
		assertEquals("MESSAGE", stub.currentVal);
	}


	@Test
	public void testSendEnforce()
	{
		client.getEnforceCallback().addObserver(ogg -> lastEnforce = ogg);
		handler.sendEnforce(new EnforceStub());
		assertEquals(EnforceStub.class, lastEnforce.getClass());
	}

	@Test
	public void testSendRequest()
	{
		handler.getRequestCallback().addObserver(ogg -> called = true);
		client.sendRequest(null);
		assertEquals(true, called);
	}

	@Test
	public void testDisconnect()
	{
		client.disconnect(false);
		assertFalse(client.isRunning());
	}

}
