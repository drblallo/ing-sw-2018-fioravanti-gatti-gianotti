package progetto.network.proxy;

import progetto.model.RoundTrackData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

/**
 * @author Massimo
 * the round track enforce that will send the round track data to the player
 */
public class RoundTrackEnforce implements IEnforce
{
	private final RoundTrackData data;

	/**
	 *
	 * @param data the data to be sent to the player
	 */
	public RoundTrackEnforce(RoundTrackData data) {
		this.data = data;
	}

	/**
	 *
	 * @param c the client connection that received this enforce
	 */
	@Override
	public void execute(ClientConnection c)
	{
		c.getProxy().getRoundTrack().setData(data);
	}
}
