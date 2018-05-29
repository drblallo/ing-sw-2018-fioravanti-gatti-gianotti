package progetto.clientmodelproxy;

import progetto.game.MainBoardData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

public class MainBoardReplacementEnforce implements IEnforce
{
	private final MainBoardData mainBoardData;

	public MainBoardReplacementEnforce(MainBoardData data)
	{
		mainBoardData = data;
	}

	@Override
	public void execute(ClientConnection c) {
		c.getProxy().getMainBoard().setData(mainBoardData);
	}
}
