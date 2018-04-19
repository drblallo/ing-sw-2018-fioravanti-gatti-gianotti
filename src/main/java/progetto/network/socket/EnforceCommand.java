package progetto.network.socket;

import progetto.network.IEnforce;

/**
 * allows to transport a enforce across the network
 */
final class EnforceCommand implements INetworkCommand<SocketClient> {

	private IEnforce iEnforce;

	EnforceCommand(IEnforce enforceCommand) {
		this.iEnforce = enforceCommand;
	}

	public void execute(SocketClient mng) {
		mng.getEnforceCallback().call(iEnforce);
	}
}
