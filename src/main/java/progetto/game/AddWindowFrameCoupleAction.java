package progetto.game;

/**
 * add a window frame couple to the game
 */
public class AddWindowFrameCoupleAction extends AbstractGameAction
{
	private final WindowFrameCouple couple;

	public AddWindowFrameCoupleAction(WindowFrameCouple couple)
	{
		super(-1);
		this.couple = couple;
	}

	/**
	 *
	 * @param game the game in which this command should be executed
	 * @return true if the game is in the pre game state
	 */
	@Override
	public boolean canBeExecuted(Game game) {
		return game.getMainBoard().getData().getGameState().getClass()==PreGameState.class;
	}

	/**
	 * add the couple provided at construction
	 * @param game the game in which we want to execute this command
	 */
	@Override
	protected void execute(Game game) {
		game.getMainBoard().addWindowFrameCouple(couple);
	}
}
