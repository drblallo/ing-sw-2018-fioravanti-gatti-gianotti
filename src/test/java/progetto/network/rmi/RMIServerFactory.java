package progetto.network.rmi;

import progetto.Settings;
import progetto.network.INetworkModule;
import progetto.network.INetworkModuleFactory;

public class RMIServerFactory implements INetworkModuleFactory {

	public INetworkModule getINetworkModule() {
		return new RMIModule(Settings.getSettings().getRmiPort());
	}
}
