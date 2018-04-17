package progetto.network.socket;

import progetto.network.INetworkClient;
import progetto.network.SocketServerTestStub;

public class ClientSocketFactory implements SocketServerTestStub.INetworkClientFactory {

	public INetworkClient getINetworkClient() {
		return new SocketClient("127.0.0.1", 8527);
	}
}
