package progetto.controller;

import progetto.model.*;

import java.util.Map;

public class ToolCardSetSinglePlayerDiceAction extends AbstractExecutibleGameAction{

	private final int nDice;

	public ToolCardSetSinglePlayerDiceAction(int player, int nDice)
	{
		super(player);
		this.nDice = nDice;
	}

	public ToolCardSetSinglePlayerDiceAction()
	{
		super(-1);
		nDice = -1;
	}

	@Override
	public boolean canBeExecuted(Model game)
	{
		if(game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class ||
				nDice>=game.getMainBoard().getExtractedDices().getData().getNumberOfDices() || nDice < 0 ||
				game.getMainBoard().getData().getPlayerCount()!=1)
		{
			return false;
		}

		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int nCard = map.get("nCard");
		Color color = game.getMainBoard().getData().getToolCards().get(nCard).getColor();

		Dice dice = game.getMainBoard().getExtractedDices().getData().getDice(nDice);
		if(dice == null)
		{
			return false;
		}

		return dice.getColor() == color;

	}

	@Override
	public void execute(Model game)
	{
		game.getMainBoard().setParamToolCard("SPDice", nDice);

	}
}
