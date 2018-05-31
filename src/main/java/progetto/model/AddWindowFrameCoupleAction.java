package progetto.model;

/**
 * add a window frame couple to the model
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
	 * @param game the model in which this command should be executed
	 * @return true if the model is in the pre model state
	 */
	@Override
	public boolean canBeExecuted(Game game) {
		return game.getMainBoard().getData().getGameState().getClass()==PreGameState.class;
	}

	/**
	 * add the couple provided at construction
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Game game) {
		game.getMainBoard().addWindowFrameCouple(couple);
	}
}
