package progetto.controller;

import progetto.model.*;

import java.util.Map;

/**
 * Action to set the value of the dice
 */
public class ToolCardSetDiceValueAction extends AbstractExecutibleGameAction{

	private final int value;
	private static final int MAX_VALUE = 6;
	private static final String N_DICE = "nDice";
	private static final String DB_CHANGED = "changedDiceDB";
	private static final int CARD11 = 11;

	/**
	 * Constructor without parameters
	 */
	public ToolCardSetDiceValueAction()
	{
		super();
		value = 1;

	}

	/**
	 * Constructor to set values
	 * @param nPlayer callerID
	 * @param value new value for the dice (value int)
	 */
	public ToolCardSetDiceValueAction(int nPlayer, int value)
	{
		super(nPlayer);
		this.value = value;

	}

	/**
	 * Constructor to set values
	 * @param nPlayer callerID
	 * @param value new value for the dice (value Value)
	 */
	public ToolCardSetDiceValueAction(int nPlayer, Value value)
	{
		super(nPlayer);
		this.value = value.ordinal()+1;

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
				value>=1 && value<=MAX_VALUE && index == CARD11;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game) {
		game.getMainBoard().setParamToolCard("value", value);

		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();
		if(cardState.getIndex()==CARD11 && map.containsKey(DB_CHANGED) && map.containsKey(N_DICE))
		{
			int nDice = map.get(N_DICE);
			DicePlacementCondition placementCondition = game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getData().getDicePlacementCondition(nDice);
			if(placementCondition != null)
			{
				Dice dice = placementCondition.getDice();
				dice = dice.setValue(Value.valueOf(value));
				game.getPlayerBoard(getCallerID()).getPickedDicesSlot().changeDice(nDice, dice);

			}

			game.getMainBoard().delParamToolCard();
			game.setState(new RoundState());

		}

	}

}
