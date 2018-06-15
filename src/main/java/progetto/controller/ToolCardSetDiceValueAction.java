package progetto.controller;

import progetto.model.*;

/**
 * Action to set the value of the dice
 */
public class ToolCardSetDiceValueAction extends AbstractExecutibleGameAction{

	private final int value;
	private static final int MAX_VALUE = 6;

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
		DicePlacementCondition placementCondition = game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getData().getDicePlacementCondition(nDice);

		if(dbChanged==-1 || nDice==-1 || dice==null || placementCondition==null)
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

		RoundInformation roundInformation = game.getRoundInformation();

		int nDice = roundInformation.getData().getToolCardParameters().getNDice();
		Dice dice = game.getRoundInformation().getData().getToolCardParameters().getDice();

		dice = dice.setValue(Value.valueOf(value));

		DicePlacementCondition placementCondition = game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getData().getDicePlacementCondition(nDice);

		game.getDiceBag().add(placementCondition.getDice().getGameColor());
		game.getPlayerBoard(getCallerID()).getPickedDicesSlot().changeDice(nDice, dice);

		game.getRoundInformation().setDice(null);


	}

}
