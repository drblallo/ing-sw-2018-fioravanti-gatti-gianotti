package progetto.controller;

import progetto.model.*;

/**
 * Action to execute tool card 11
 */
public class ExecuteToolCard11Action extends AbstractExecutibleGameAction{

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
		RoundInformationData roundInformationData = game.getRoundInformation().getData();
		int currentPlayer = game.getRoundInformation().getData().getCurrentPlayer();

		int nDice = roundInformationData.getToolCardParameters().getNDice();

		if(currentPlayer != getCallerID() || nDice==-1 ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

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
		Dice dice = game.getRNGenerator().extractDice(game.getDiceBag());

		game.getRoundInformation().setDice(dice);

		game.getRoundInformation().setChangedDiceDB(0);

	}

}
