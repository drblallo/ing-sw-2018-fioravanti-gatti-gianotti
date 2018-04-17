package progetto.network;

final class RoomCreationRequest extends AbstractRequest {

	private String name;

	RoomCreationRequest(String name) {
		this.name = name;
	}

	public void execute(ConnectionsManager manager, ServerConnection serverConnection) {
		manager.getServerState().createRoom(name);
	}
}
