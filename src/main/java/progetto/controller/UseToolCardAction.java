package progetto.controller;

import progetto.model.Game;
import progetto.model.RoundState;

public class UseToolCardAction extends AbstractExecutibleGameAction{

	private final int nCard;

	public UseToolCardAction(int nCard)
	{
		this.nCard = nCard;
	}

	public UseToolCardAction()
	{
		super(-1);
		nCard = -1;
	}

	@Override
	public boolean canBeExecuted(Game game)
	{
		return game.getMainBoard().getData().getGameState().getClass() == RoundState.class &&
				game.getMainBoard().getData().getToolCards().size()>nCard && nCard >= 0;
	}

	@Override
	public void execute(Game game) {
		game.setState(game.getMainBoard().getData().getToolCards().get(nCard).getState());
	}
}