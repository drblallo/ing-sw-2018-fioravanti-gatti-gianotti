package progetto.game;

/**
 * This interface that represents a object that can receive items to be processed.
 * Items are left pending util the relative method is called
 */
public interface IProcessor<T>
{
	/**
	 * Append a item to the list of pending items.
	 * @param item the serialized command.
	 */
	void sendItem(T item);

	/**
	 * @param itemToProcessCount count of items that must be processed.
	 */
	void processItems(int itemToProcessCount);

	/**
	 * processes all items
	 */
	void processAllItems();

	/**
	 *
	 * @return the count of pending items
	 */
	int getPendingItemsCount();

	/**
	 *
	 * @param index of the pending item
	 * @return the item of the pending list
	 */
	T getPendingItem(int index);

	/**
	 * number of item received by this item.
	 * pending items are not part of this count
	 */
	void getPastItemCount();

	/**
	 *
	 * @return the past item at index
	 * pending items are not part of this count.
	 */
	T getPastItem(int index);


}
