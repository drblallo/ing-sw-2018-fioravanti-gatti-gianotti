package progetto.network;

public class SyncFactoryStub implements ISyncFactory {

	public ISync create()
	{
		return new SyncStub();
	}
}
