package progetto.controller;

import progetto.model.*;

/**
 * Action to set the value of the dice
 * @author Michele
 */
public class ToolCardSetDiceValueAction extends AbstractExecutibleGameAction{

	private final int value;
	public static final int MAX_VALUE = 6;

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
	 * @param callerID callerID
	 * @param value new value for the dice (value int)
	 */
	public ToolCardSetDiceValueAction(int callerID, int value)
	{
		super(callerID);
		this.value = value;

	}

	/**
	 * Constructor to set values
	 * @param callerID callerID
	 * @param value new value for the dice (value Value)
	 */
	public ToolCardSetDiceValueAction(int callerID, Value value)
	{
		super(callerID);
		this.value = value.ordinal()+1;

	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game) {
		MainBoardData data = game.getMainBoard().getData();

		if(data.getGameState().getClass() != RoundState.class)
		{
			return false;
		}

		RoundInformationData roundInformationData = game.getRoundInformation().getData();

		int nDice = roundInformationData.getToolCardParameters().getNDice();
		int dbChanged = roundInformationData.getToolCardParameters().getChangedDiceDB();
		Dice dice = roundInformationData.getToolCardParameters().getDice();

		if(dbChanged==-1 || nDice==-1 || dice==null)
		{
			return false;
		}

		return getCallerID()==game.getRoundInformation().getData().getCurrentPlayer() &&
				value>=1 && value<=MAX_VALUE && game.getRoundInformation().getData().getToolCardParameters().getDice()!=null;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game) {
		game.getRoundInformation().setValue(value);

		Dice dice = game.getRoundInformation().getData().getToolCardParameters().getDice();

		dice = dice.setValue(Value.valueOf(value));

		game.getPlayerBoard(getCallerID()).getPickedDicesSlot().add(dice);

		game.getRoundInformation().setDice(null);


	}

}
