package progetto.network.socket;

/**
 * Informs the other side socket that the connections is closing
 */
final class GoodByeCommand implements INetworkCommand<AbstractSocket> {

	/**
	 * tears down the connection on the other side
	 * @param mng the manger that will receive this object
	 */
	public void execute(AbstractSocket mng) {
		mng.disconnect(false);
	}
}
