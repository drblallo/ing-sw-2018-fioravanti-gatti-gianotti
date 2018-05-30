package progetto.game;

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
	public boolean canBeExecuted(Game game)
	{
		return game.getMainBoard().getData().getGameState().getClass() == RoughingForcepsState.class &&
				(increaseDecrease==0 || increaseDecrease==1);
	}

	@Override
	protected void execute(Game game)
	{
		game.getMainBoard().setParamToolCard("increaseDecrease", increaseDecrease);
	}

}
