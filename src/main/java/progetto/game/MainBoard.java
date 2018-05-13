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

	/**
	 * Get immutable mainBoardData
	 * @return mainBoardData
	 */
	public MainBoardData getMainBoardData()
	{
		return mainBoardData;
	}

	/**
	 * Get extracted dices
	 * @return extractedDices
	 */
	public ExtractedDices getExtractedDices()
	{
		return extractedDices;
	}

	/**
	 * Add window frame couple
	 * @param windowFrameCouple to add
	 */
	void addWindowFrameCouple(WindowFrameCouple windowFrameCouple)
	{
		mainBoardData = mainBoardData.addWindowFrameCouple(windowFrameCouple);
		change(mainBoardData);
	}

	/**
	 * Set number of player
	 * @param playerCount the new player count
	 */
	void setPlayerCount(int playerCount)
	{
		mainBoardData = mainBoardData.setPlayerCount(playerCount);
		change(mainBoardData);
	}

	/**
	 * Set gameState
	 * @param state the new state of the game
	 */
	void setGameState(AbstractGameState state)
	{
		mainBoardData = mainBoardData.setGameState(state);
		change(mainBoardData);
	}

	/**
	 * Set current first player
	 * @param currentFirstPlayer new current first player
	 */
	void setCurrentFirstPlayer(int currentFirstPlayer)
	{
		mainBoardData = mainBoardData.setCurrentFirstPlayer(currentFirstPlayer);
		change(mainBoardData);
	}

	/**
	 * Set current player
	 * @param currentPlayer new current player
	 */
	void setCurrentPlayer(int currentPlayer)
	{
		mainBoardData = mainBoardData.setCurrentPlayer(currentPlayer);
		change(mainBoardData);
	}

	/**
	 * Set current round
	 * @param currentRound new current round
	 */
	void setCurrentRound(int currentRound)
	{
		mainBoardData = mainBoardData.setCurrentRound(currentRound);
		change(mainBoardData);
	}

	/**
	 * Add a player in the queue of the round
	 * @param i next player in round player queue
	 */
	void addPlayerQueue(Integer i)
	{
		playerQueue.add(i);
	}

	/**
	 * Get the index of the next player
	 * @return next player to play
	 */
	Integer getNextPlayer()
	{
		if(!playerQueue.isEmpty())
			return playerQueue.remove(0);
		return -1;
	}

}
