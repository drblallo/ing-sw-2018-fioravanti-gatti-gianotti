package progetto.game;


import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Command queue of the game
 */
public class CommandQueue extends DataContainer<CommandQueueData> implements AbstractProcessor<AbstractGameAction> {


	CommandQueue()
	{
		super(new CommandQueueData(new ArrayList<>(), new LinkedList<>()));
	}

	/**
	 * Append an action to the queue
	 * @param action the action that must be appended
	 */
	void offer(AbstractGameAction action)
	{
		setData(getData().offerPendingActions(action));
	}

	/**
	 *
	 * @return the oldest inserted value without removing it from the queue
	 */
	public AbstractGameAction peekPending()
	{
		return getData().peekPending();
	}

	/**
	 * Removes the oldest inserted values and places it in the already executed list
	 * @return the oldest inserted value
	 */
	AbstractGameAction pollPending()
	{

		AbstractGameAction action = getData().peekPending();

		if (action != null) {
			setData(getData().popPendingAction());
			setData(getData().addPastAction(action));
		}

		return action;
	}

	/**
	 *
	 * @return the number of pending actions
	 */
	public int getPendingItemsCount()
	{
		return getData().pendingSize();
	}

	/**
	 *
	 * @return the number of already executed actions
	 */
	public int getPastItemCount()
	{
		return getData().pastSize();
	}

	/**
	 *
	 * @param index the index of the action that is being retrieved
	 * @return a already executed action
	 */
	public AbstractGameAction getPastItem(int index)
	{
		return getData().getPastItem(index);
	}

}
