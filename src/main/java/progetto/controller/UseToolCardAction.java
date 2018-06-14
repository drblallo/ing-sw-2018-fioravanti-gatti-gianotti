package progetto.controller;

import progetto.model.IModel;
import progetto.model.Model;
import progetto.model.RoundState;
import progetto.model.ToolCardState;

/**
 * Action to use tool card
 */
public class UseToolCardAction extends AbstractExecutibleGameAction{

	private final int nCard;

	/**
	 * Constructor without parameters
	 */
	public UseToolCardAction()
	{
		super(-1);
		nCard = -1;
	}

	/**
	 * Constructor to set values
	 * @param player callerID
	 * @param nCard tool card on the MainBoard to use
	 */
	public UseToolCardAction(int player, int nCard)
	{
		super(player);
		this.nCard = nCard;
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game)
	{
		if(game.getMainBoard().getData().getGameState().getClass() != RoundState.class ||
				nCard>=game.getMainBoard().getData().getToolCards().size() || nCard < 0 ||
				game.getRoundInformation().getData().getUsedToolCard())
		{
			return false;
		}

		if(game.getMainBoard().getData().getPlayerCount() != 1)
		{
			int nCallToolCard = game.getMainBoard().getData().getNCallToolCard(nCard);
			int playerToken = game.getPlayerBoard(getCallerID()).getData().getToken();
			int askedToken = 1;

			if (nCallToolCard > 0) {
				askedToken++;
			}

			return playerToken >= askedToken;
		}

		return true;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		int nCallToolCard = game.getMainBoard().getData().getNCallToolCard(nCard);
		int askedToken = 1;
		if(nCallToolCard>0)
		{
			askedToken++;
		}

		game.getMainBoard().setParamToolCard("token", askedToken);
		game.getMainBoard().setParamToolCard("nCard", nCard);

		game.setState(new ToolCardState(game.getMainBoard().getData().getToolCards().get(nCard).getIndex()));

	}
}
