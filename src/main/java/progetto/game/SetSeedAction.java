package progetto.game;

public class SetSeedAction extends AbstractGameAction
{

	private final int seed;

	public SetSeedAction(int seed)
	{
		super(-1);
		this.seed = seed;
	}

	SetSeedAction()
	{
		super(-1);
		this.seed = 0;
	}

	/**
	 *
	 * @param game the game in which this command should be executed
	 * @return true if it's the pregame state , false otherwise
	 */
	@Override
	public boolean canBeExecuted(Game game)
	{
		return (game.getGameState().getClass() == PreGameState.class);
	}

	/**
	 * Set the current seed of the game to the provided one.
	 * @param game the game in which we want to execute this command
	 */
	@Override
	protected void execute(Game game)
	{
		game.setSeed(seed);
	}
}
