package progetto.controller;

import progetto.model.FrameSelectionState;
import progetto.model.Game;
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
	public boolean canBeExecuted(Game game) {
		return game.getMainBoard().getData().getGameState().getClass() == PreGameState.class;
	}

	@Override
	public void execute(Game game) {
		game.setState(new FrameSelectionState());

	}
}
