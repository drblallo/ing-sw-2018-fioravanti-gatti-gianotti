package progetto.network.localconnection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;
import progetto.network.INetworkHandler;

import static org.junit.Assert.assertEquals;

public abstract class AbstractLocalConnectionTest
{
	class EnforceStub implements IEnforce
	{
		public void execute(ClientConnection c)
		{
			//just a stub
		}
	}

	protected LocalConnectionClient client;
	protected static final LocalConnectionModule server = new LocalConnectionModule();
	protected static INetworkHandler handler;
	protected IEnforce lastEnforce;
	protected boolean called = false;

	@BeforeClass
	public static void beforeClass()
	{
		server.start();
		server.getPlayerJoinedCallback().addObserver(ogg -> handler = ogg);
	}

	@Before
	public void setUp()
	{
		client = new LocalConnectionClient(server);
		assertEquals(handler, client.getOtherSide());
	}

	@After
	public void tearDown()
	{
		client.disconnect(true);
	}

	@AfterClass
	public static void tearDownClass()
	{
		server.stop();
	}
}
