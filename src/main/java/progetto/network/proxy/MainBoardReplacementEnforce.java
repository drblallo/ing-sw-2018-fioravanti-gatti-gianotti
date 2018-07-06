package progetto.network.proxy;

import progetto.model.MainBoardData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

/**
 * @author Massimo
 * enforce that replaces the main board data holded by the client
 */
public class MainBoardReplacementEnforce implements IEnforce
{
	private final MainBoardData mainBoardData;

	/**
	 *
	 * @param data the data that will be sent to the player
	 */
	public MainBoardReplacementEnforce(MainBoardData data)
	{
		mainBoardData = data;
	}

	/**
	 * replaced the data in the client side
	 * @param c the client connection that received this enforce
	 */
	@Override
	public void execute(ClientConnection c) {
		c.getProxy().getMainBoard().setData(mainBoardData);
	}
}
