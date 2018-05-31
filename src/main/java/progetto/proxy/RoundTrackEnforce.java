package progetto.proxy;

import progetto.model.RoundTrackData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

public class RoundTrackEnforce implements IEnforce
{
	private final RoundTrackData data;

	public RoundTrackEnforce(RoundTrackData data) {
		this.data = data;
	}

	@Override
	public void execute(ClientConnection c)
	{
		c.getProxy().getRoundTrack().setData(data);
	}
}
