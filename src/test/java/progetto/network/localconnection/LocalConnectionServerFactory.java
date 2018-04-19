package progetto.network.localconnection;

import progetto.network.INetworkModule;
import progetto.network.SocketServerTestStub;

public class LocalConnectionServerFactory implements SocketServerTestStub.INetworkModuleFactory {

	private LocalConnectionServer latest;

	public INetworkModule getINetworkModule() {
		latest = new LocalConnectionServer();
		return latest;
	}

	LocalConnectionServer getLatest()
	{
		return latest;
	}
}
