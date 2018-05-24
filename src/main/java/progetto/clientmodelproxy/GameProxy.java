package progetto.clientmodelproxy;


import progetto.game.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameProxy implements IGame
{
	private MainBoardProxy mainBoardProxy = new MainBoardProxy();
	private DataContainerProxy<RoundTrackData> roundTrackProxy = new DataContainerProxy<>(new RoundTrackData());
	private PlayerBoardProxy[] playerBoardProxy = new PlayerBoardProxy[Game.MAX_NUM_PLAYERS];
	private DataContainerProxy<CommandQueueData> commandQueueProxy = new DataContainerProxy<>(
			new CommandQueueData(new ArrayList<>(), new LinkedList<>()));

	public GameProxy()
	{
		for (int a = 0; a < playerBoardProxy.length; a++)
			playerBoardProxy[a] = new PlayerBoardProxy();
	}

	@Override
	public DataContainerProxy<CommandQueueData> getCommandQueue() {
		return commandQueueProxy;
	}

	@Override
	public DataContainerProxy<RoundTrackData> getRoundTrack() {
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
