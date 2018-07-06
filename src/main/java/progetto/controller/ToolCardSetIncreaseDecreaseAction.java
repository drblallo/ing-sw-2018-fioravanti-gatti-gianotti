package progetto.controller;

import progetto.model.IModel;
import progetto.model.MainBoardData;
import progetto.model.Model;
import progetto.model.ToolCardState;

/**
 * Action to choose whether to increase or decrease the value of the dice
 * @author Michele
 */
public class ToolCardSetIncreaseDecreaseAction extends AbstractExecutibleGameAction{

	private int increaseDecrease;

	/**
	 * Constructor without parameters
	 */
	public  ToolCardSetIncreaseDecreaseAction()
	{
		super();
	}

	/**
	 * Constructor to set values
	 * @param nPlayer callerID
	 * @param increaseDecrease  0 = increase, 1 = decrease
	 */
	public ToolCardSetIncreaseDecreaseAction(int nPlayer, int increaseDecrease)
	{
		super(nPlayer);
		this.increaseDecrease = increaseDecrease;
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game)
	{
		MainBoardData data = game.getMainBoard().getData();

		if(data.getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		return (increaseDecrease == 0 || increaseDecrease == 1) &&
				getCallerID()==game.getRoundInformation().getData().getCurrentPlayer() ;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		game.getRoundInformation().setIncreaseDecrease(increaseDecrease);
	}

}
