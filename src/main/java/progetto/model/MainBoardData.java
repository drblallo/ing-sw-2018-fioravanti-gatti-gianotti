package progetto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Immutable class with data of MainBoard
 */
public final class MainBoardData implements Serializable {

	public static final int MAX_NUM_PLAYERS = 4;
	private static final int STD_DIFFICULTY = 3;

	private final List<WindowFrameCouple> windowFrameCouples;
	private final List<ToolCard> toolCards;
	private final List<AbstractPublicObjectiveCard> publicObjectiveCards;
	private final List<Integer> nCallToolCard;
	private final int playerCount;
	private final AbstractGameState gameState;
	private final int difficulty;

	private final int singlePlayerTarget;


	/**
	 * Constructor
	 */
	public MainBoardData()
	{
		playerCount = MAX_NUM_PLAYERS;

		ArrayList<WindowFrameCouple> tempW = new ArrayList<>();
		windowFrameCouples = Collections.unmodifiableList(tempW);

		ArrayList<ToolCard> tempC = new ArrayList<>();
		toolCards = Collections.unmodifiableList(tempC);

		ArrayList<Integer> tempN = new ArrayList<>();
		nCallToolCard = Collections.unmodifiableList(tempN);

		ArrayList<AbstractPublicObjectiveCard> tempP = new ArrayList<>();
		publicObjectiveCards = Collections.unmodifiableList(tempP);

		gameState = new PreGameState();

		difficulty = STD_DIFFICULTY;

		singlePlayerTarget = 0;

	}

	/**
	 * Constructor to add WindowFrameCouple
	 * @param mainBoardData previous mainBoardData
	 * @param windowFrameCouple to add
	 */
	private MainBoardData(MainBoardData mainBoardData, WindowFrameCouple windowFrameCouple)
	{
		this.playerCount = mainBoardData.playerCount;

		ArrayList<WindowFrameCouple> tempW = new ArrayList<>(mainBoardData.windowFrameCouples);
		tempW.add(windowFrameCouple);
		this.windowFrameCouples = Collections.unmodifiableList(tempW);

		ArrayList<ToolCard> tempC = new ArrayList<>(mainBoardData.toolCards);
		toolCards = Collections.unmodifiableList(tempC);

		ArrayList<Integer> tempN = new ArrayList<>(mainBoardData.nCallToolCard);
		nCallToolCard = Collections.unmodifiableList(tempN);

		ArrayList<AbstractPublicObjectiveCard> tempP = new ArrayList<>(mainBoardData.publicObjectiveCards);
		publicObjectiveCards = Collections.unmodifiableList(tempP);

		this.gameState = mainBoardData.gameState;
		this.difficulty = mainBoardData.difficulty;
		this.singlePlayerTarget = mainBoardData.singlePlayerTarget;

	}

	/**
	 * Constructor to add or remove a ToolCard
	 * @param mainBoardData previous mainBoardData
	 * @param toolCard list to set
	 */
	private MainBoardData(MainBoardData mainBoardData, List<ToolCard> toolCard)
	{
		this.playerCount = mainBoardData.playerCount;

		ArrayList<WindowFrameCouple> tempW = new ArrayList<>(mainBoardData.windowFrameCouples);
		this.windowFrameCouples = Collections.unmodifiableList(tempW);

		ArrayList<ToolCard> tempC = new ArrayList<>(toolCard);
		toolCards = Collections.unmodifiableList(tempC);

		ArrayList<Integer> tempN = new ArrayList<>(mainBoardData.nCallToolCard);
		tempN.add(0);
		nCallToolCard = Collections.unmodifiableList(tempN);

		ArrayList<AbstractPublicObjectiveCard> tempP = new ArrayList<>(mainBoardData.publicObjectiveCards);
		publicObjectiveCards = Collections.unmodifiableList(tempP);

		this.gameState = mainBoardData.gameState;
		this.difficulty = mainBoardData.difficulty;
		this.singlePlayerTarget = mainBoardData.singlePlayerTarget;

	}

	/**
	 * Constructor to set a new value for different attributes
	 * @param mainBoardData previous mainBoardData
	 * @param playerCount new playerCount value
	 * @param difficulty new difficulty value
	 */
	private MainBoardData(MainBoardData mainBoardData, int playerCount, int difficulty, int singlePlayerTarget)
	{
		this.playerCount = playerCount;

		ArrayList<WindowFrameCouple> tempW = new ArrayList<>(mainBoardData.windowFrameCouples);
		windowFrameCouples = Collections.unmodifiableList(tempW);

		ArrayList<ToolCard> tempC = new ArrayList<>(mainBoardData.toolCards);
		toolCards = Collections.unmodifiableList(tempC);

		ArrayList<Integer> tempN = new ArrayList<>(mainBoardData.nCallToolCard);
		nCallToolCard = Collections.unmodifiableList(tempN);

		ArrayList<AbstractPublicObjectiveCard> tempP = new ArrayList<>(mainBoardData.publicObjectiveCards);
		publicObjectiveCards = Collections.unmodifiableList(tempP);

		this.difficulty = difficulty;

		this.gameState = mainBoardData.gameState;
		this.singlePlayerTarget = singlePlayerTarget;

	}

	/**
	 * Constructor to set a new value for game State
	 * @param mainBoardData previous mainBoardData
	 * @param gameState new gameState
	 */
	private MainBoardData(MainBoardData mainBoardData, AbstractGameState gameState)
	{
		this.playerCount = mainBoardData.playerCount;

		ArrayList<WindowFrameCouple> tempW = new ArrayList<>(mainBoardData.windowFrameCouples);
		windowFrameCouples = Collections.unmodifiableList(tempW);

		ArrayList<ToolCard> tempC = new ArrayList<>(mainBoardData.toolCards);
		toolCards = Collections.unmodifiableList(tempC);

		ArrayList<Integer> tempN = new ArrayList<>(mainBoardData.nCallToolCard);
		nCallToolCard = Collections.unmodifiableList(tempN);

		ArrayList<AbstractPublicObjectiveCard> tempP = new ArrayList<>(mainBoardData.publicObjectiveCards);
		publicObjectiveCards = Collections.unmodifiableList(tempP);

		this.difficulty = mainBoardData.difficulty;

		this.gameState = gameState;

		this.singlePlayerTarget = mainBoardData.singlePlayerTarget;

	}

	/**
	 * Constructor to add a publicObjectiveCard
	 * @param mainBoardData previous mainBoardData
	 * @param publicObjectiveCard to set
	 */
	private MainBoardData(MainBoardData mainBoardData, AbstractPublicObjectiveCard publicObjectiveCard)
	{
		this.playerCount = mainBoardData.playerCount;

		ArrayList<WindowFrameCouple> tempW = new ArrayList<>(mainBoardData.windowFrameCouples);
		windowFrameCouples = Collections.unmodifiableList(tempW);

		ArrayList<ToolCard> tempC = new ArrayList<>(mainBoardData.toolCards);
		toolCards = Collections.unmodifiableList(tempC);

		ArrayList<Integer> tempN = new ArrayList<>(mainBoardData.nCallToolCard);
		nCallToolCard = Collections.unmodifiableList(tempN);

		ArrayList<AbstractPublicObjectiveCard> tempP = new ArrayList<>(mainBoardData.publicObjectiveCards);
		tempP.add(publicObjectiveCard);
		publicObjectiveCards = Collections.unmodifiableList(tempP);

		this.gameState = mainBoardData.gameState;

		this.difficulty = mainBoardData.difficulty;

		this.singlePlayerTarget = mainBoardData.singlePlayerTarget;

	}

	/**
	 * Constructor to increment the number of usage of a tool card
	 * @param mainBoardData previous mainBoardData
	 * @param pos position of the user tool card
	 */
	private MainBoardData(MainBoardData mainBoardData, int pos)
	{
		this.playerCount = mainBoardData.playerCount;

		ArrayList<WindowFrameCouple> tempW = new ArrayList<>(mainBoardData.windowFrameCouples);
		windowFrameCouples = Collections.unmodifiableList(tempW);

		ArrayList<ToolCard> tempC = new ArrayList<>(mainBoardData.toolCards);
		toolCards = Collections.unmodifiableList(tempC);

		ArrayList<Integer> tempN = new ArrayList<>(mainBoardData.nCallToolCard);
		int usage = tempN.remove(pos);
		usage++;
		tempN.add(pos, usage);
		nCallToolCard = Collections.unmodifiableList(tempN);

		ArrayList<AbstractPublicObjectiveCard> tempP = new ArrayList<>(mainBoardData.publicObjectiveCards);
		publicObjectiveCards = Collections.unmodifiableList(tempP);

		this.gameState = mainBoardData.gameState;

		this.difficulty = mainBoardData.difficulty;

		this.singlePlayerTarget = mainBoardData.singlePlayerTarget;

	}

	/**
	 * Get number of player
	 * @return the current player count
	 */
	public int getPlayerCount()
	{
		return playerCount;
	}

	/**
	 * Get single player target score
	 * @return single player target score
	 */
	public int getSinglePlayerTarget()
	{
		return singlePlayerTarget;
	}

	/**
	 * Get windowFrame
	 * @param index position of the window frame to get
	 * @return selected window frame
	 */
	WindowFrameCouple getWindowFrame(int index)
	{
		return windowFrameCouples.get(index);
	}

	/**
	 * Get List windowFrameCouples
	 * @return List of window frame couples
	 */
	List<WindowFrameCouple> getWindowFrameCouples()
	{
		return new ArrayList<>(this.windowFrameCouples);
	}

	/**
	 * Get gameState
	 * @return the current model state
	 */
	public AbstractGameState getGameState()
	{
		return gameState;
	}

	/**
	 * Get difficulty (single player)
	 * @return difficulty
	 */
	public int getDifficulty()
	{
		return difficulty;
	}

	/**
	 * Set number of player
	 * @param playerCount the new player count
	 */
	MainBoardData setPlayerCount(int playerCount)
	{
		return new MainBoardData(this, playerCount, difficulty, singlePlayerTarget);
	}

	/**
	 * Set gameState
	 * @param state the new state of the model
	 */
	MainBoardData setGameState(AbstractGameState state)
	{
		return new MainBoardData(this, state);
	}

	/**
	 * Add a windowFrameCouple
	 * @param windowFrameCouple to add
	 * @return new MainBoardData with added windowFrameCouple
	 */
	MainBoardData addWindowFrameCouple(WindowFrameCouple windowFrameCouple)
	{
		return new MainBoardData(this, windowFrameCouple);
	}

	/**
	 * Add public ObjectiveCards
	 * @param publicObjectiveCards to add
	 * @return  new MainBoardData with added ObjectiveCard
	 */
	MainBoardData addPublicObjectiveCard(AbstractPublicObjectiveCard publicObjectiveCards)
	{
		return new MainBoardData(this, publicObjectiveCards);
	}

	/**
	 * Get List of public objective cards
	 * @return List of public objective cards
	 */
	public List<AbstractPublicObjectiveCard> getPublicObjectiveCards()
	{
		return new ArrayList<>(this.publicObjectiveCards);
	}

	/**
	 * Get List of tool cards
	 * @return List of tool cards
	 */
	public List<ToolCard> getToolCards()
	{
		return new ArrayList<>(this.toolCards);
	}

	/**
	 * Set toolCard list
	 * @param tempC new ToolCard list
	 * @return new MainBoardData with modified list
	 */
	MainBoardData setToolCardList(List<ToolCard> tempC)
	{
		return new MainBoardData(this, tempC);

	}

	/**
	 * Set difficulty
	 * @param difficulty to set
	 * @return new MainBoardData with modified difficulty
	 */
	MainBoardData setDifficulty(int difficulty)
	{
		return new MainBoardData(this, playerCount, difficulty, singlePlayerTarget);
	}

	/**
	 * Increase number of call of the tool card in position pos
	 * @param pos position of the tool card
	 * @return new MainBoardData with modified number of call
	 */
	MainBoardData incNCallToolCard(int pos)
	{
		if(pos<0 || pos>=nCallToolCard.size())
		{
			return this;
		}
		return new MainBoardData(this, pos);

	}

	/**
	 * Get number of call of tool card in position pos
	 * @param pos position of the tool card
	 * @return number of call
	 */
	public Integer getNCallToolCard(int pos)
	{
		if(pos<0 || pos>=nCallToolCard.size())
		{
			return 0;
		}
		return nCallToolCard.get(pos);

	}

	/**
	 * Set singlePlayerTarget
	 * @param singlePlayerTarget to set
	 * @return new MainBoardData with modified singlePlayerTarget
	 */
	MainBoardData setSinglePlayerTarget(int singlePlayerTarget)
	{
		return new MainBoardData(this, playerCount, difficulty, singlePlayerTarget);
	}


}
