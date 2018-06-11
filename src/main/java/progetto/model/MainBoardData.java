package progetto.model;

import java.io.Serializable;
import java.util.*;

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
	private final int currentFirstPlayer;
	private final int currentPlayer;
	private final int currentRound;
	private final int difficulty;

	private final Map<String, Integer> paramToolCard;


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
		currentFirstPlayer = 0;
		currentPlayer = 0;
		currentRound = 1;
		difficulty = STD_DIFFICULTY;

		paramToolCard = Collections.unmodifiableMap(new HashMap<>());

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
		this.currentFirstPlayer = mainBoardData.currentFirstPlayer;
		this.currentPlayer = mainBoardData.currentPlayer;
		this.currentRound = mainBoardData.currentRound;
		this.paramToolCard = mainBoardData.paramToolCard;

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
		this.currentFirstPlayer = mainBoardData.currentFirstPlayer;
		this.currentPlayer = mainBoardData.currentPlayer;
		this.currentRound = mainBoardData.currentRound;
		this.difficulty = mainBoardData.difficulty;
		this.paramToolCard = mainBoardData.paramToolCard;

	}

	/**
	 * Constructor to set a new value for different attributes
	 * @param mainBoardData previous mainBoardData
	 * @param playerCount new playerCount value
	 * @param currentFirstPlayer new currentFirstPlayer value
	 * @param currentPlayer new currentPlayer value
	 * @param currentRound new currentRound value
	 * @param difficulty new difficulty value
	 */
	private MainBoardData(MainBoardData mainBoardData, int playerCount, int currentFirstPlayer, int currentPlayer,
	                      int currentRound, int difficulty)
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

		this.currentFirstPlayer = currentFirstPlayer;
		this.currentPlayer = currentPlayer;
		this.currentRound = currentRound;
		this.difficulty = difficulty;

		this.gameState = mainBoardData.gameState;
		this.paramToolCard = mainBoardData.paramToolCard;

	}

	/**
	 * Constructor to set a new value for different attributes
	 * @param mainBoardData previous mainBoardData
	 * @param paramToolCard new HashMap
	 * @param gameState new gameState
	 */
	private MainBoardData(MainBoardData mainBoardData, Map paramToolCard, AbstractGameState gameState)
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

		this.currentFirstPlayer = mainBoardData.currentFirstPlayer;
		this.currentPlayer = mainBoardData.currentPlayer;
		this.currentRound = mainBoardData.currentRound;
		this.difficulty = mainBoardData.difficulty;

		this.gameState = gameState;
		this.paramToolCard = paramToolCard;

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
		this.currentFirstPlayer = mainBoardData.currentFirstPlayer;
		this.currentPlayer = mainBoardData.currentPlayer;
		this.currentRound = mainBoardData.currentRound;
		this.difficulty = mainBoardData.difficulty;
		this.paramToolCard = mainBoardData.paramToolCard;

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
		this.currentFirstPlayer = mainBoardData.currentFirstPlayer;
		this.currentPlayer = mainBoardData.currentPlayer;
		this.currentRound = mainBoardData.currentRound;
		this.difficulty = mainBoardData.difficulty;
		this.paramToolCard = mainBoardData.paramToolCard;

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
	 * Get current first player
	 * @return current first player
	 */
	public int getCurrentFirstPlayer()
	{
		return currentFirstPlayer;
	}

	/**
	 * Get current player
	 * @return current player
	 */
	public int getCurrentPlayer()
	{
		return currentPlayer;
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
	 * Get current round
	 * @return current round
	 */
	public int getCurrentRound()
	{
		return currentRound;
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
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer , currentRound, difficulty);
	}

	/**
	 * Set gameState
	 * @param state the new state of the model
	 */
	MainBoardData setGameState(AbstractGameState state)
	{
		return new MainBoardData(this, paramToolCard, state);
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
	 * Set current first player
	 * @param currentFirstPlayer to set
	 * @return new MainBoardData with modified currentFirstPlayer
	 */
	MainBoardData setCurrentFirstPlayer(int currentFirstPlayer)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer , currentRound, difficulty);
	}

	/**
	 * Set current player
	 * @param currentPlayer to set
	 * @return new MainBoardData with modified currentPlayer
	 */
	MainBoardData setCurrentPlayer(int currentPlayer)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer , currentRound, difficulty);
	}

	/**
	 * Set current round
	 * @param currentRound to set
	 * @return new MainBoardData with modified currentRound
	 */
	MainBoardData setCurrentRound(int currentRound)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer , currentRound, difficulty);
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
	List<AbstractPublicObjectiveCard> getPublicObjectiveCards()
	{
		return new ArrayList<>(this.publicObjectiveCards);
	}

	/**
	 * Get List of public objective cards
	 * @return List of public objective cards
	 */
	public List<ToolCard> getToolCards()
	{
		return new ArrayList<>(this.toolCards);
	}

	/**
	 * Add one tool card to the main board
	 * @param toolCard to add
	 * @return new MainBoardData with added toolCard
	 */
	MainBoardData addToolCard(ToolCard toolCard)
	{
		ArrayList<ToolCard> tempC = new ArrayList<>(toolCards);
		tempC.add(toolCard);
		return new MainBoardData(this, tempC);
	}

	/**
	 * Set difficulty
	 * @param difficulty to set
	 * @return new MainBoardData with modified difficulty
	 */
	MainBoardData setDifficulty(int difficulty)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer , currentRound, difficulty);
	}

	/**
	 * Add a parameter of a tool card
	 * @param nameP new parameter
	 * @param val new value
	 * @return new MainBoardData with added parameter
	 */
	MainBoardData setParamToolCard(String nameP, Integer val)
	{
		HashMap<String, Integer> hs = new HashMap<>(paramToolCard);
		hs.put(nameP, val);
		return new MainBoardData(this, hs, gameState);
	}

	/**
	 * Get parameters
	 * @return parameters of tool cards
	 */
	public Map<String, Integer> getParamToolCard()
	{
		return paramToolCard;
	}

	/**
	 * Delete all parameters of tool cards
	 * @return new MainBoardData without deleted parameters
	 */
	MainBoardData delParamToolCard()
	{
		HashMap<String, Integer> hs = new HashMap<>();
		return new MainBoardData(this, hs, gameState);
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
	 * Remove one tool card from the main board
	 * @param index of the toolCard to remove
	 * @return new MainBoardData without selected toolCard
	 */
	MainBoardData removeToolCard(int index)
	{
		ArrayList<ToolCard> tempC = new ArrayList<>(toolCards);
		tempC.remove(index);
		return new MainBoardData(this, tempC);
	}


}
