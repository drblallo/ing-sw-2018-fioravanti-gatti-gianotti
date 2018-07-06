package progetto.network.proxy;

import progetto.model.RoundInformationData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

/**
 * @author Massimo
 * this enforce is used to sent to the client the data regaring the round information
 */
public class RoundInformationEnforce implements IEnforce{

	private RoundInformationData data;

	/**
	 *
	 * @param d the round information to be sent to the player
	 */
	public RoundInformationEnforce(RoundInformationData d)
	{
		data = d;
	}


	/**
	 *
	 * @param c the client connection that received this enforce
	 */
	@Override
	public void execute(ClientConnection c) {
		c.getProxy().getRoundInformation().setData(data);
	}
}
