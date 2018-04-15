package progetto.network;

import progetto.utils.IObserver;

final class EnforceObserver implements IObserver<AbstractEnforce>
{
	private final ClientConnection clientConnection;

	EnforceObserver(ClientConnection con)
	{
		clientConnection = con;
	}

	public void notifyChange(AbstractEnforce ogg)
	{
		ogg.execute(clientConnection);
	}
}
