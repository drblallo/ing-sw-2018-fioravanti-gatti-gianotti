package progetto.model;

import java.util.ArrayList;

/**
 * Main gaming table
 */
public final class MainBoard extends AbstractMainBoard
{
	MainBoard()
	{
		super(new MainBoardData());
	}

	private final ExtractedDices extractedDices = new ExtractedDices();

	private ArrayList<Integer> playerQueue = new ArrayList<>();


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
		setData(getData().addWindowFrameCouple(windowFrameCouple));
	}

	/**
	 * Set number of player
	 * @param playerCount the new player count
	 */
	public void setPlayerCount(int playerCount)
	{
		setData(getData().setPlayerCount(playerCount));
	}

	/**
	 * Set gameState
	 * @param state the new state of the model
	 */
	void setGameState(AbstractGameState state)
	{
		setData(getData().setGameState(state));
	}

	/**
	 * Set current first player
	 * @param currentFirstPlayer new current first player
	 */
	public void setCurrentFirstPlayer(int currentFirstPlayer)
	{
		setData(getData().setCurrentFirstPlayer(currentFirstPlayer));
	}

	/**
	 * Set current player
	 * @param currentPlayer new current player
	 */
	public void setCurrentPlayer(int currentPlayer)
	{
		setData(getData().setCurrentPlayer(currentPlayer));
	}

	/**
	 * Set current round
	 * @param currentRound new current round
	 */
	public void setCurrentRound(int currentRound)
	{
		setData(getData().setCurrentRound(currentRound));
	}

	/**
	 * Add a player in the queue of the round
	 * @param i next player in round player queue
	 */
	public void addPlayerQueue(Integer i)
	{
		playerQueue.add(i);
	}

	/**
	 * Get the index of the next player
	 * @return next player to play
	 */
	public Integer getNextPlayer()
	{
		if(!playerQueue.isEmpty())
			return playerQueue.remove(0);
		return -1;
	}

	/**
	 * Add a public objective card
	 * @param publicObjectiveCard to add
	 */
	public void addPublicObjectiveCards(AbstractPublicObjectiveCard publicObjectiveCard)
	{
		setData(getData().addPublicObjectiveCard(publicObjectiveCard));
	}

	/**
	 * Add one tool card
	 * @param toolCard to add
	 */
	public void addToolCard(AbstractToolCard toolCard)
	{
		setData(getData().addToolCard(toolCard));
	}

	/**
	 * Set difficulty
	 * @param difficulty to set
	 */
	public void setDifficulty(int difficulty)
	{
		setData(getData().setDifficulty(difficulty));
	}

	public void setParamToolCard(String param, Integer val)
	{
		setData(getData().setParamToolCard(param, val));
	}

	public void delParamToolCard()
	{
		setData(getData().delParamToolCard());
	}

}
