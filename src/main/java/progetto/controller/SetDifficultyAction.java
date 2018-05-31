package progetto.controller;

import progetto.model.Game;
import progetto.model.PreGameState;

public class SetDifficultyAction extends AbstractExecutibleGameAction
{
	private final int difficulty;

	public SetDifficultyAction()
	{
		super(-1);
		difficulty = 1;
	}

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
	public void execute(Game game) {
		game.getMainBoard().setDifficulty(difficulty);
	}
}
