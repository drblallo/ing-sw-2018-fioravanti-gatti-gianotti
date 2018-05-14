package progetto.game;

/**
 * Immutable class with data of PlayerBoard
 */
public final class PlayerBoardData {

	private final WindowFrame windowFrame;

	/**
	 * consutructor
	 */
	PlayerBoardData()
	{
		windowFrame = null;
	}

	/**
	 * Constructor to set windowFrame
	 * @param windowFrame windowFrame to set
	 */
	private PlayerBoardData(WindowFrame windowFrame)
	{
		this.windowFrame = windowFrame;
	}

	/**
	 * Get player window frame
	 * @return window frame
	 */
	public WindowFrame getWindowFrame()
	{
		return windowFrame;
	}

	/**
	 * Set player window frame
	 * @param windowFrame window frame to set
	 * @return new PlayerBoardData with the window frame set
	 */
	PlayerBoardData setWindowFrame(WindowFrame windowFrame)
	{
		return new PlayerBoardData(windowFrame);
	}


}
