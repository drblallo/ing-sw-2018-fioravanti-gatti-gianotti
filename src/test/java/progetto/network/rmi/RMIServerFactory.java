package progetto.network.rmi;

import progetto.network.INetworkModule;
import progetto.network.SocketServerTestStub;

public class RMIServerFactory implements SocketServerTestStub.INetworkModuleFactory {

	public INetworkModule getINetworkModule() {
		return new RMIServer();
	}
}
