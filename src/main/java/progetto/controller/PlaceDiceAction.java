package progetto.controller;

import progetto.model.*;

public class PlaceDiceAction extends AbstractExecutibleGameAction{

	private int nDice;

	private int x;
	private int y;

	public PlaceDiceAction(){

		super(-1);
		this.x = -1;
		this.y = -1;

	}

	public PlaceDiceAction(int nPlayer, int nDice, int y, int x)
	{
		super(nPlayer);
		this.nDice = nDice;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean canBeExecuted(Model game)
	{
		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getData().getDicePlacementCondition(nDice);
		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		return game.getMainBoard().getData().getGameState().getClass() == RoundState.class &&
				dicePlacementCondition!=null &&
				dicePlacementCondition.canBePlaced(y, x, playerBoard);

	}

	@Override
	public void execute(Model game)
	{
		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		Dice dice = playerBoard.getPickedDicesSlot().remove(nDice).getDice();
		playerBoard.addDiceInPlacedFrame(dice, y, x);

	}
}
