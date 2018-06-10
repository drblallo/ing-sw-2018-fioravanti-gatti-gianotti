package progetto.controller;

import progetto.model.*;

import java.util.List;

public class ExecuteToolCard8Action extends AbstractExecutibleGameAction{

	private static final int INDEX = 8;

	public ExecuteToolCard8Action()
	{
		super();
	}

	public ExecuteToolCard8Action(int nPlayer)
	{
		super(nPlayer);
	}

	@Override
	public boolean canBeExecuted(Model game)
	{
		int currentPlayer = game.getMainBoard().getData().getCurrentPlayer();

		if(currentPlayer != getCallerID() ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();



		List<Integer> playerQueue = game.getMainBoard().getRoundPlayerList();
		int nPlayer = getCallerID();
		boolean found = false;

		while(!playerQueue.isEmpty())
		{
			int nextP = playerQueue.remove(0);
			if (nextP == nPlayer)   //check first turn of the player
			{
				found = true;
			}
		}

		return cardState.getIndex() == INDEX && found;

	}

	@Override
	public void execute(Model game)
	{
		game.getMainBoard().changeNextPlayer(getCallerID());

		game.getMainBoard().delParamToolCard();
		game.setState(new RoundState());

	}

}
