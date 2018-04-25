package progetto.network;

/**
 * Extension of AbstractServerRequest used to ask the server to send the room state again.
 */
final class FetchServerStateRequest extends AbstractServerRequest
{
	void execute(ServerState state, AbstractRoom room)
	{
		getAuthor().sendServerState(state.getView());
	}
}
