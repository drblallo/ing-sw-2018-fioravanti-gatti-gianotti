package progetto.network.localconnection;

import progetto.network.AbstractMessageExchangeTest;

public class LocalConnectionMessageExchangeTest extends AbstractMessageExchangeTest
{
	private static final LocalConnectionServerFactory s = new LocalConnectionServerFactory();
	private static final LocalConnectionClientFactory c = new LocalConnectionClientFactory(s);

	public LocalConnectionMessageExchangeTest()
	{
		super(s, c);
	}
}
