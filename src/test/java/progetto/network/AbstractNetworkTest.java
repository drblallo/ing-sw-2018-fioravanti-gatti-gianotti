package progetto.network;

import org.junit.After;
import org.junit.Before;
import progetto.utils.Waiter;

public abstract class AbstractNetworkTest
{

	private INetworkClient client;
	private INetworkHandler handler;
	private INetworkModule module;
	private IEnforce enforce;

	public IEnforce getEnforce() {
		return enforce;
	}

	public IRoomRequest getRequest() {
		return request;
	}

	private IRoomRequest request;

	public INetworkClient getClient() {
		return client;
	}

	public INetworkHandler getHandler() {
		return handler;
	}

	public INetworkModule getModule() {
		return module;
	}

	private final INetworkClientFactory cFact;
	private final INetworkModuleFactory fFact;
	private Waiter johnatan = new Waiter();
	public static final int SHORT_WAIT = 100;
	public static final int MEDIUM_WAIT = 300;
	public static final int LONG_WAIT = 500;

	public String getMessage() {
		return message;
	}

	private String message;

	public AbstractNetworkTest(INetworkClientFactory c, INetworkModuleFactory f)
	{
		cFact = c;
		fFact = f;
	}

	public void wait(int milliseconds)
	{
		johnatan.wait(milliseconds);
	}

	@Before
	public void setUp()
	{
		module = fFact.getINetworkModule();
		module.start();
		module.getPlayerJoinedCallback().addObserver(ogg -> {
			handler = ogg;
			handler.getRequestCallback().addObserver(ogg1 -> request = ogg1);
		});
		wait(SHORT_WAIT);

		client = cFact.getINetworkClient();
		client.getMessageCallback().addObserver(ogg -> message = ogg);

		client.getEnforceCallback().addObserver(ogg -> enforce = ogg);
		wait(MEDIUM_WAIT);

	}


	@After
	public void tearDown()
	{
		client.disconnect(true);
		module.stop();
		wait(MEDIUM_WAIT);
	}

}
