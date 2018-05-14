package progetto.game;

public class FrameSetAction extends AbstractGameAction {

	private final int selectedWindowFrame;

	public FrameSetAction(){

		super(-1);
		selectedWindowFrame = -1;

	}

	public FrameSetAction(int selectedWindowFrame)
	{
		this.selectedWindowFrame = selectedWindowFrame;
	}

	@Override
	public boolean canBeExecuted(Game game) {
		return (game.getMainBoard().getMainBoardData().getGameState().getClass() == FrameSelectionState.class);
	}

	@Override
	protected void execute(Game game)
	{
		//selected window frame in PlayerBoard
		//other window frames in mainBoard

		game.setState(new GameStartedState());
	}
}
