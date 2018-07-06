package progetto.network.rmi;

import progetto.network.AbstractSynchronizationTest;

public class RMISyncronizationTest extends AbstractSynchronizationTest {

	public RMISyncronizationTest() {
		super(new RMIServerFactory(), new RMIClientFactory());
		timeModifier = 1;
	}
}
