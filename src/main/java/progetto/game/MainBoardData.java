package progetto.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Immutable class with data of MainBoard
 */
public final class MainBoardData implements Serializable {

	public static final int MAX_NUM_PLAYERS = 4;

	private final List<WindowFrameCouple> windowFrameCouples;
	private final int playerCount;
	private final AbstractGameState gameState;
	private final int currentFirstPlayer;
	private final int currentPlayer;
	private final int currentRound;
	private final AbstractPublicObjectiveCard[] publicObjectiveCard;

	/**
	 * Constructor
	 */
	public MainBoardData()
	{
		playerCount = MAX_NUM_PLAYERS;
		ArrayList<WindowFrameCouple> temp = new ArrayList<>();
		windowFrameCouples = Collections.unmodifiableList(temp);
		gameState = new PreGameState();
		currentFirstPlayer = 0;
		currentPlayer = 0;
		currentRound = 1;
		publicObjectiveCard = null;
	}

	/**
	 * Constructor to change gameState
	 * @param mainBoardData previous mainBoardData
	 * @param gameState new gameState
	 */
	private MainBoardData(MainBoardData mainBoardData, AbstractGameState gameState)
	{
		this.playerCount = mainBoardData.playerCount;

		ArrayList<WindowFrameCouple> temp = new ArrayList<>(mainBoardData.windowFrameCouples);
		windowFrameCouples = Collections.unmodifiableList(temp);

		this.gameState = gameState;

		this.currentFirstPlayer = mainBoardData.currentFirstPlayer;

		this.currentPlayer = mainBoardData.currentPlayer;

		this.currentRound = mainBoardData.currentRound;

		this.publicObjectiveCard = mainBoardData.publicObjectiveCard;
	}

	/**
	 * Constructor to add WindowFrameCouple
	 * @param mainBoardData previous mainBoardData
	 * @param windowFrameCouple to add
	 */
	private MainBoardData(MainBoardData mainBoardData, WindowFrameCouple windowFrameCouple)
	{
		this.playerCount = mainBoardData.playerCount;

		ArrayList<WindowFrameCouple> temp = new ArrayList<>(mainBoardData.windowFrameCouples);
		temp.add(windowFrameCouple);
		this.windowFrameCouples = Collections.unmodifiableList(temp);

		this.gameState = mainBoardData.gameState;

		this.currentFirstPlayer = mainBoardData.currentFirstPlayer;

		this.currentPlayer = mainBoardData.currentPlayer;

		this.currentRound = mainBoardData.currentRound;

		this.publicObjectiveCard = mainBoardData.publicObjectiveCard;
	}

	/**
	 * Constructor to set a new vaue of playerCount or currentFirstPlayer or CurrentPlayer or currentRound
	 * @param mainBoardData previous mainBoardData
	 * @param playerCount new playerCount value
	 * @param currentFirstPlayer new currentFirstPlayer value
	 * @param currentPlayer new currentPlayer value
	 * @param currentRound new currentRound value
	 */
	private MainBoardData(MainBoardData mainBoardData, int playerCount, int currentFirstPlayer, int currentPlayer, int currentRound)
	{
		this.playerCount = playerCount;

		ArrayList<WindowFrameCouple> temp = new ArrayList<>(mainBoardData.windowFrameCouples);
		windowFrameCouples = Collections.unmodifiableList(temp);

		this.gameState = mainBoardData.gameState;

		this.currentFirstPlayer = currentFirstPlayer;

		this.currentPlayer = currentPlayer;

		this.currentRound = currentRound;

		this.publicObjectiveCard = mainBoardData.publicObjectiveCard;
	}

	/**
	 * Constructor to set publicObjectiveCards
	 * @param mainBoardData previous mainBoardData
	 * @param publicObjectiveCard to set
	 */
	private MainBoardData(MainBoardData mainBoardData, AbstractPublicObjectiveCard[] publicObjectiveCard)
	{
		this.playerCount = mainBoardData.playerCount;

		ArrayList<WindowFrameCouple> temp = new ArrayList<>(mainBoardData.windowFrameCouples);
		windowFrameCouples = Collections.unmodifiableList(temp);

		this.gameState = mainBoardData.gameState;

		this.currentFirstPlayer = mainBoardData.currentFirstPlayer;

		this.currentPlayer = mainBoardData.currentPlayer;

		this.currentRound = mainBoardData.currentRound;

		this.publicObjectiveCard = publicObjectiveCard;
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
	 * @return the current game state
	 */
	public AbstractGameState getGameState()
	{
		return gameState;
	}

	public int getCurrentRound()
	{
		return currentRound;
	}

	/**
	 * Set number of player
	 * @param playerCount the new player count
	 */
	MainBoardData setPlayerCount(int playerCount)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer , currentRound);
	}

	/**
	 * Set gameState
	 * @param state the new state of the game
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
	 * Set current first player
	 * @param currentFirstPlayer to set
	 * @return new MainBoardData with modified currentFirstPlayer
	 */
	MainBoardData setCurrentFirstPlayer(int currentFirstPlayer)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer, currentRound);
	}

	/**
	 * Set current player
	 * @param currentPlayer to set
	 * @return new MainBoardData with modified currentPlayer
	 */
	MainBoardData setCurrentPlayer(int currentPlayer)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer, currentRound);
	}

	/**
	 * Set current round
	 * @param currentRound to set
	 * @return new MainBoardData with modified currentRound
	 */
	MainBoardData setCurrentRound(int currentRound)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer, currentRound);
	}

	MainBoardData setPublicObjectiveCards(AbstractPublicObjectiveCard[] publicObjectiveCards)
	{
		return new MainBoardData(this, publicObjectiveCards);
	}

	AbstractPublicObjectiveCard[] getPublicObjectiveCards()
	{
		return publicObjectiveCard;
	}

}
