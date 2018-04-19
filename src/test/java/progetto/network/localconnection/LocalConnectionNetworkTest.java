package progetto.network.localconnection;

import progetto.network.NetworkTest;

public class LocalConnectionNetworkTest extends NetworkTest
{
	private static final LocalConnectionServerFactory s = new LocalConnectionServerFactory();
	private static final LocalConnectionClientFactory c = new LocalConnectionClientFactory(s);

	public LocalConnectionNetworkTest()
	{
		super(s, c);
	}
}
