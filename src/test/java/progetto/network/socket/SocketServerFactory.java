package progetto.network.socket;

import progetto.network.INetworkModule;
import progetto.network.INetworkModuleFactory;

public class SocketServerFactory implements INetworkModuleFactory {

	public INetworkModule getINetworkModule() {
		return new SocketServer(8527);
	}
}
