package progetto.network;

final class FetchServerStateRequest extends AbstractRequest {
	public void execute(ConnectionsManager manager, ServerConnection serverConnection) {
		serverConnection.sendServerState(manager.getServerState());
	}
}
