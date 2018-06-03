package progetto.controller;

import progetto.model.Dice;
import progetto.model.Model;
import progetto.model.RoundState;

public class PickDiceAction extends AbstractExecutibleGameAction{

	private final int nDice;

	public PickDiceAction(){

		super(-1);
		nDice = -1;

	}

	public PickDiceAction(int nPlayer, int nDice)
	{
		super(nPlayer);
		this.nDice = nDice;
	}

	@Override
	public boolean canBeExecuted(Model game) {

		return game.getMainBoard().getData().getGameState().getClass() == RoundState.class &&
				getCallerID() == game.getMainBoard().getData().getCurrentPlayer() &&
				game.getMainBoard().getExtractedDices().getData().getDice(nDice) != null;

	}

	@Override
	public void execute(Model game)
	{
		Dice dice = game.getMainBoard().getExtractedDices().removeDice(nDice);
		game.getPlayerBoard(getCallerID()).getPickedDicesSlot().add(dice, false, false, false);
	}
}
