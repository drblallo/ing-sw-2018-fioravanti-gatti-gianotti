package progetto.network.socket;

import progetto.network.AbstractEnforce;

/**
 * allows to transport a enforce across the network
 */
final class EnforceCommand extends AbstractNetworkCommand<SocketClient> {

	private AbstractEnforce abstractEnforce;

	EnforceCommand(AbstractEnforce enforceCommand) {
		this.abstractEnforce = enforceCommand;
	}

	void execute(SocketClient mng) {
		mng.getEnforceCallback().call(abstractEnforce);
	}
}
