package progetto.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Command queue of the game
 */
public class CommandQueue extends AbstractProcessor<AbstractGameAction> {

	private List<AbstractGameAction> pastAction = Collections.synchronizedList(new ArrayList<>());
	private Queue<AbstractGameAction> pendingActions = new ConcurrentLinkedQueue<>();

	/**
	 * Append an action to the queue
	 * @param action the action that must be appended
	 */
	void offer(AbstractGameAction action)
	{
		pendingActions.offer(action);
		change(this);
	}

	/**
	 *
	 * @return the oldest inserted value without removing it from the queue
	 */
	public AbstractGameAction peekPending()
	{
		return pendingActions.peek();
	}

	/**
	 * Removes the oldest inserted values and places it in the already executed list
	 * @return the oldest inserted value
	 */
	AbstractGameAction pollPending()
	{
		AbstractGameAction action = pendingActions.poll();

		if (action != null)
			pastAction.add(action);

		change(this);
		return action;
	}

	/**
	 *
	 * @return the number of pending actions
	 */
	public int getPendingItemsCount()
	{
		return pendingActions.size();
	}

	/**
	 *
	 * @return the number of already executed actions
	 */
	public int getPastItemCount()
	{
		return pastAction.size();
	}

	/**
	 *
	 * @param index the index of the action that is being retrieved
	 * @return a already executed action
	 */
	public AbstractGameAction getPastItem(int index)
	{
		if (pastAction.size() <= index)
			return null;
		return pastAction.get(index);
	}

}
