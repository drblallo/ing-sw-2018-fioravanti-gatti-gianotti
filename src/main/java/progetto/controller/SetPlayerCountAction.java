package progetto.controller;

import progetto.model.Model;
import progetto.model.PreGameState;

public class SetPlayerCountAction extends AbstractExecutibleGameAction
{
	private final int playerCount;

	public SetPlayerCountAction(int playerCount)
	{
		super(-1);
		this.playerCount = playerCount;
	}

	public SetPlayerCountAction()
	{
		super();
		this.playerCount = 1;
	}

	@Override
	public boolean canBeExecuted(Model game) {
		return (game.getMainBoard().getData().getGameState().getClass() == PreGameState.class);
	}

	@Override
	public void execute(Model game) {
		game.getMainBoard().setPlayerCount(playerCount);
	}
}
