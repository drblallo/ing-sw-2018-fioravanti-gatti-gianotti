package progetto.controller;

import progetto.model.Dice;
import progetto.model.IModel;
import progetto.model.Model;
import progetto.model.RoundState;

/**
 * Action to pick a dice (from extracted to picked)
 * @author Michele
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
	 * @param callerID callerID
	 * @param nDice from ExtractedDices
	 */
	public PickDiceAction(int callerID, int nDice)
	{
		super(callerID);
		this.nDice = nDice;
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game) {

		return game.getMainBoard().getData().getGameState().getClass() == RoundState.class &&
				getCallerID() == game.getRoundInformation().getData().getCurrentPlayer() &&
				game.getMainBoard().getExtractedDices().getData().getDice(nDice) != null &&
				!game.getRoundInformation().getData().getPickedDice();

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
		game.getRoundInformation().setPickedDice(true);
	}
}
