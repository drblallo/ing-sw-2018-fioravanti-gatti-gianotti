package progetto.network.rmi;

import progetto.network.AbstractStandardTest;

public class RMINetworkTest extends AbstractStandardTest {
	public RMINetworkTest() {
		super(new RMIServerFactory(), new RMIClientFactory());
		timeModifier = 1;
	}
}
