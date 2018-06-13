package progetto.controller;

import progetto.model.*;

/**
 * Action to place a dice in placed window frame
 */
public class PlaceDiceAction extends AbstractExecutibleGameAction{

	private int nDice;

	private int x;
	private int y;

	/**
	 * Constructor without parameters
	 */
	public PlaceDiceAction(){

		super(-1);
		this.x = -1;
		this.y = -1;

	}

	/**
	 * Constructor to set values
	 * @param nPlayer callerID
	 * @param nDice number of the picked dice to place
	 * @param y y pos in window frame
	 * @param x x pos in window frame
	 */
	public PlaceDiceAction(int nPlayer, int nDice, int y, int x)
	{
		super(nPlayer);
		this.nDice = nDice;
		this.x = x;
		this.y = y;
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game)
	{
		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(getCallerID())
				.getPickedDicesSlot().getData().getDicePlacementCondition(nDice);
		AbstractPlayerBoard playerBoard = game.getPlayerBoard(getCallerID());
		WindowFrame windowFrame = playerBoard.getData().getWindowFrame();
		DicePlacedFrameData dicePlacedFrameData = playerBoard.getDicePlacedFrame().getData();

		return game.getMainBoard().getData().getGameState().getClass() == RoundState.class &&
				dicePlacementCondition!=null &&
				dicePlacementCondition.canBePlaced(y, x, windowFrame, dicePlacedFrameData);

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		Dice dice = playerBoard.getPickedDicesSlot().remove(nDice).getDice();
		playerBoard.addDiceInPlacedFrame(dice, y, x);

	}
}
