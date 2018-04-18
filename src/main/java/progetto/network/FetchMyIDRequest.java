package progetto.network;

final class FetchMyIDRequest extends AbstractServerRequest
{
	public void execute(ServerState state, AbstractRoom room)
	{
		getAuthor().sendID();
	}
}
