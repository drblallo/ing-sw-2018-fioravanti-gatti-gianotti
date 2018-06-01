package progetto.network.socket;

import progetto.network.IEnforce;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * allows to transport a enforce across the network
 */
final class EnforceCommand implements INetworkCommand<SocketClient> {

	private IEnforce iEnforce;
	private static final Logger LOGGER = Logger.getLogger(EnforceCommand.class.getName());

	EnforceCommand(IEnforce enforceCommand) {
		this.iEnforce = enforceCommand;
	}

	public void execute(SocketClient mng) {
		LOGGER.log(Level.FINE, "received enforce {0}", iEnforce.getClass().getSimpleName());
		mng.getEnforceCallback().call(iEnforce);
	}
}
