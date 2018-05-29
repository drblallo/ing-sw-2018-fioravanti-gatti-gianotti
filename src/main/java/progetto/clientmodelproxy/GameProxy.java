package progetto.clientmodelproxy;


import progetto.game.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameProxy implements IGame
{
	private MainBoardProxy mainBoardProxy = new MainBoardProxy();
	private DataContainer<RoundTrackData> roundTrackProxy = new DataContainer<>(new RoundTrackData());
	private PlayerBoardProxy[] playerBoardProxy = new PlayerBoardProxy[Game.MAX_NUM_PLAYERS];
	private DataContainer<CommandQueueData> commandQueueProxy = new DataContainer<>(
			new CommandQueueData(new ArrayList<>(), new LinkedList<>()));

	public GameProxy()
	{
		for (int a = 0; a < playerBoardProxy.length; a++)
			playerBoardProxy[a] = new PlayerBoardProxy();
	}

	@Override
	public DataContainer<CommandQueueData> getCommandQueue() {
		return commandQueueProxy;
	}

	@Override
	public DataContainer<RoundTrackData> getRoundTrack() {
		return roundTrackProxy;
	}

	@Override
	public PlayerBoardProxy getPlayerBoard(int index) {
		return playerBoardProxy[index];
	}

	@Override
	public MainBoardProxy getMainBoard() {
		return mainBoardProxy;
	}

}
