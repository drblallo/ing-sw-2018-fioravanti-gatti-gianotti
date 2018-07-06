package progetto.model;

/**
 * add a window frame couple to the model
 * @author Michele
 */
public class AddWindowFrameCoupleAction extends AbstractGameAction
{
	private final WindowFrameCouple couple;

	/**
	 * public constructor
	 * @param couple couple of window frames that must be added to the usable window frame
	 */
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
	public boolean canBeExecuted(IModel game) {
		return game.getMainBoard().getData().getGameState().getClass()==PreGameState.class;
	}

	/**
	 * add the couple provided at construction
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game) {
		game.getMainBoard().addWindowFrameCouple(couple);
	}
}
