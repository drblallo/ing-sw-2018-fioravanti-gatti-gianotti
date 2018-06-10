package progetto.controller;

import progetto.model.*;

public class CancelToolCardUseAction extends AbstractGameAction
{

	public CancelToolCardUseAction(){

		super(-1);

	}

	public CancelToolCardUseAction(int nPlayer)
	{
		super(nPlayer);
	}

	@Override
	public boolean canBeExecuted(Model game)
	{
		return game.getMainBoard().getData().getGameState().getClass() == ToolCardState.class &&
				getCallerID() == game.getMainBoard().getData().getCurrentPlayer();
	}

	@Override
	public void execute(Model game)
	{
		game.getMainBoard().delParamToolCard();
		game.setState(new RoundState());

	}
}

