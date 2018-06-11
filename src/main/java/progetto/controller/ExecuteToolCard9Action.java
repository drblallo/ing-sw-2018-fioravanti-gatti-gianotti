package progetto.controller;

import progetto.model.*;

import java.util.Map;

/**
 * Action to execute tool card 9
 */
public class ExecuteToolCard9Action extends AbstractExecutibleGameAction{

	private static final String N_DICE = "nDice";
	private static final int CARD9 = 9;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard9Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param nPlayer
	 */
	public ExecuteToolCard9Action(int nPlayer)
	{
		super(nPlayer);
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(Model game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int currentPlayer = game.getMainBoard().getData().getCurrentPlayer();

		if(currentPlayer != getCallerID() || !map.containsKey(N_DICE) ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		int nDice = map.get(N_DICE);

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		return cardState.getIndex() == CARD9 &&
				game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getData().getDicePlacementCondition(nDice) != null;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int nDice = map.get(N_DICE);

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		playerBoard.getPickedDicesSlot().setIgnoreAdjacent(nDice, true);

		game.getMainBoard().delParamToolCard();

		game.setState(new RoundState());

	}

}
