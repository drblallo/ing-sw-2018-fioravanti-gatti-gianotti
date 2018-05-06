package progetto.game;

public class PlayerBoardData {

	private final WindowFrame windowFrame;

	PlayerBoardData()
	{
		windowFrame = null;
	}

	PlayerBoardData(WindowFrame windowFrame)
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
