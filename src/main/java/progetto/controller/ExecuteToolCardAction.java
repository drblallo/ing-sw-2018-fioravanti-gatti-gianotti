package progetto.controller;

import progetto.model.AbstractGameState;
import progetto.model.AbstractToolCardState;
import progetto.model.Game;

public class ExecuteToolCardAction extends AbstractExecutibleGameAction{

	@Override
	public boolean canBeExecuted(Game game)
	{
		AbstractGameState gameState = game.getMainBoard().getData().getGameState();
		AbstractToolCardState toolCardState;
		if(gameState instanceof AbstractToolCardState)
		{
			toolCardState = (AbstractToolCardState)gameState;
		}
		else
		{
			return false;
		}

		return toolCardState.isEverythingSet(game);

	}

	@Override
	public void execute(Game game)
	{
		AbstractGameState gameState = game.getMainBoard().getData().getGameState();
		AbstractToolCardState toolCardState;
		if(gameState instanceof AbstractToolCardState)
		{
			toolCardState = (AbstractToolCardState)gameState;
			toolCardState.solve(game);
		}
		game.getMainBoard().delParamToolCard();
	}

}
