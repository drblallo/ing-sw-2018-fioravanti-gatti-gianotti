package progetto.controller;

import progetto.model.Model;
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
	public boolean canBeExecuted(Model game) {
		return (game.getMainBoard().getData().getGameState().getClass() == PreGameState.class);
	}

	@Override
	public void execute(Model game) {
		game.getMainBoard().setDifficulty(difficulty);
	}
}
