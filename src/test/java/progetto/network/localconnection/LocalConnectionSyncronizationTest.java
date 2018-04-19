package progetto.network.localconnection;

import progetto.network.SyncronizationTest;

public class LocalConnectionSyncronizationTest extends SyncronizationTest
{
	private static final LocalConnectionServerFactory s = new LocalConnectionServerFactory();
	private static final LocalConnectionClientFactory c = new LocalConnectionClientFactory(s);

	public LocalConnectionSyncronizationTest()
	{
		super(s, c);
	}
}
