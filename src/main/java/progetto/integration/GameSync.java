package progetto.integration;

import progetto.controller.GameController;
import progetto.model.AbstractGameAction;
import progetto.network.IEnforce;
import progetto.network.ISync;
import progetto.utils.Callback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A game sync ties together a game and a sync object, so that it can be kept syncronized across the network.
 */
public class GameSync implements ISync
{
	private GameController game = new GameController();

	/**
	 * process a game action
	 * @param s must be a game action, otherwise it will fail.
	 */
	@Override
	public void sendItem(Serializable s)
	{
		game.sendAction((AbstractGameAction) s);
		game.processAllPendingAction();
	}

	/**
	 *
	 * @param s the game action that must be evaluated.
	 * @param senderID the chiar of the player that is trying to use the action
	 * @return true if the action can be executed by the player, if the action is sent with id -1 or if the sender has id -1
	 */
	@Override
	public boolean isItemGood(Serializable s, int senderID)
	{
		AbstractGameAction action = (AbstractGameAction) s;
		if (action == null)
			return false;
		return (action.canBeExecuted(game.getModel()) && (senderID == -1 || action.getCallerID() == -1
				|| action.getCallerID() == senderID));
	}

	/**
	 *
	 * @return the hash that was evaluated after index game actions where evaluted
	 */
	@Override
	public int getHash(int index) {
		return game.getHash(index);
	}

	/**
	 *
	 * @return the number of gameaction that were evaluated.
	 */
	public int getItemCount() {
		return game.getModel().getCommandQueue().getPastItemCount();
	}

	/**
	 *
	 * @return the hash of the game
	 */
	public int getHash() {
		return game.hashCode();
	}

	/**
	 * reset the state of the game to the default one
	 */
	public void clear() {
		game = new GameController();
	}

	/**
	 *
	 * @return the list of all game actions used up to this point
	 */
	public List<Serializable> getAllItems() {
		ArrayList<Serializable> list = new ArrayList<>();
		for (int a = 0; a < game.getModel().getCommandQueue().getPastItemCount(); a++)
			list.add(game.getModel().getCommandQueue().getPastItem(a));
		return list;
	}

	/**
	 *
	 * @return the undelying game
	 */
	protected GameController getGame()
	{
		return game;
	}

	/**
	 *
	 * @return this should only be called on the client side
	 */
	@Override
	public Callback<IEnforce> getEnforceCallback() {
		throw new UnsupportedOperationException("YOU ARE NOT A SERVER");
	}

	/**
	 *
	 * @return this should only be called on the client side.
	 */
	@Override
	public List<IEnforce> getNewPlayerEnforces() {
		throw new UnsupportedOperationException("YOU ARE NOT A SERVER");
	}
}
