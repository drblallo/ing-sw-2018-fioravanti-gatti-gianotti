package progetto.network.localconnection;

import progetto.network.AbstractStandardTest;

public class LocalConnectionNetworkTest extends AbstractStandardTest
{
	private static final LocalConnectionServerFactory s = new LocalConnectionServerFactory();
	private static final LocalConnectionClientFactory c = new LocalConnectionClientFactory(s);

	public LocalConnectionNetworkTest()
	{
		super(s, c);
	}
}
