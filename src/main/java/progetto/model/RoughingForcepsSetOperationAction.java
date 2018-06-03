package progetto.model;

public class RoughingForcepsSetOperationAction  extends AbstractGameAction {

	private int increaseDecrease;

	/**
	 * Constructor
	 * @param increaseDecrease  0 -> increase, 1 -> decrease
	 */
	RoughingForcepsSetOperationAction(int increaseDecrease)
	{
		this.increaseDecrease = increaseDecrease;
	}

	@Override
	public boolean canBeExecuted(Model game)
	{
		return game.getMainBoard().getData().getGameState().getClass() == RoughingForcepsState.class &&
				(increaseDecrease==0 || increaseDecrease==1);
	}

	@Override
	public void execute(Model game)
	{
		game.getMainBoard().setParamToolCard("increaseDecrease", increaseDecrease);
	}

}
