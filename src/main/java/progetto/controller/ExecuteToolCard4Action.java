package progetto.controller;

import progetto.model.*;

/**
 * Action to execute tool card 4
 */
public class ExecuteToolCard4Action extends AbstractExecutibleGameAction{

	private static final int INDEX = 4;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard4Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param nPlayer
	 */
	public ExecuteToolCard4Action(int nPlayer)
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
		RoundInformationData roundInformationData = game.getRoundInformation().getData();
		int currentPlayer = game.getRoundInformation().getData().getCurrentPlayer();

		int xPos = roundInformationData.getToolCardParameters().getXPlacedDice();
		int yPos = roundInformationData.getToolCardParameters().getYPlacedDice();

		int xPos2 = roundInformationData.getToolCardParameters().getXPlacedDice2();
		int yPos2 = roundInformationData.getToolCardParameters().getYPlacedDice2();

		if(currentPlayer != getCallerID() || xPos==-1 || yPos==-1
				|| xPos2==-1 || yPos2==-1 ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		return cardState.getIndex() == INDEX &&
				game.getPlayerBoard(getCallerID()).getDicePlacedFrame().getData().getDice(yPos, xPos) != null &&
				game.getPlayerBoard(getCallerID()).getDicePlacedFrame().getData().getDice(yPos2, xPos2) != null;

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

		int xPos2 = roundInformation.getData().getToolCardParameters().getXPlacedDice2();
		int yPos2 = roundInformation.getData().getToolCardParameters().getYPlacedDice2();

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		Dice dice = playerBoard.getDicePlacedFrame().removeDice(yPos, xPos);

		playerBoard.getPickedDicesSlot().add(dice, false, false, false);

		dice = playerBoard.getDicePlacedFrame().removeDice(yPos2, xPos2);

		playerBoard.getPickedDicesSlot().add(dice, false, false, false);

	}

}
