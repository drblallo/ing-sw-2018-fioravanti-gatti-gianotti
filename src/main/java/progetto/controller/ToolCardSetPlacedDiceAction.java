package progetto.controller;

import progetto.model.IModel;
import progetto.model.MainBoardData;
import progetto.model.Model;
import progetto.model.ToolCardState;

/**
 * Action to select a dice from placed dice frame
 */
public class ToolCardSetPlacedDiceAction extends AbstractExecutibleGameAction{

	private final int x;
	private final int y;

	/**
	 * Constructor without parameters
	 */
	public ToolCardSetPlacedDiceAction()
	{
		super();
		x = -1;
		y = -1;

	}

	/**
	 * Constructor to set values
	 * @param nPlayer callerID
	 * @param y y - pos in dice placed frame
	 * @param x x - pos in dice placed frame
	 */
	public ToolCardSetPlacedDiceAction(int nPlayer, int y, int x)
	{
		super(nPlayer);
		this.x = x;
		this.y = y;

	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game) {
		MainBoardData data = game.getMainBoard().getData();

		if(data.getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		return getCallerID()==game.getRoundInformation().getData().getCurrentPlayer() &&
				game.getPlayerBoard(getCallerID()).getDicePlacedFrame().getData().getDice(y, x)!=null ;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		game.getRoundInformation().setYXValues(y, x);

	}

}
