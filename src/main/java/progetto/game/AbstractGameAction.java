package progetto.game;

import java.io.Serializable;

/**
 * This is the class every command must be derived from.
 * every derived class must implement canBeExecuted and execute.
 */
public abstract class AbstractGameAction implements Serializable
{
	private int playerID = -1;

	/**
	 * constructor that sets the caller id.
	 * @param callerID the id of the player trying to execute the command.
	 */
	protected AbstractGameAction(int callerID)
	{
		playerID = callerID;
	}

	/**
	 *
	 * @return the name of this class.
	 */
	public final String getName()
	{
		return getClass().getName();
	}

	/**
	 *
	 * @return the id of the player that is trying to perform this action
	 */
	public final int getCallerID()
	{
		return playerID;
	}

	/**
	 *
	 * @param game the game in which this command should be executed
	 * @return true if the current state of the game allow to use this command
	 */
	protected abstract boolean canBeExecuted(Game game);

	/**
	 *
	 * @param game the game in which we want to execute this command
	 * @return true if the execution was allowed, false otherwise
	 */
	protected abstract boolean execute(Game game);

}
