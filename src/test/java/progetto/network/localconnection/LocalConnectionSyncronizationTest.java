package progetto.network.localconnection;

import progetto.network.AbstractSynchronizationTest;

public class LocalConnectionSyncronizationTest extends AbstractSynchronizationTest
{
	private static final LocalConnectionServerFactory s = new LocalConnectionServerFactory();
	private static final LocalConnectionClientFactory c = new LocalConnectionClientFactory(s);

	public LocalConnectionSyncronizationTest()
	{
		super(s, c);
	}
}
