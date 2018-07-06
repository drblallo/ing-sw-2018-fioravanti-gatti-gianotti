package progetto.network.localconnection;

import progetto.network.AbstractINetowrkClientTest;

public class INetworkClientLocalConnectionTest extends AbstractINetowrkClientTest{

	private static LocalConnectionServerFactory factory = new LocalConnectionServerFactory();
	private static LocalConnectionClientFactory clientFactory = new LocalConnectionClientFactory(factory);

	public INetworkClientLocalConnectionTest() {
		super(clientFactory, factory);
	}
}
