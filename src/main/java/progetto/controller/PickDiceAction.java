package progetto.controller;

import progetto.model.Dice;
import progetto.model.Model;
import progetto.model.RoundState;

/**
 * Action to pick a dice (from extracted to picked)
 */
public class PickDiceAction extends AbstractExecutibleGameAction{

	private final int nDice;

	/**
	 * Constructor without parameters
	 */
	public PickDiceAction(){

		super(-1);
		nDice = -1;

	}

	/**
	 * Constructor to set values
	 * @param nPlayer callerID
	 * @param nDice from ExtractedDices
	 */
	public PickDiceAction(int nPlayer, int nDice)
	{
		super(nPlayer);
		this.nDice = nDice;
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(Model game) {

		return game.getMainBoard().getData().getGameState().getClass() == RoundState.class &&
				getCallerID() == game.getMainBoard().getData().getCurrentPlayer() &&
				game.getMainBoard().getExtractedDices().getData().getDice(nDice) != null;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		Dice dice = game.getMainBoard().getExtractedDices().removeDice(nDice);
		game.getPlayerBoard(getCallerID()).getPickedDicesSlot().add(dice, false, false, false);
	}
}
