package progetto.controller;

import progetto.model.*;

import java.util.Map;

public class ExecuteToolCard1Action extends AbstractExecutibleGameAction{

	private static final String N_DICE = "nDice";
	private static final String INC_DEC = "increaseDecrease";
	private static final int INDEX = 1;

	public ExecuteToolCard1Action()
	{
		super();
	}

	public ExecuteToolCard1Action(int nPlayer)
	{
		super(nPlayer);
	}

	@Override
	public boolean canBeExecuted(Model game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int currentPlayer = game.getMainBoard().getData().getCurrentPlayer();

		if(currentPlayer != getCallerID() || !map.containsKey(N_DICE) || !map.containsKey(INC_DEC) ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		int nDice = map.get(N_DICE);
		int increaseDecrease = map.get(INC_DEC);

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(currentPlayer).getPickedDicesSlot().getData()
				.getDicePlacementCondition(nDice);

		return cardState.getIndex() == INDEX &&
				dicePlacementCondition != null && (increaseDecrease == 0 || increaseDecrease == 1);

	}

	@Override
	public void execute(Model game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int nDice = map.get(N_DICE);
		int increaseDecrease = map.get(INC_DEC);

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		DicePlacementCondition dicePlacementCondition = playerBoard.getPickedDicesSlot()
				.getData().getDicePlacementCondition(nDice);

		Dice dice = dicePlacementCondition.getDice();

		if(increaseDecrease == 0)
		{
			dice = dice.increaseValue();
		}
		else
		{
			dice = dice.decreaseValue();
		}

		playerBoard.getPickedDicesSlot().changeDice(nDice, dice);

		game.getMainBoard().delParamToolCard();
		game.setState(new RoundState());

	}

}
