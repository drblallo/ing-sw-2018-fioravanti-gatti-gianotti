package progetto.game;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * starts the game
 */
public class StartGameAction extends AbstractGameAction
{
	private static final Logger LOGGER = Logger.getLogger(StartGameAction.class.getName());
	public StartGameAction() {
		super(-1);
	}


	@Override
	public boolean canBeExecuted(Game game) {
		return game.getGameState().getClass() == PreGameState.class;
	}

	@Override
	protected void execute(Game game) {
		game.setGameState(new FrameSelectionState());

		LOGGER.log(Level.SEVERE, "MIKEEEEEEE QUAAAAA VA FAAAAATTTO IL SEEEETUUUUUUP");
	}
}
