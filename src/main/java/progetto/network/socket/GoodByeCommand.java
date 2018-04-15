package progetto.network.socket;

/**
 * Informs the other side socket that the connections is closing
 */
final class GoodByeCommand extends AbstractNetworkCommand<AbstractSocketManager>
{

	void execute(AbstractSocketManager mng) {
		mng.disconnect(false);
	}
}
