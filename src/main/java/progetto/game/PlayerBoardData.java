package progetto.game;

public final class PlayerBoardData {

	private final WindowFrame windowFrame;

	PlayerBoardData()
	{
		windowFrame = null;
	}

	private PlayerBoardData(WindowFrame windowFrame)
	{
		this.windowFrame = windowFrame;
	}

	public WindowFrame getWindowFrame()
	{
		return windowFrame;
	}

	PlayerBoardData setWindowFrame(WindowFrame windowFrame)
	{
		return new PlayerBoardData(windowFrame);
	}



}
