package progetto.game;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FrameSetAction extends AbstractGameAction {

	private final int selectedCouple;
	private final int selectedSide;

	private static final Logger LOGGER = Logger.getLogger(FrameSetAction.class.getName());

	public FrameSetAction(){

		super(-1);
		this.selectedCouple = 0;
		this.selectedSide = 0;

	}

	public FrameSetAction(int nPlayer, int selectedCouple, int selectedSide)
	{
		super(nPlayer);
		this.selectedCouple = selectedCouple;
		this.selectedSide = selectedSide;
	}

	@Override
	public boolean canBeExecuted(Game game) {
		return (game.getMainBoard().getData().getGameState().getClass() == FrameSelectionState.class);
	}

	@Override
	protected void execute(Game game)
	{
		LOGGER.log(Level.FINE, "WindowFrame {0}", selectedCouple);

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		//selected window frame in PlayerBoard
		if(selectedCouple==-1)
		{
			playerBoard.setEmptyWindowFrame();
		}
		playerBoard.setWindowFrame(selectedCouple, selectedSide);

		boolean allSet = true;

		for(int i=0; i<game.getMainBoard().getData().getPlayerCount(); i++)
		{
			allSet = allSet && game.getPlayerBoard(i).getData().getWindowFrameIsSet();
		}

		if(allSet)
		{
			game.setState(new GameStartedState());
		}

	}

}
