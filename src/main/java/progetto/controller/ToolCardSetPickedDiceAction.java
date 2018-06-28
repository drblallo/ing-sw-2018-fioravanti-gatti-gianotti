package progetto.controller;

import progetto.model.IModel;
import progetto.model.MainBoardData;
import progetto.model.Model;
import progetto.model.ToolCardState;

/**
 * Action to select a picked dice
 */
public class ToolCardSetPickedDiceAction extends AbstractExecutibleGameAction{

	private final int nDice;

	/**
	 * Constructor without parameters
	 */
	public ToolCardSetPickedDiceAction()
	{
		super();
		nDice = -1;

	}

	/**
	 * Constructor to set values
	 * @param nPlayer callerID
	 * @param nDice n dice in picked dice slot
	 */
	public ToolCardSetPickedDiceAction(int nPlayer, int nDice)
	{
		super(nPlayer);
		this.nDice = nDice;

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
				data.getGameState().getClass() == ToolCardState.class &&
				game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getData().getNDices() > nDice &&
				nDice >= 0 ;
	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		game.getRoundInformation().setNDice(nDice);
	}

}
