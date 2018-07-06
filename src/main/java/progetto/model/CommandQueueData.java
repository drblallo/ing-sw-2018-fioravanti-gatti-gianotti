package progetto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Michele
 */
public class CommandQueueData implements Serializable{

	private final List<AbstractGameAction> pastAction;
	private final Queue<AbstractGameAction> pendingActions;


	public CommandQueueData(List<AbstractGameAction> past, Queue<AbstractGameAction> pending)
	{
		pastAction = past;
		pendingActions = pending;
	}


	public CommandQueueData offerPendingActions(AbstractGameAction action)
	{
		Queue<AbstractGameAction> pending = new LinkedList<>(pendingActions);
		pending.offer(action);
		return new CommandQueueData(pastAction, pending);
	}

	public AbstractGameAction peekPending()
	{
		return pendingActions.peek();
	}

	public CommandQueueData popPendingAction()
	{
		Queue<AbstractGameAction> pending = new LinkedList<>(pendingActions);
		pending.poll();
		return new CommandQueueData(pastAction, pending);
	}


	public int pendingSize()
	{
		return pendingActions.size();
	}

	public AbstractGameAction getPastItem(int index)
	{
		if (pastSize() <= index)
			return null;
		return pastAction.get(index);
	}

	public int pastSize()
	{
		return pastAction.size();
	}


	public CommandQueueData addPastAction(AbstractGameAction action)
	{
		ArrayList<AbstractGameAction> pasts = new ArrayList<>(pastAction);
		pasts.add(action);
		return new CommandQueueData(pasts, pendingActions);

	}
}
