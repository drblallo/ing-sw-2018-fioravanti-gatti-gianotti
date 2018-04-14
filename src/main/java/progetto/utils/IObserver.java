package progetto.utils;

/**
 *	Class that must be implemented to be able to be attached to a AbstractObservable.
 * @param <T> type of argument of notifyChange
 */
public interface IObserver<T> {

	/**
	 * Called every this the observed AbstractObservables desire to notify a change.
	 */
	void notifyChange(T ogg);
}
