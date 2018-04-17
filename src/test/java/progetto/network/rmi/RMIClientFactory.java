package progetto.network.rmi;

import progetto.network.INetworkClient;
import progetto.network.SocketServerTestStub;

public class RMIClientFactory implements SocketServerTestStub.INetworkClientFactory {
	public INetworkClient getINetworkClient() {
		return new RMIClient("127.0.0.1");
	}
}
