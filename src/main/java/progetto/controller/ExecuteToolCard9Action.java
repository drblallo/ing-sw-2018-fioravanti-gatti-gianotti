package progetto.controller;

import progetto.model.*;

/**
 * Action to execute tool card 9
 * @author Michele
 */
public class ExecuteToolCard9Action extends AbstractExecutibleGameAction{

	private static final int CARD9 = 9;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard9Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param callerID
	 */
	public ExecuteToolCard9Action(int callerID)
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
		RoundInformationData roundInformationData = game.getRoundInformation().getData();
		int currentPlayer = game.getRoundInformation().getData().getCurrentPlayer();

		int nDice = roundInformationData.getToolCardParameters().getNDice();

		if(currentPlayer != getCallerID() || nDice==-1 ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		return cardState.getIndex() == CARD9 &&
				game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getData().getDicePlacementCondition(nDice) != null;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		RoundInformation roundInformation = game.getRoundInformation();
		int nDice = roundInformation.getData().getToolCardParameters().getNDice();

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		playerBoard.getPickedDicesSlot().setIgnoreAdjacent(nDice, true);

	}

}
