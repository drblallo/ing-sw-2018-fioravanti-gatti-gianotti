package progetto.controller;

import progetto.model.*;

import java.util.Map;

public class ExecuteToolCard2Or3Action extends AbstractExecutibleGameAction{

	private static final String XPOS = "XPlacedDice";
	private static final String YPOS = "YPlacedDice";
	private static final int CARD2 = 2;
	private static final int CARD3 = 3;


	public ExecuteToolCard2Or3Action()
	{
		super();
	}

	public ExecuteToolCard2Or3Action(int nPlayer)
	{
		super(nPlayer);
	}

	@Override
	public boolean canBeExecuted(Model game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int currentPlayer = game.getMainBoard().getData().getCurrentPlayer();

		if(currentPlayer != getCallerID() || !map.containsKey(XPOS) || !map.containsKey(YPOS) ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		int xPos = map.get(XPOS);
		int yPos = map.get(YPOS);

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();

		return (cardState.getIndex() == CARD2 || cardState.getIndex() == CARD3) &&
				game.getPlayerBoard(getCallerID()).getDicePlacedFrame().getData().getDice(yPos, xPos) != null;

	}

	@Override
	public void execute(Model game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int xPos = map.get(XPOS);
		int yPos = map.get(YPOS);

		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		Dice dice = playerBoard.getDicePlacedFrame().removeDice(yPos, xPos);

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();
		if(cardState.getIndex() == CARD2)
		{
			playerBoard.getPickedDicesSlot().add(dice, true, false, false);
		}
		else if(cardState.getIndex() == CARD3)
		{
			playerBoard.getPickedDicesSlot().add(dice, false, true, false);
		}

		game.getMainBoard().delParamToolCard();

		game.setState(new RoundState());

	}

}
