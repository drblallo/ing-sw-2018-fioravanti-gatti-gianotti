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

	private static final int CARD12 = 12;

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

		int round = map.get(ROUND);
		int nDiceRT = map.get(N_DICE_RT);

		Dice diceRT = game.getRoundTrack().getData().getDice(round, nDiceRT);

		int xPos = -1;
		int yPos = -1;
		Dice dice = null;

		int xPos2 = -1;
		int yPos2 = -1;
		Dice dice2 = null;

		if(map.containsKey(XPOS) && map.containsKey(YPOS))
		{
			xPos = map.get(XPOS);
			yPos = map.get(YPOS);
			dice = game.getPlayerBoard(getCallerID()).getDicePlacedFrame().getData().getDice(yPos, xPos);
		}

		if(map.containsKey(XPOS2) && map.containsKey(YPOS2))
		{
			xPos2 = map.get(XPOS2);
			yPos2 = map.get(YPOS2);
			dice2 = game.getPlayerBoard(getCallerID()).getDicePlacedFrame().getData().getDice(yPos2, xPos2);
		}

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		return cardState.getIndex() == CARD12 && verifyDiceColor(diceRT, dice, dice2)
				&& (!(dice==null && dice2==null));

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		Dice dice;

		if(map.containsKey(XPOS) && map.containsKey(YPOS))
		{
			int xPos = map.get(XPOS);
			int yPos = map.get(YPOS);

			dice = playerBoard.getDicePlacedFrame().removeDice(yPos, xPos);
			playerBoard.getPickedDicesSlot().add(dice, false, false, false);

		}

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

	private boolean verifyParam(IModel game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int currentPlayer = game.getRoundInformation().getData().getCurrentPlayer();

		if(!(map.containsKey(XPOS) && map.containsKey(YPOS)) && !(map.containsKey(XPOS2) && map.containsKey(YPOS2)))
		{
			return false;
		}

		return currentPlayer == getCallerID() &&
				game.getMainBoard().getData().getGameState().getClass() == ToolCardState.class &&
				map.containsKey(ROUND) && map.containsKey(N_DICE_RT);

	}

	private boolean verifyDiceColor(Dice diceRT, Dice dice, Dice dice2)
	{
		return !((dice!=null && dice.getGameColor()!=diceRT.getGameColor()) || (dice2!=null && dice2.getGameColor()!=diceRT.getGameColor()));
	}

}
