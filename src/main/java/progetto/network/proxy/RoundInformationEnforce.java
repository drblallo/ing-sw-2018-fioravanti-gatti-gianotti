package progetto.network.proxy;

import progetto.model.RoundInformationData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

/**
 * @author Massimo
 */
public class RoundInformationEnforce implements IEnforce{

	private RoundInformationData data;

	public RoundInformationEnforce(RoundInformationData d)
	{
		data = d;
	}


	@Override
	public void execute(ClientConnection c) {
		c.getProxy().getRoundInformation().setData(data);
	}
}
