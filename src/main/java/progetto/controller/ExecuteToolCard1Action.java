package progetto.controller;

import progetto.model.*;

/**
 * Action to execute tool card 1
 * @author Michele
 */
public class ExecuteToolCard1Action extends AbstractExecutibleGameAction{

	private static final int INDEX = 1;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard1Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param callerID ID of the caller
	 */
	public ExecuteToolCard1Action(int callerID)
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
		int increaseDecrease = roundInformationData.getToolCardParameters().getIncreaseDecrease();

		if(currentPlayer != getCallerID() || nDice==-1 || increaseDecrease==-1 ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(currentPlayer).getPickedDicesSlot().getData()
				.getDicePlacementCondition(nDice);

		return cardState.getIndex() == INDEX &&
				dicePlacementCondition != null && (increaseDecrease == 0 || increaseDecrease == 1);

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
		int increaseDecrease = roundInformation.getData().getToolCardParameters().getIncreaseDecrease();

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		DicePlacementCondition dicePlacementCondition = playerBoard.getPickedDicesSlot()
				.getData().getDicePlacementCondition(nDice);

		Dice dice = dicePlacementCondition.getDice();

		if(increaseDecrease == 0)
		{
			dice = dice.increaseValue();
		}
		else
		{
			dice = dice.decreaseValue();
		}

		playerBoard.getPickedDicesSlot().changeDice(nDice, dice);

	}

}
