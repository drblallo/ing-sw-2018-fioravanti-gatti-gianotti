package progetto.clientintegration;

import progetto.game.AbstractGameAction;
import progetto.game.Game;
import progetto.network.ISync;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGameSync implements ISync{
	private Game game = new Game();

	@Override
	public void sendItem(Serializable s) {
		game.sendAction((AbstractGameAction) s);
		game.processAllPendingAction();
	}

	@Override
	public boolean isItemGood(Serializable s, int senderID) {
		AbstractGameAction action = (AbstractGameAction) s;
		if (action == null)
			return false;
		return (action.canBeExecuted(game) && (senderID == -1 || action.getCallerID() == senderID));
	}

	@Override
	public int getHash(int index) {
		return game.getHash(index);
	}

	public int getItemCount() {
		return game.getActionQueue().getPastItemCount();
	}

	public Serializable getItem(int index) {
		return game.getActionQueue().getPastItem(index);
	}

	public int getHash() {
		return game.hashCode();
	}

	public void clear() {
		game = new Game();
	}

	public List<Serializable> getAllItems() {
		ArrayList<Serializable> list = new ArrayList<>();
		for (int a = 0; a < game.getActionQueue().getPastItemCount(); a++)
			list.add(game.getActionQueue().getPastItem(a));
		return list;
	}

	Game getGame()
	{
		return game;
	}
}
