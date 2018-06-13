package progetto.controller;

import progetto.model.*;

import java.util.Map;

/**
 * Action to execute tool card 5
 */
public class ExecuteToolCard5Action extends AbstractExecutibleGameAction{

	private static final String N_DICE = "nDice";
	private static final String ROUND = "round";
	private static final String N_DICE_RT = "nDiceRT";
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
	 * @param nPlayer
	 */
	public ExecuteToolCard5Action(int nPlayer)
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

		if(currentPlayer != getCallerID() || !map.containsKey(N_DICE) || !map.containsKey(ROUND)
				|| !map.containsKey(N_DICE_RT) ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		int nDice = map.get(N_DICE);
		int round = map.get(ROUND);
		int nDiceRT = map.get(N_DICE_RT);

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
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int nDice = map.get(N_DICE);
		int round = map.get(ROUND);
		int nDiceRT = map.get(N_DICE_RT);

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		RoundTrack roundTrack = game.getRoundTrack();

		Dice dice = playerBoard.getPickedDicesSlot().getData().getDicePlacementCondition(nDice).getDice();

		Dice dice1 = roundTrack.change(round, nDiceRT, dice);

		playerBoard.getPickedDicesSlot().changeDice(nDice, dice1);

		game.getMainBoard().delParamToolCard();

		game.setState(new RoundState());

	}

}
