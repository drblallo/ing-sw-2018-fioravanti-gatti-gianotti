package progetto.network.localconnection;

import progetto.network.INetworkModule;
import progetto.network.INetworkModuleFactory;

public class LocalConnectionServerFactory implements INetworkModuleFactory {

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
