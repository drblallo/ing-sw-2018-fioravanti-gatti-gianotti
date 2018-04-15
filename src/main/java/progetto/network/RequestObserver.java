package progetto.network;

import progetto.utils.IObserver;

final class RequestObserver implements IObserver<AbstractRequest>{
	RequestObserver(ServerConnection connection, ConnectionsManager manager) {
		this.connection = connection;
		this.manager = manager;
	}

	private ServerConnection connection;
	private ConnectionsManager manager;

	public void notifyChange(AbstractRequest ogg) {
		ogg.execute(manager, connection);
	}
}
