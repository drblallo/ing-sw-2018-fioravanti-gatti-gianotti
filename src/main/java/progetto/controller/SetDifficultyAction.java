package progetto.controller;

import progetto.model.Model;
import progetto.model.PreGameState;

/**
 * Action to set difficulty (used in Single Player game)
 */
public class SetDifficultyAction extends AbstractExecutibleGameAction
{
	private final int difficulty;

	/**
	 * Constructor without parameters
	 */
	public SetDifficultyAction()
	{
		super(-1);
		difficulty = 1;
	}

	/**
	 * Constructor to set difficulty
	 * @param difficulty
	 */
	public SetDifficultyAction(int difficulty)
	{
		super(-1);
		this.difficulty = difficulty;
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(Model game) {
		return (game.getMainBoard().getData().getGameState().getClass() == PreGameState.class);
	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game) {
		game.getMainBoard().setDifficulty(difficulty);
	}
}
