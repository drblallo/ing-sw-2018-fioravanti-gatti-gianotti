package progetto.network;

final class RoomCreationRequest extends AbstractRequest{

	RoomCreationRequest(String name) {
		this.name = name;
	}

	private String name;

	public void execute(ConnectionsManager manager, ServerConnection serverConnection) {
		manager.getServerState().createRoom(name);
	}
}
