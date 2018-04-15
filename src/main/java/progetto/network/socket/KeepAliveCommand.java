package progetto.network.socket;

import progetto.network.NetworkSettings;

/**
 * Message sent every TIME_TO_LIVE to inform the other side that we are still connected.
 */
class KeepAliveCommand extends AbstractNetworkCommand<AbstractSocketManager>
{

	@Override
	protected void execute(AbstractSocketManager client)
	{
		client.setTTL(NetworkSettings.MAX_TIME_TO_LIVE_SKIPPED);
	}
}
