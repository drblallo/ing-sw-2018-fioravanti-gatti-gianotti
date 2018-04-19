package progetto.network.socket;

import progetto.network.NetworkSettings;

/**
 * Message sent every TIME_TO_LIVE to inform the other side that we are still connected.
 */
final class KeepAliveCommand implements INetworkCommand<AbstractSocket> {

	@Override
	public void execute(AbstractSocket client) {
		client.setTTL(NetworkSettings.MAX_TIME_TO_LIVE_SKIPPED);
	}
}
