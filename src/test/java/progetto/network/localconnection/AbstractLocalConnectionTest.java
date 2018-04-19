package progetto.network.localconnection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import progetto.network.AbstractEnforce;
import progetto.network.ClientConnection;
import progetto.network.INetworkClientHandler;
import progetto.utils.IObserver;

import static org.junit.Assert.assertEquals;

public abstract class AbstractLocalConnectionTest
{
	class EnforceStub extends AbstractEnforce
	{
		public void execute(ClientConnection c)
		{
			//just a stub
		}
	}

	protected LocalConnectionClient client;
	protected static final LocalConnectionServer server = new LocalConnectionServer();
	protected static INetworkClientHandler handler;
	protected AbstractEnforce lastEnforce;
	protected boolean called = false;

	@BeforeClass
	public static void beforeClass()
	{
		server.start();
		server.getPlayerJoinedCallback().addObserver(new IObserver<INetworkClientHandler>()
		{
			public void notifyChange(INetworkClientHandler ogg)
			{
				handler = ogg;
			}
		});
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
