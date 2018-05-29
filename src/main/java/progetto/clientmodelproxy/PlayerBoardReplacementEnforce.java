package progetto.clientmodelproxy;

import progetto.game.PlayerBoardData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

public class PlayerBoardReplacementEnforce implements IEnforce
{
	private PlayerBoardData data;
	private int target;

	public PlayerBoardReplacementEnforce(PlayerBoardData data, int target)
	{
		this.data = data;
		this.target = target;
	}

	@Override
	public void execute(ClientConnection c) {
		c.getProxy().getPlayerBoard(target).setData(data);
	}
}
