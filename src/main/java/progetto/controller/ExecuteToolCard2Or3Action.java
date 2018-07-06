package progetto.controller;

import progetto.model.*;

/**
 * Action to execute tool card 2 ore 3
 * @author Michele
 */
public class ExecuteToolCard2Or3Action extends AbstractExecutibleGameAction{

	private static final int CARD2 = 2;
	private static final int CARD3 = 3;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard2Or3Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param callerID
	 */
	public ExecuteToolCard2Or3Action(int callerID)
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

		int xPos = roundInformationData.getToolCardParameters().getXPlacedDice();
		int yPos = roundInformationData.getToolCardParameters().getYPlacedDice();

		if(currentPlayer != getCallerID() || xPos==-1 || yPos==-1 ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		return (cardState.getIndex() == CARD2 || cardState.getIndex() == CARD3) &&
				game.getPlayerBoard(getCallerID()).getDicePlacedFrame().getData().getDice(yPos, xPos) != null;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		RoundInformation roundInformation = game.getRoundInformation();
		int xPos = roundInformation.getData().getToolCardParameters().getXPlacedDice();
		int yPos = roundInformation.getData().getToolCardParameters().getYPlacedDice();

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		Dice dice = playerBoard.getDicePlacedFrame().removeDice(yPos, xPos);

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();
		if(cardState.getIndex() == CARD2)
		{
			playerBoard.getPickedDicesSlot().add(dice, true, false, false);
		}
		else if(cardState.getIndex() == CARD3)
		{
			playerBoard.getPickedDicesSlot().add(dice, false, true, false);
		}


	}

}
