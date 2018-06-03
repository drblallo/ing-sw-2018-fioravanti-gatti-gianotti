package progetto.controller;

import progetto.model.FrameSelectionState;
import progetto.model.Model;
import progetto.model.PreGameState;

/**
 * starts the model
 */
public class StartGameAction extends AbstractExecutibleGameAction
{
	public StartGameAction() {
		super(-1);
	}


	@Override
	public boolean canBeExecuted(Model game) {
		return game.getMainBoard().getData().getGameState().getClass() == PreGameState.class;
	}

	@Override
	public void execute(Model game) {
		game.setState(new FrameSelectionState());

	}
}
