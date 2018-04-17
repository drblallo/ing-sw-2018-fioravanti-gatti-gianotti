package progetto.network.socket;

import progetto.network.INetworkModule;
import progetto.network.SocketServerTestStub;

public class SocketServerFactory implements SocketServerTestStub.INetworkModuleFactory {

	public INetworkModule getINetworkModule() {
		return new SocketServer(8527);
	}
}
