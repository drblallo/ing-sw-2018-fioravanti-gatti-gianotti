package progetto.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A AbstractObservable is a class that must be extended to be able to be observed by a IObserver.
 * Notice that is you prefer composition over inheritance it's better to create a Callback object instead.
 * <p>
 * AbstractObservables are thread safe.
 *
 * @param <T> The type that is passed to IObserver when called.
 */
public abstract class AbstractObservable<T>
{

	private static final Logger LOGGER = Logger.getLogger(AbstractObservable.class.getName());
	private final List<IObserver<T>> currentObservers = Collections.synchronizedList(new ArrayList<IObserver<T>>());
	private final List<IObserver<T>> toBeRemovedObservers = Collections.synchronizedList(new ArrayList<IObserver<T>>());
	private int suspensions = 0;

	/**
	 * removes all the observer marked for deletion.
	 * this should only be called when we are sure that no other change is being executed.
	 */
	private void fixLists()
	{
		if (!toBeRemovedObservers.isEmpty())
		{
			LOGGER.log(Level.FINE, "removing {0} observers", toBeRemovedObservers.size());
			currentObservers.removeAll(toBeRemovedObservers);
			toBeRemovedObservers.clear();
		}
	}

	/**
	 * call this method when you wish to notify a change to every observer.
	 *
	 * @param value the value that must be sent to every listener
	 */
	protected final synchronized void change(T value)
	{
		fixLists();

		LOGGER.log(Level.FINE, "notifying observers");

		if (!isSuspended())
		{
			for (IObserver<T> o : currentObservers)
				o.notifyChange(value);
		}
		fixLists();
	}

	/**
	 * add a observer to the observer list, there cannot be multiple copies of observers.
	 *
	 * @param obs the observer to be added.
	 */
	public final synchronized void addObserver(IObserver<T> obs)
	{
		if (!currentObservers.contains(obs))
		{
			LOGGER.log(Level.FINE, "adding observer");
			currentObservers.add(obs);
		}
		else
		{
			LOGGER.log(Level.FINE, "observer already existed");
		}
	}

	/**
	 * mark an observer for deletion, it will be deleted the next time change is called.
	 *
	 * @param obs the observer to be marked
	 */
	public final synchronized void removeObserver(IObserver<T> obs)
	{
		if (!toBeRemovedObservers.contains(obs) && currentObservers.contains(obs))
		{
			LOGGER.log(Level.FINE, "marking observer for removal");
			toBeRemovedObservers.add(obs);
		}
		else
		{
			LOGGER.log(Level.FINE, "observer could not be marked for removal");
		}
	}

	/**
	 * mark all current existing observer for deletion
	 */
	public final synchronized void clearObserver()
	{
		LOGGER.log(Level.FINE, "marking all observer for removal");
		toBeRemovedObservers.addAll(currentObservers);
	}

	/**
	 * @return The number of observer that will be called the next time change is called.
	 */
	public final synchronized int getObserversCount()
	{
		return currentObservers.size() - toBeRemovedObservers.size();
	}

	/**
	 * prevent the observable from notify changes.
	 * if it's called twice then it must be resumed twice
	 */
	public final void stop()
	{
		suspensions++;
	}

	/**
	 * resume the notifications
	 */
	public final void start()
	{
		if (suspensions > 0)
			suspensions--;
		else
			LOGGER.log(Level.FINE, "tried to restart the observable too many times");
	}

	/**
	 * @return true if the observable is suspended
	 */
	public final boolean isSuspended()
	{
		return (suspensions > 0);
	}


}
