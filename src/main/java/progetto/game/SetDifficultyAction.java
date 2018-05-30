package progetto.game;

public class SetDifficultyAction extends AbstractGameAction
{
	private final int difficulty;

	public SetDifficultyAction(int difficulty)
	{
		super(-1);
		this.difficulty = difficulty;
	}

	@Override
	public boolean canBeExecuted(Game game) {
		return (game.getMainBoard().getData().getGameState().getClass() == PreGameState.class);
	}

	@Override
	protected void execute(Game game) {
		game.getMainBoard().setDifficulty(difficulty);
	}
}
