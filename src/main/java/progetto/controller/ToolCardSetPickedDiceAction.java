package progetto.controller;

import progetto.model.MainBoardData;
import progetto.model.Model;
import progetto.model.ToolCardState;

/**
 * Action to select a picked dice
 */
public class ToolCardSetPickedDiceAction extends AbstractExecutibleGameAction{

	private final int nDice;

	private static final int CARD1 = 1;
	private static final int CARD5 = 5;
	private static final int CARD6 = 6;
	private static final int CARD9 = 9;
	private static final int CARD10 = 10;
	private static final int CARD11 = 11;

	/**
	 * Constructor without parameters
	 */
	public ToolCardSetPickedDiceAction()
	{
		super();
		nDice = -1;

	}

	/**
	 * Constructor to set values
	 * @param nPlayer callerID
	 * @param nDice n dice in picked dice slot
	 */
	public ToolCardSetPickedDiceAction(int nPlayer, int nDice)
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
		MainBoardData data = game.getMainBoard().getData();

		if(data.getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState toolCardState = (ToolCardState) data.getGameState();

		int index = toolCardState.getIndex();

		return getCallerID()==game.getMainBoard().getData().getCurrentPlayer() &&
				data.getGameState().getClass() == ToolCardState.class &&
				game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getNDices() > nDice &&
				nDice >= 0 && verifyCards(index);
	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game) {
		game.getMainBoard().setParamToolCard("nDice", nDice);
	}

	/**
	 * Support method to verify if action is used with the correct tool card
	 * @param index of the used card
	 * @return result of the check
	 */
	private boolean verifyCards(int index)
	{
		return (index == CARD1 || index == CARD5 || index == CARD6 || index == CARD9 || index == CARD10 || index == CARD11);
	}

}
