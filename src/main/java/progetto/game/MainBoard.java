package progetto.game;

import progetto.utils.AbstractObservable;

import java.util.ArrayList;

/**
 * Main gaming table
 */
public final class MainBoard extends AbstractObservable<MainBoardData> {

	private MainBoardData mainBoardData = new MainBoardData();
	private final ExtractedDices extractedDices = new ExtractedDices();

	private ArrayList<Integer> playerQueue = new ArrayList<>();

	public MainBoardData getMainBoardData()
	{
		return mainBoardData;
	}

	public ExtractedDices getExtractedDices()
	{
		return extractedDices;
	}

	void addWindowFrameCouple(WindowFrameCouple windowFrameCouple)
	{
		mainBoardData = mainBoardData.addWindowFrameCouple(windowFrameCouple);
		change(mainBoardData);
	}

	/**
	 *
	 * @param playerCount the new player count
	 */
	void setPlayerCount(int playerCount)
	{
		mainBoardData = mainBoardData.setPlayerCount(playerCount);
		change(mainBoardData);
	}

	/**
	 *
	 * @param state the new state of the game
	 */
	void setGameState(AbstractGameState state)
	{
		mainBoardData = mainBoardData.setGameState(state);
		change(mainBoardData);
	}

	void setCurrentFirstPlayer(int currentFirstPlayer)
	{
		mainBoardData = mainBoardData.setCurrentFirstPlayer(currentFirstPlayer);
		change(mainBoardData);
	}

	void setCurrentPlayer(int currentPlayer)
	{
		mainBoardData = mainBoardData.setCurrentPlayer(currentPlayer);
		change(mainBoardData);
	}

	void setCurrentRound(int currentRound)
	{
		mainBoardData = mainBoardData.setCurrentRound(currentRound);
		change(mainBoardData);
	}

	void addPlayerQueue(Integer i)
	{
		playerQueue.add(i);
	}

	Integer getNextPlayer()
	{
		if(!playerQueue.isEmpty())
			return playerQueue.remove(0);
		return -1;
	}

}
