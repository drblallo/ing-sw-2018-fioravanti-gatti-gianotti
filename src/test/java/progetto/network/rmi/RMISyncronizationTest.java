package progetto.network.rmi;

import progetto.network.SyncronizationTest;

public class RMISyncronizationTest extends SyncronizationTest {

	public RMISyncronizationTest() {
		super(new RMIServerFactory(), new RMIClientFactory());
		timeModifier = 1;
	}
}
