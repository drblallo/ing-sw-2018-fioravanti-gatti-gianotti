package progetto.controller;

import progetto.model.IModel;
import progetto.model.Model;
import progetto.model.PreGameState;

/**
 * Action to set number of players of the game
 */
public class SetPlayerCountAction extends AbstractExecutibleGameAction
{
	private final int playerCount;

	private static final int MAX_N_PLAYERS = 4;

	/**
	 * Constructor without parameters
	 */
	public SetPlayerCountAction()
	{
		super();
		this.playerCount = 1;
	}

	/**
	 * Constructor with callerID
	 */
	public SetPlayerCountAction(int playerCount)
	{
		super(-1);
		this.playerCount = playerCount;
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game) {
		return (game.getMainBoard().getData().getGameState().getClass() == PreGameState.class &&
			playerCount>=0 && playerCount<=MAX_N_PLAYERS);
	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game) {
		game.getMainBoard().setPlayerCount(playerCount);
	}
}
