package progetto.controller;

import progetto.model.*;

/**
 * Action to execute tool card 12
 * @author Michele
 */
public class ExecuteToolCard12Action extends AbstractExecutibleGameAction{

	private static final int CARD12 = 12;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard12Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param callerID ID of the caller
	 */
	public ExecuteToolCard12Action(int callerID)
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

		if(!verifyParam(game))
		{
			return false;
		}

		int round = roundInformationData.getToolCardParameters().getRound();
		int nDiceRT = roundInformationData.getToolCardParameters().getNDiceRT();

		Dice diceRT = game.getRoundTrack().getData().getDice(round, nDiceRT);

		int xPos = roundInformationData.getToolCardParameters().getXPlacedDice();
		int yPos = roundInformationData.getToolCardParameters().getYPlacedDice();
		Dice dice = null;

		int xPos2 = roundInformationData.getToolCardParameters().getXPlacedDice2();
		int yPos2 = roundInformationData.getToolCardParameters().getYPlacedDice2();
		Dice dice2 = null;

		if(xPos!=-1 && yPos!=-1)
		{
			dice = game.getPlayerBoard(getCallerID()).getDicePlacedFrame().getData().getDice(yPos, xPos);
		}

		if(xPos2!=-1 && yPos2!=-1)
		{
			dice2 = game.getPlayerBoard(getCallerID()).getDicePlacedFrame().getData().getDice(yPos2, xPos2);
		}

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		return cardState.getIndex() == CARD12 && verifyDiceColor(diceRT, dice, dice2)
				&& (!(dice==null && dice2==null));

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		RoundInformation roundInformation = game.getRoundInformation();

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		Dice dice;

		int xPos = roundInformation.getData().getToolCardParameters().getXPlacedDice();
		int yPos = roundInformation.getData().getToolCardParameters().getYPlacedDice();

		if(xPos!=-1 && yPos!=-1)
		{
			dice = playerBoard.getDicePlacedFrame().removeDice(yPos, xPos);
			playerBoard.getPickedDicesSlot().add(dice, false, false, false);

		}

		int xPos2 = roundInformation.getData().getToolCardParameters().getXPlacedDice2();
		int yPos2 = roundInformation.getData().getToolCardParameters().getYPlacedDice2();

		if(xPos2!=-1 && yPos2!=-1)
		{
			dice = playerBoard.getDicePlacedFrame().removeDice(yPos2, xPos2);
			playerBoard.getPickedDicesSlot().add(dice, false, false, false);
		}

	}

	/**
	 * Support method to verify parameters of the tool card
	 * @param game model in use
	 * @return result of the check
	 */
	private boolean verifyParam(IModel game)
	{
		RoundInformationData roundInformationData = game.getRoundInformation().getData();
		int currentPlayer = game.getRoundInformation().getData().getCurrentPlayer();

		int xPos = roundInformationData.getToolCardParameters().getXPlacedDice();
		int yPos = roundInformationData.getToolCardParameters().getYPlacedDice();
		int xPos2 = roundInformationData.getToolCardParameters().getXPlacedDice2();
		int yPos2 = roundInformationData.getToolCardParameters().getYPlacedDice2();

		if(xPos==-1 && yPos==-1 && xPos2==-1 && yPos2==-1)
		{
			return false;
		}

		int round = roundInformationData.getToolCardParameters().getRound();
		int nDiceRT = roundInformationData.getToolCardParameters().getNDiceRT();

		return currentPlayer == getCallerID() &&
				game.getMainBoard().getData().getGameState().getClass() == ToolCardState.class &&
				round!=-1 && nDiceRT!=-1;

	}

	/**
	 * Supporto method to verify dice color
	 * @param diceRT dice of the roundTrack
	 * @param dice first dice selected
	 * @param dice2 second dice selected
	 * @return result of the check
	 */
	private boolean verifyDiceColor(Dice diceRT, Dice dice, Dice dice2)
	{
		return !((dice!=null && dice.getGameColor()!=diceRT.getGameColor()) ||
				(dice2!=null && dice2.getGameColor()!=diceRT.getGameColor()));
	}

}
