package progetto.network.proxy;

import progetto.model.ExtractedDicesData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

/**
 * @author Massimo
 * the enforce that will replace the extracted dice that on the player side
 */
public class ExtractedDicesEnforce implements IEnforce
{
	private final ExtractedDicesData data;

	/**
	 * @param data the data that will be sent to the player
	 */
	public ExtractedDicesEnforce(ExtractedDicesData data)
	{
		this.data = data;
	}

	/**
	 * replace the extracted dices
	 * @param c the client connection that received this enforce
	 */
	@Override
	public void execute(ClientConnection c) {
		c.getProxy().getMainBoard().getExtractedDices().setData(data);
	}
}
