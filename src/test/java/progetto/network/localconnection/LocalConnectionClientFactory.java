package progetto.network.localconnection;

import progetto.network.INetworkClient;
import progetto.network.INetworkClientFactory;

public class LocalConnectionClientFactory implements INetworkClientFactory
{
	private final LocalConnectionServerFactory factory;

	LocalConnectionClientFactory(LocalConnectionServerFactory factory)
	{
		this.factory = factory;
	}

	public INetworkClient getINetworkClient()
	{
		return new LocalConnectionClient(factory.getLatest());
	}
}