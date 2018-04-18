package progetto.network;

final class RoomCreationRequest extends AbstractServerRequest
{
	private String name;

	RoomCreationRequest(String name) {
		this.name = name;
	}

	public void execute(ServerState state, AbstractRoom room)
	{
		state.createRoom(name);
	}
}
