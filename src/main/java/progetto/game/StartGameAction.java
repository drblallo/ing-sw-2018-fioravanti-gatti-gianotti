package progetto.game;

/**
 * starts the game
 */
public class StartGameAction extends AbstractGameAction
{
	public StartGameAction() {
		super(-1);
	}


	@Override
	public boolean canBeExecuted(Game game) {
		return game.getMainBoard().getMainBoardData().getGameState().getClass() == PreGameState.class;
	}

	@Override
	protected void execute(Game game) {
		game.getMainBoard().setGameState(new FrameSelectionState());

	}
}
