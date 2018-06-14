package progetto.controller;

import progetto.model.IModel;
import progetto.model.Model;
import progetto.model.RoundState;
import progetto.model.ToolCardState;

/**
 * Action to cancel the use of a tool card
 */
public class CancelToolCardUseAction extends AbstractExecutibleGameAction
{

	/**
	 * Constructor with no parameters
	 */
	public CancelToolCardUseAction(){

		super(-1);

	}

	/**
	 * Constructor to se nPlayer
	 * @param nPlayer
	 */
	public CancelToolCardUseAction(int nPlayer)
	{
		super(nPlayer);
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game)
	{
		return game.getMainBoard().getData().getGameState().getClass() == ToolCardState.class &&
				getCallerID() == game.getMainBoard().getData().getCurrentPlayer();
	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		game.getMainBoard().delParamToolCard();
		game.setState(new RoundState());

	}
}

