package progetto.controller;

import progetto.model.FrameSelectionState;
import progetto.model.IModel;
import progetto.model.Model;
import progetto.model.PreGameState;

/**
 * starts the model
 */
public class StartGameAction extends AbstractExecutibleGameAction
{

	/**
	 * Constructor
	 */
	public StartGameAction() {
		super(-1);
	}


	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game) {
		return game.getMainBoard().getData().getGameState().getClass() == PreGameState.class;
	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game) {
		game.setState(new FrameSelectionState());

	}
}
