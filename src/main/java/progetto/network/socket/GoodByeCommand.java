package progetto.network.socket;

/**
 * Informs the other side socket that the connections is closing
 */
final class GoodByeCommand implements INetworkCommand<AbstractSocket> {

	public void execute(AbstractSocket mng) {
		mng.disconnect(false);
	}
}
