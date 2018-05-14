package progetto.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MainBoardData {

	public static final int MAX_NUM_PLAYERS = 4;

	private final List<WindowFrameCouple> windowFrameCouples;
	private final int playerCount;
	private final AbstractGameState gameState;
	private final int currentFirstPlayer;
	private final int currentPlayer;
	private final int currentRound;

	MainBoardData()
	{
		playerCount = MAX_NUM_PLAYERS;
		ArrayList<WindowFrameCouple> temp = new ArrayList<>();
		windowFrameCouples = Collections.unmodifiableList(temp);
		gameState = new PreGameState();
		currentFirstPlayer = 0;
		currentPlayer = 0;
		currentRound = 1;
	}

	private MainBoardData(MainBoardData mainBoardData, AbstractGameState gameState)
	{
		this.playerCount = mainBoardData.playerCount;

		ArrayList<WindowFrameCouple> temp = new ArrayList<>(mainBoardData.windowFrameCouples);
		windowFrameCouples = Collections.unmodifiableList(temp);

		this.gameState = gameState;

		this.currentFirstPlayer = mainBoardData.currentFirstPlayer;

		this.currentPlayer = mainBoardData.currentPlayer;

		this.currentRound = mainBoardData.currentRound;
	}

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
	}

	private MainBoardData(MainBoardData mainBoardData, int playerCount, int currentFirstPlayer, int currentPlayer, int currentRound)
	{
		this.playerCount = playerCount;

		ArrayList<WindowFrameCouple> temp = new ArrayList<>(mainBoardData.windowFrameCouples);
		windowFrameCouples = Collections.unmodifiableList(temp);

		this.gameState = mainBoardData.gameState;

		this.currentFirstPlayer = currentFirstPlayer;

		this.currentPlayer = currentPlayer;

		this.currentRound = currentRound;
	}


	/**
	 *
	 * @return the current player count
	 */
	public int getPlayerCount()
	{
		return playerCount;
	}

	public int getCurrentFirstPlayer()
	{
		return currentFirstPlayer;
	}

	public int getCurrentPlayer()
	{
		return currentPlayer;
	}

	WindowFrameCouple getWindowFrame(int index)
	{
		return windowFrameCouples.get(index);
	}

	/**
	 *
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
	 *
	 * @param playerCount the new player count
	 */
	MainBoardData setPlayerCount(int playerCount)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer , currentRound);
	}

	/**
	 *
	 * @param state the new state of the game
	 */
	MainBoardData setGameState(AbstractGameState state)
	{
		return new MainBoardData(this, state);
	}

	MainBoardData addWindowFrameCouple(WindowFrameCouple windowFrameCouple)
	{
		return new MainBoardData(this, windowFrameCouple);
	}

	MainBoardData setCurrentFirstPlayer(int currentFirstPlayer)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer, currentRound);
	}

	MainBoardData setCurrentPlayer(int currentPlayer)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer, currentRound);
	}

	MainBoardData setCurrentRound(int currentRound)
	{
		return new MainBoardData(this, playerCount, currentFirstPlayer, currentPlayer, currentRound);
	}


}
