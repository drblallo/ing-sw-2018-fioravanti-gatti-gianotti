package progetto.network;

final class FetchServerStateRequest extends AbstractServerRequest
{
	public void execute(ServerState state, AbstractRoom room)
	{
		getAuthor().sendServerState(state.getView());
	}
}
