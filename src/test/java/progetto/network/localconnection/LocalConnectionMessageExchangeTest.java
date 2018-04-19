package progetto.network.localconnection;

import progetto.network.MessageExcangeTest;

public class LocalConnectionMessageExchangeTest extends MessageExcangeTest
{
	private static final LocalConnectionServerFactory s = new LocalConnectionServerFactory();
	private static final LocalConnectionClientFactory c = new LocalConnectionClientFactory(s);

	public LocalConnectionMessageExchangeTest()
	{
		super(s, c);
	}
}
