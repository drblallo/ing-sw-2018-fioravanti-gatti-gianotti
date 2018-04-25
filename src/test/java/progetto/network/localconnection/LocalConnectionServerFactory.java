package progetto.network.localconnection;

import progetto.network.INetworkModule;
import progetto.network.INetworkModuleFactory;

public class LocalConnectionServerFactory implements INetworkModuleFactory {

	private LocalConnectionModule latest;

	public INetworkModule getINetworkModule() {
		latest = new LocalConnectionModule();
		return latest;
	}

	LocalConnectionModule getLatest()
	{
		return latest;
	}
}
