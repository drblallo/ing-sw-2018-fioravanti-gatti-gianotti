package progetto.network;

/**
 * Extension of AbstractServerRequest used to ask to the server to resend the player id.
 * @author Massimo
 */
final class FetchMyIDRequest extends AbstractServerRequest
{
	/**
	 * tries to sent the player id to the relative client
	 */
	void execute(ServerState state, AbstractRoom room)
	{
		getAuthor().sendID();
	}
}
