package progetto.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

/**
 * Main gaming table
 */
public final class MainBoard extends AbstractMainBoard
{
	private final ExtractedDices extractedDices = new ExtractedDices();

	private static final Logger LOGGER = Logger.getLogger(MainBoard.class.getName());

	/**
	 * Constructor
	 */
	MainBoard()
	{
		super(new MainBoardData());
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
	public void addToolCard(ToolCard toolCard)
	{
		List<ToolCard> toolCardList = getData().getToolCards();
		toolCardList.add(toolCard);
		setData(getData().setToolCardList(toolCardList));
	}

	/**
	 * Remove a tool card from main board
	 * @param index index of the card to remove
	 */
	public void removeToolCard(int index)
	{
		List<ToolCard> toolCardList = getData().getToolCards();
		try
		{
			toolCardList.remove(index);
			setData(getData().setToolCardList(toolCardList));
		} catch (Exception e)
		{
			LOGGER.log(Level.SEVERE,"Wrong index");
		}

	}

	/**
	 * Set difficulty
	 * @param difficulty to set
	 */
	public void setDifficulty(int difficulty)
	{
		setData(getData().setDifficulty(difficulty));
	}

	/**
	 * Set a parameter of a tool card
	 * @param param new param
	 * @param val new value
	 */
	public void setParamToolCard(String param, Integer val)
	{
		setData(getData().setParamToolCard(param, val));
	}

	/**
	 * Delete all parameters of a tool card
	 */
	public void delParamToolCard()
	{
		setData(getData().delParamToolCard());
	}

	/**
	 * Increase number of call of the tool card in position pos
	 * @param pos position of the tool card
	 */
	public void incNCallToolCard(int pos)
	{
		setData(getData().incNCallToolCard(pos));
	}


}
