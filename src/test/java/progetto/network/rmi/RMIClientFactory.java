package progetto.network.rmi;

import progetto.network.INetworkClient;
import progetto.network.INetworkClientFactory;

public class RMIClientFactory implements INetworkClientFactory {
	public INetworkClient getINetworkClient() {
		return new RMIClient("127.0.0.1");
	}
}
