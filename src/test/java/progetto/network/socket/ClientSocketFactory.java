package progetto.network.socket;

import progetto.network.INetworkClient;
import progetto.network.INetworkClientFactory;

public class ClientSocketFactory implements INetworkClientFactory {

	public INetworkClient getINetworkClient() {
		return new SocketClient("127.0.0.1", 8527);
	}
}
