package progetto.integration;

import progetto.controller.GameController;
import progetto.model.AbstractGameAction;
import progetto.network.IEnforce;
import progetto.network.ISync;
import progetto.utils.Callback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameSync implements ISync
{
	private GameController game = new GameController();

	@Override
	public void sendItem(Serializable s)
	{
		game.sendAction((AbstractGameAction) s);
		game.processAllPendingAction();
	}

	@Override
	public boolean isItemGood(Serializable s, int senderID)
	{
		AbstractGameAction action = (AbstractGameAction) s;
		if (action == null)
			return false;
		return (action.canBeExecuted(game.getModel()) && (senderID == -1 || action.getCallerID() == senderID));
	}

	@Override
	public int getHash(int index) {
		return game.getHash(index);
	}

	public int getItemCount() {
		return game.getModel().getCommandQueue().getPastItemCount();
	}

	public int getHash() {
		return game.hashCode();
	}

	public void clear() {
		game = new GameController();
	}

	public List<Serializable> getAllItems() {
		ArrayList<Serializable> list = new ArrayList<>();
		for (int a = 0; a < game.getModel().getCommandQueue().getPastItemCount(); a++)
			list.add(game.getModel().getCommandQueue().getPastItem(a));
		return list;
	}

	protected GameController getGame()
	{
		return game;
	}

	@Override
	public Callback<IEnforce> getEnforceCallback() {
		throw new UnsupportedOperationException("YOU ARE NOT A SERVER");
	}

	@Override
	public List<IEnforce> getNewPlayerEnforces() {
		throw new UnsupportedOperationException("YOU ARE NOT A SERVER");
	}
}
