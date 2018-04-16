package progetto.network.socket;

/**
 * Informs the other side socket that the connections is closing
 */
final class GoodByeCommand extends AbstractNetworkCommand<AbstractSocket>
{

	void execute(AbstractSocket mng) {
		mng.disconnect(false);
	}
}
