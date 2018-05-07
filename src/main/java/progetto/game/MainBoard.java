package progetto.game;

import progetto.utils.AbstractObservable;

/**
 * Main gaming table
 */
public final class MainBoard extends AbstractObservable<MainBoardData> {

	private MainBoardData mainBoardData = new MainBoardData();
	private final ExtractedDices extractedDices = new ExtractedDices();

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

}
