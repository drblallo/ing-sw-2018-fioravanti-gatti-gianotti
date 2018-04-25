package progetto.network;

/**
 * Extension of AbstractServerRequest used to ask to the server to resend the player id.
 */
final class FetchMyIDRequest extends AbstractServerRequest
{
	void execute(ServerState state, AbstractRoom room)
	{
		getAuthor().sendID();
	}
}
