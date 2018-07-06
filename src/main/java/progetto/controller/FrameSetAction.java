package progetto.controller;

import progetto.model.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Action to set window frame at the begin of the game
 * @author Michele
 */
public class FrameSetAction extends AbstractExecutibleGameAction {

	private final int selectedCouple;
	private final int selectedSide;

	private static final Logger LOGGER = Logger.getLogger(FrameSetAction.class.getName());

	/**
	 * Constructor without parameters
	 */
	public FrameSetAction(){

		super(-1);
		this.selectedCouple = 0;
		this.selectedSide = 0;

	}

	/**
	 * Constructor to set values
	 * @param callerID  callerID
	 * @param selectedCouple  selected window frame card
	 * @param selectedSide selected side of the card
	 */
	public FrameSetAction(int callerID, int selectedCouple, int selectedSide)
	{
		super(callerID);

		this.selectedCouple = selectedCouple;
		this.selectedSide = selectedSide;
	}

	/**
	 * Verify if the action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game) {
		return (game.getMainBoard().getData().getGameState().getClass() == FrameSelectionState.class);
	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		LOGGER.log(Level.FINE, "WindowFrame {0}", selectedCouple);

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		//selected window frame in PlayerBoard
		if(selectedCouple==-1)
		{
			playerBoard.setEmptyWindowFrame();
		}
		playerBoard.setWindowFrame(selectedCouple, selectedSide);

		playerBoard.setToken(playerBoard.getData().getWindowFrame().getFavorToken());

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
