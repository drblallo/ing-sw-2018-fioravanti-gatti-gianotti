package progetto.controller;

import progetto.model.*;

import java.util.Map;

/**
 * Action to execute tool card 12
 */
public class ExecuteToolCard12Action extends AbstractExecutibleGameAction{

	private static final String XPOS = "XPlacedDice";
	private static final String YPOS = "YPlacedDice";
	private static final String XPOS2 = "XPlacedDice2";
	private static final String YPOS2 = "YPlacedDice2";
	private static final String ROUND = "round";
	private static final String N_DICE_RT = "nDiceRT";
	private static final int INDEX = 12;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard12Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param nPlayer
	 */
	public ExecuteToolCard12Action(int nPlayer)
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

		if(!verifyParam(game))
		{
			return false;
		}

		int xPos = map.get(XPOS);
		int yPos = map.get(YPOS);

		int round = map.get(ROUND);
		int nDiceRT = map.get(N_DICE_RT);

		Dice diceRT = game.getRoundTrack().getData().getDice(round, nDiceRT);

		if(map.containsKey(XPOS2) && map.containsKey(YPOS2) && !verifyPos(game, diceRT, map.get(YPOS2), map.get(XPOS2)))
		{
			return false;

		}

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		return cardState.getIndex() == INDEX && verifyPos(game, diceRT, yPos, xPos);

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int xPos = map.get(XPOS);
		int yPos = map.get(YPOS);

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		Dice dice = playerBoard.getDicePlacedFrame().removeDice(yPos, xPos);
		playerBoard.getPickedDicesSlot().add(dice, false, false, false);

		if(map.containsKey(XPOS2) && map.containsKey(YPOS2))
		{
			int xPos2 = map.get(XPOS2);
			int yPos2 = map.get(YPOS2);

			dice = playerBoard.getDicePlacedFrame().removeDice(yPos2, xPos2);

			playerBoard.getPickedDicesSlot().add(dice, false, false, false);
		}


		game.getMainBoard().delParamToolCard();

		game.setState(new RoundState());

	}

	private boolean verifyPos(IModel game, Dice dice, int yPos, int xPos)
	{
		Dice dice1 = game.getPlayerBoard(getCallerID()).getDicePlacedFrame().getData().getDice(yPos, xPos);
		return dice != null && dice1 != null && dice.getGameColor() == dice1.getGameColor();

	}

	private boolean verifyParam(IModel game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int currentPlayer = game.getMainBoard().getData().getCurrentPlayer();

		return currentPlayer == getCallerID() && map.containsKey(XPOS) && map.containsKey(YPOS) &&
				game.getMainBoard().getData().getGameState().getClass() == ToolCardState.class &&
				map.containsKey(ROUND) && map.containsKey(N_DICE_RT);

	}

}
