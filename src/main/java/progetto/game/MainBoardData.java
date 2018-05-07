package progetto.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MainBoardData {

	private static final int MAX_NUM_PLAYERS = 4;

	private final List<WindowFrameCouple> windowFrameCouples;
	private final int playerCount;
	private final AbstractGameState gameState;

	MainBoardData()
	{
		playerCount = MAX_NUM_PLAYERS;
		ArrayList<WindowFrameCouple> temp = new ArrayList<>();
		windowFrameCouples = Collections.unmodifiableList(temp);
		gameState = new PreGameState();
	}

	MainBoardData(MainBoardData mainBoardData, int playerCount)
	{
		this.playerCount = playerCount;

		ArrayList<WindowFrameCouple> temp = new ArrayList<>(mainBoardData.windowFrameCouples);
		windowFrameCouples = Collections.unmodifiableList(temp);

		gameState = mainBoardData.gameState;
	}

	MainBoardData(MainBoardData mainBoardData, AbstractGameState gameState)
	{
		this.playerCount = mainBoardData.playerCount;

		ArrayList<WindowFrameCouple> temp = new ArrayList<>(mainBoardData.windowFrameCouples);
		windowFrameCouples = Collections.unmodifiableList(temp);

		this.gameState = gameState;
	}

	MainBoardData(MainBoardData mainBoardData, WindowFrameCouple windowFrameCouple)
	{
		this.playerCount = mainBoardData.playerCount;

		ArrayList<WindowFrameCouple> temp = new ArrayList<>(mainBoardData.windowFrameCouples);
		temp.add(windowFrameCouple);
		this.windowFrameCouples = Collections.unmodifiableList(temp);

		this.gameState = mainBoardData.gameState;
	}

	/**
	 *
	 * @return the current player count
	 */
	public int getPlayerCount()
	{
		return playerCount;
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

	/**
	 *
	 * @param playerCount the new player count
	 */
	MainBoardData setPlayerCount(int playerCount)
	{
		return new MainBoardData(this, playerCount);
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

}
