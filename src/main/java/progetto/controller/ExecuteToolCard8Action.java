package progetto.controller;

import progetto.model.IModel;
import progetto.model.Model;
import progetto.model.ToolCardState;

import java.util.List;

/**
 * Action to execute tool card 8
 * @author Michele
 */
public class ExecuteToolCard8Action extends AbstractExecutibleGameAction{

	private static final int INDEX = 8;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard8Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param callerID
	 */
	public ExecuteToolCard8Action(int callerID)
	{
		super(callerID);
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game)
	{
		int currentPlayer = game.getRoundInformation().getData().getCurrentPlayer();

		if(currentPlayer != getCallerID() ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();



		List<Integer> playerQueue = game.getRoundInformation().getData().getRoundPlayerList();
		int nPlayer = getCallerID();
		boolean found = false;

		while(!playerQueue.isEmpty())
		{
			int nextP = playerQueue.remove(0);
			if (nextP == nPlayer)   //check first turn of the player
			{
				found = true;
			}
		}

		return cardState.getIndex() == INDEX && found;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		game.getRoundInformation().changeNextPlayer(getCallerID());

	}

}
