package progetto.controller;

import progetto.model.Model;
import progetto.model.RoundState;
import progetto.model.ToolCardState;

public class UseToolCardAction extends AbstractExecutibleGameAction{

	private final int nCard;

	public UseToolCardAction(int player, int nCard)
	{
		super(player);
		this.nCard = nCard;
	}

	public UseToolCardAction()
	{
		super(-1);
		nCard = -1;
	}

	@Override
	public boolean canBeExecuted(Model game)
	{
		if(game.getMainBoard().getData().getGameState().getClass() != RoundState.class ||
				nCard>=game.getMainBoard().getData().getToolCards().size() || nCard < 0)
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
