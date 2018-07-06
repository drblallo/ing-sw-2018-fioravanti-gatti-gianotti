package progetto.network;

/**
 * Extension of AbstractServerRequest used to ask the server to send the room state again.
 * @author Massimo
 */
final class FetchServerStateRequest extends AbstractServerRequest
{
	/**
	 * Tries to send the full state of the server to the connection that requested this action.
	 * @param state the server state that received this request.
	 * @param room the room that received this request
	 */
	void execute(ServerState state, AbstractRoom room)
	{
		getAuthor().sendServerState(state.getView());
	}
}
