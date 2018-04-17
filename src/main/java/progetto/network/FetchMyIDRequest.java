package progetto.network;

class FetchMyIDRequest extends AbstractRequest {
	public void execute(ConnectionsManager manager, ServerConnection serverConnection) {
		serverConnection.sendID();
	}
}
