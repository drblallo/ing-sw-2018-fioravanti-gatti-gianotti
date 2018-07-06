package progetto.network.proxy;

import progetto.model.PlayerBoardData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Massimo
 * the enforce that is calle to replace the data on the client side
 */
public class PlayerBoardReplacementEnforce implements IEnforce
{
	private static final Logger LOGGER = Logger.getLogger(PlayerBoardReplacementEnforce.class.getName());
	private PlayerBoardData data;
	private int target;

	/**
	 *
	 * @param data the data that will be sent to the player
	 * @param target the chair of the player that will receive this item
	 */
	public PlayerBoardReplacementEnforce(PlayerBoardData data, int target)
	{
		this.data = data;
		this.target = target;
	}

	/**
	 *
	 * @param c the client connection that received this enforce
	 */
	@Override
	public void execute(ClientConnection c)
	{
		LOGGER.log(Level.FINE, "Trying to replace a board {0}", target);
		c.getProxy().getPlayerBoard(target).setData(data);
	}
}
