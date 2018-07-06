package progetto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Contains all the past and to be executed actions received by the model
 * @author Michele
 */
public class CommandQueueData implements Serializable{

	private final List<AbstractGameAction> pastAction;
	private final Queue<AbstractGameAction> pendingActions;

	/**
	 * public constructor
	 * @param past list of past actions
	 * @param pending list of actions to be executed
	 */
	public CommandQueueData(List<AbstractGameAction> past, Queue<AbstractGameAction> pending)
	{
		pastAction = past;
		pendingActions = pending;
	}

	/**
	 * public constructor
	 * @param action add an action to the this queue
	 * @return the modified queue
	 */
	public CommandQueueData offerPendingActions(AbstractGameAction action)
	{
		Queue<AbstractGameAction> pending = new LinkedList<>(pendingActions);
		pending.offer(action);
		return new CommandQueueData(pastAction, pending);
	}

	/**
	 *
	 * @return without removing the oldest action received
	 */
	public AbstractGameAction peekPending()
	{
		return pendingActions.peek();
	}

	/**
	 * remove and execute the oldest action received
	 * @return the modified queue
	 */
	public CommandQueueData popPendingAction()
	{
		Queue<AbstractGameAction> pending = new LinkedList<>(pendingActions);
		pending.poll();
		return new CommandQueueData(pastAction, pending);
	}


	/**
	 *
	 * @return the number of actions to be executed
	 */
	public int pendingSize()
	{
		return pendingActions.size();
	}

	/**
	 * return an already executed action
	 * @param index index of the action
	 * @return the required action
	 */
	public AbstractGameAction getPastItem(int index)
	{
		if (pastSize() <= index)
			return null;
		return pastAction.get(index);
	}

	/**
	 *
	 * @return the number of executed actions
	 */
	public int pastSize()
	{
		return pastAction.size();
	}

	/**
	 * Add an action to the list of past actions
	 * @param action action to be added
	 * @return the modified queue
	 */
	public CommandQueueData addPastAction(AbstractGameAction action)
	{
		ArrayList<AbstractGameAction> pasts = new ArrayList<>(pastAction);
		pasts.add(action);
		return new CommandQueueData(pasts, pendingActions);

	}
}
