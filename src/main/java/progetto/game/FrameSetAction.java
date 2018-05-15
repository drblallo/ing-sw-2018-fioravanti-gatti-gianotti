package progetto.game;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FrameSetAction extends AbstractGameAction {

	private final int selectedWindowFrame;
	private static final Logger LOGGER = Logger.getLogger(FrameSetAction.class.getName());

	public FrameSetAction(int selectedWindowFrame)
	{
		this.selectedWindowFrame = selectedWindowFrame;
	}

	@Override
	public boolean canBeExecuted(Game game) {
		return (game.getMainBoard().getMainBoardData().getGameState().getClass() == FrameSelectionState.class);
	}

	@Override
	protected void execute(Game game)
	{
		LOGGER.log(Level.FINE, "WindowFrame ", selectedWindowFrame);
		//selected window frame in PlayerBoard
		//other window frames in mainBoard
		game.setState(new GameStartedState());
	}
}
