package progetto.controller;

import progetto.model.*;

import java.util.Map;

/**
 * Action to execute tool card 11
 */
public class ExecuteToolCard11Action extends AbstractExecutibleGameAction{

	private static final String N_DICE = "nDice";
	private static final String DB_CHANGED = "changedDiceDB";
	private static final int INDEX = 11;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard11Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param nPlayer
	 */
	public ExecuteToolCard11Action(int nPlayer)
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
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int currentPlayer = game.getMainBoard().getData().getCurrentPlayer();

		if(currentPlayer != getCallerID() || !map.containsKey(N_DICE) ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		int nDice = map.get(N_DICE);

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(currentPlayer).getPickedDicesSlot().getData()
				.getDicePlacementCondition(nDice);

		return cardState.getIndex() == INDEX && dicePlacementCondition != null ;

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

		DicePlacementCondition dicePlacementCondition = playerBoard.getPickedDicesSlot()
				.getData().getDicePlacementCondition(nDice);

		Dice dice = dicePlacementCondition.getDice();

		game.getDiceBag().add(dice.getGameColor());

		dice = game.getRNGenerator().extractDice(game.getDiceBag());

		playerBoard.getPickedDicesSlot().changeDice(nDice, dice);

		game.getMainBoard().setParamToolCard(DB_CHANGED, 0);

	}

}
