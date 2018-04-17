package progetto.network.rmi;

import progetto.network.NetworkTest;

public class RMINetworkTest extends NetworkTest {
	public RMINetworkTest() {
		super(new RMIServerFactory(), new RMIClientFactory());
		timeModifier = 1;
	}
}
