package progetto.network.rmi;

import org.junit.Assert;
import org.junit.Test;
import progetto.Settings;
import progetto.network.INetworkHandler;
import progetto.utils.ObserverStub;
import progetto.utils.Waiter;

public class RMITest {

	@Test
	public void testCreation() {
		Waiter steve = new Waiter();
		RMIModule server = new RMIModule(Settings.getSettings().getRmiPort());
		server.start();
		steve.wait(500);
		RMIClient client = new RMIClient("127.0.0.1", Settings.getSettings().getRmiPort());
		steve.wait(100);
		Assert.assertEquals(true, client.isRunning());

		server.stop();
		steve.wait(50);
	}

	@Test
	public void testMultipleLogin() {
		Waiter steve = new Waiter();
		RMIModule server = new RMIModule(Settings.getSettings().getRmiPort());
		server.start();
		steve.wait(500);
		RMIClient client = new RMIClient("127.0.0.1", Settings.getSettings().getRmiPort());
		RMIClient client2 = new RMIClient("127.0.0.1", Settings.getSettings().getRmiPort());

		Assert.assertEquals(true, client.isRunning());
		Assert.assertEquals(true, client2.isRunning());

		client.disconnect(true);

		server.stop();
		steve.wait(50);
	}

	@Test
	public void testSendMessage() {
		Waiter steve = new Waiter();
		RMIModule server = new RMIModule(Settings.getSettings().getRmiPort());
		ObserverStub<INetworkHandler> obs = new ObserverStub<>();
		server.getPlayerJoinedCallback().addObserver(obs);
		obs.currentVal = null;
		server.start();
		steve.wait(500);
		RMIClient client = new RMIClient("127.0.0.1", Settings.getSettings().getRmiPort());
		steve.wait(100);

		Assert.assertNotNull(obs.currentVal);

		server.stop();
		steve.wait(50);
	}

	@Test
	public void testMissing()
	{
		RMIClient rmiClient = new RMIClient("127.0.0.1", Settings.getSettings().getRmiPort());
		Assert.assertEquals(false, rmiClient.isRunning());
		rmiClient.disconnect(true);
		rmiClient.sendRequest(null);
	}

	@Test
	public void testOpenTwice()
	{
		RMIModule server = new RMIModule(Settings.getSettings().getRmiPort());
		server.start();
		server.start();
		Assert.assertEquals(true, server.isRunning());
		server.stop();
		Assert.assertEquals(false, server.isRunning());

	}

	@Test
	public void testMultiplModules()
	{
		RMIModule server = new RMIModule(Settings.getSettings().getRmiPort());
		server.start();
		RMIModule server2 = new RMIModule(Settings.getSettings().getRmiPort());
		Assert.assertEquals(false, server2.isRunning());
		server2.start();
		server.stop();
		server2.stop();
	}

}
