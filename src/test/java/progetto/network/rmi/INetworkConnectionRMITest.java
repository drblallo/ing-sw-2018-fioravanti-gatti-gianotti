package progetto.network.rmi;

import progetto.network.AbstractINetowrkClientTest;

public class INetworkConnectionRMITest extends AbstractINetowrkClientTest{

	public INetworkConnectionRMITest() {
		super(new RMIClientFactory(), new RMIServerFactory());
	}
}
