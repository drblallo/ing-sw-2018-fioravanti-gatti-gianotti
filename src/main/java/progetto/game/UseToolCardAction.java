package progetto.game;

public class UseToolCardAction extends AbstractGameAction {

	private int nCard;

	UseToolCardAction(int nCard)
	{
		this.nCard = nCard;
	}

	@Override
	public boolean canBeExecuted(Game game)
	{
		return game.getMainBoard().getData().getGameState().getClass() == RoundState.class &&
				game.getMainBoard().getData().getToolCards().size()>nCard;
	}

	@Override
	protected void execute(Game game) {
		game.setState(game.getMainBoard().getData().getToolCards().get(nCard).getState());
	}
}
