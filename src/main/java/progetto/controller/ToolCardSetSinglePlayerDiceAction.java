package progetto.controller;

import progetto.model.*;

import java.util.Map;

/**
 * Action to set the dice to use to activate tool card in single player
 */
public class ToolCardSetSinglePlayerDiceAction extends AbstractExecutibleGameAction{

	private final int nDice;

	/**
	 * Constructor without parameters
	 */
	public ToolCardSetSinglePlayerDiceAction()
	{
		super(-1);
		nDice = -1;
	}

	/**
	 * Constructor to set values
	 * @param player callerID
	 * @param nDice dice to use
	 */
	public ToolCardSetSinglePlayerDiceAction(int player, int nDice)
	{
		super(player);
		this.nDice = nDice;
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game)
	{
		if(game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class ||
				nDice>=game.getMainBoard().getExtractedDices().getData().getNumberOfDices() || nDice < 0 ||
				game.getMainBoard().getData().getPlayerCount()!=1)
		{
			return false;
		}

		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int nCard = map.get("nCard");
		GameColor gameColor = game.getMainBoard().getData().getToolCards().get(nCard).getGameColor();

		Dice dice = game.getMainBoard().getExtractedDices().getData().getDice(nDice);
		if(dice == null)
		{
			return false;
		}

		return dice.getGameColor() == gameColor;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		game.getMainBoard().setParamToolCard("SPDice", nDice);

	}
}
