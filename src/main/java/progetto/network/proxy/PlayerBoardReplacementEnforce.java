package progetto.network.proxy;

import progetto.model.PlayerBoardData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerBoardReplacementEnforce implements IEnforce
{
	private static final Logger LOGGER = Logger.getLogger(PlayerBoardReplacementEnforce.class.getName());
	private PlayerBoardData data;
	private int target;

	public PlayerBoardReplacementEnforce(PlayerBoardData data, int target)
	{
		this.data = data;
		this.target = target;
	}

	@Override
	public void execute(ClientConnection c)
	{
		LOGGER.log(Level.FINE, "Trying to replace a board {0}", target);
		c.getProxy().getPlayerBoard(target).setData(data);
	}
}
