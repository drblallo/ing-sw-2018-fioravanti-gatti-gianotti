package progetto.game;

public class ExecuteToolCardAction extends AbstractGameAction {

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
	protected void execute(Game game)
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
