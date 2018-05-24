package progetto.game;

/**
 * This interface that represents a object that can receive items to be processed.
 * Items are left pending util the relative method is called
 */
public interface AbstractProcessor<T> {

	/**
	 * @return the count of pending items
	 */
	public abstract int getPendingItemsCount();

	/**
	 * @return the oldest item of the pending list
	 */
	public abstract T peekPending();

	/**
	 * number of item received by this item.
	 * pending items are not part of this count
	 */
	public abstract int getPastItemCount();

	/**
	 * @return the past item at index
	 * pending items are not part of this count.
	 */
	public abstract T getPastItem(int index);


}
