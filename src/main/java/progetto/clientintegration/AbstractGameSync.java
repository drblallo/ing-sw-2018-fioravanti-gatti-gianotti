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
	public void sendString(Serializable s) {
		game.sendAction((AbstractGameAction) s);
		game.processAllPendingAction();
	}

	@Override
	public boolean isStringGood(Serializable s, int senderID) {
		AbstractGameAction action = (AbstractGameAction) s;
		if (action == null)
			return false;
		return (action.canBeExecuted(game) && (senderID == -1 || action.getCallerID() == senderID));
	}

	@Override
	public int getHash(int index) {
		return game.getHash(index);
	}

	public int getStringCount() {
		return game.getActionQueue().getPastItemCount();
	}

	public Serializable getString(int index) {
		return game.getActionQueue().getPastItem(index);
	}

	public int getHash() {
		return game.hashCode();
	}

	public void clear() {
		game = new Game();
	}

	public List<Serializable> getAllString() {
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
