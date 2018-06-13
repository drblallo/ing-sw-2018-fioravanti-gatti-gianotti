package progetto.controller;

import progetto.model.*;

import java.util.Map;

/**
 * Action to execute tool card 6 or 10
 */
public class ExecuteToolCard6Or10Action extends AbstractExecutibleGameAction{

	private static final String N_DICE = "nDice";
	private static final int CARD6 = 6;
	private static final int CARD10 = 10;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard6Or10Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param nPlayer
	 */
	public ExecuteToolCard6Or10Action(int nPlayer)
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

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		if(cardState.getIndex() != CARD6 && cardState.getIndex() != CARD10)
		{
			return false;
		}

		int nDice = map.get(N_DICE);

		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(currentPlayer).getPickedDicesSlot().getData()
				.getDicePlacementCondition(nDice);

		return dicePlacementCondition != null ;

	}

	/**
	 * execute action
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
		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		if(cardState.getIndex() == CARD6)
		{
			dice = game.getRNGenerator().rollAgain(dice);
		}
		else
		{
			dice = dice.flip();
		}

		playerBoard.getPickedDicesSlot().changeDice(nDice, dice);

		game.getMainBoard().delParamToolCard();
		game.setState(new RoundState());

	}

}
