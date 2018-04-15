package progetto.network.socket;

/**
 * Informs the other side socket that the connections is closing
 */
class GoodByeCommand extends AbstractNetworkCommand<AbstractSocketManager>
{

	protected void execute(AbstractSocketManager mng) {
		mng.disconnect(false);
	}
}
