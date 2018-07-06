package progetto.controller;

import progetto.model.*;

/**
 * Action to execute tool card 5
 */
public class ExecuteToolCard5Action extends AbstractExecutibleGameAction{

	private static final int INDEX = 5;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard5Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param callerID
	 */
	public ExecuteToolCard5Action(int callerID)
	{
		super(callerID);
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
		int round = roundInformationData.getToolCardParameters().getRound();
		int nDiceRT = roundInformationData.getToolCardParameters().getNDiceRT();

		if(currentPlayer != getCallerID() || nDice==-1 || round==-1
				|| nDiceRT==-1 ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		return cardState.getIndex() == INDEX && nDice >= 0 &&
				game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getData().getNDices()>nDice &&
				game.getRoundTrack().getData().getDice(round, nDiceRT) != null ;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		RoundInformation roundInformation = game.getRoundInformation();
		int nDice = roundInformation.getData().getToolCardParameters().getNDice();
		int round =roundInformation.getData().getToolCardParameters().getRound();
		int nDiceRT = roundInformation.getData().getToolCardParameters().getNDiceRT();

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		RoundTrack roundTrack = game.getRoundTrack();

		Dice dice = playerBoard.getPickedDicesSlot().getData().getDicePlacementCondition(nDice).getDice();

		Dice dice1 = roundTrack.change(round, nDiceRT, dice);

		playerBoard.getPickedDicesSlot().changeDice(nDice, dice1);

	}

}
