package progetto.controller;

import progetto.model.IModel;
import progetto.model.Model;
import progetto.model.PreGameState;

public class SetSeedAction extends AbstractExecutibleGameAction
{

	private final int seed;

	public SetSeedAction(int seed)
	{
		super(-1);
		this.seed = seed;
	}

	public SetSeedAction()
	{
		super(-1);
		this.seed = 0;
	}

	/**
	 *
	 * @param game the model in which this command should be executed
	 * @return true if it's the pregame state , false otherwise
	 */
	@Override
	public boolean canBeExecuted(IModel game)
	{
		return (game.getMainBoard().getData().getGameState().getClass() == PreGameState.class);
	}

	/**
	 * Set the current seed of the model to the provided one.
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		game.setSeed(seed);
	}
}
