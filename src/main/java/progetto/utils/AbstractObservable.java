package progetto.utils;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractObservable<T> {

	private ArrayList<IObserver<T>> currentObservers = new ArrayList<IObserver<T>>();
	private ArrayList<IObserver<T>> toBeRemovedObservers = new ArrayList<IObserver<T>>();
	private static final Logger LOGGER = Logger.getLogger(AbstractObservable.class.getName());

	private void fixLists()
	{
		if (!toBeRemovedObservers.isEmpty())
		{
			LOGGER.log(Level.FINE, "removing {0} observers", toBeRemovedObservers.size());
			currentObservers.removeAll(toBeRemovedObservers);
			toBeRemovedObservers.clear();
		}
	}

	protected final synchronized void change(T value)
	{
		fixLists();

		LOGGER.log(Level.FINE, "notifying observers");
		for (int a = 0; a < currentObservers.size(); a++)
			currentObservers.get(a).notifyChange(value);
	}

	public final synchronized void addObserver(IObserver<T> obs)
	{
		if (!currentObservers.contains(obs)) {
			LOGGER.log(Level.FINE, "adding observer");
			currentObservers.add(obs);
		}
		else
		{
			LOGGER.log(Level.FINE, "observer already existed");
		}
	}

	public final synchronized void removeObserver(IObserver<T> obs)
	{
		if (!toBeRemovedObservers.contains(obs) && currentObservers.contains(obs)) {
			LOGGER.log(Level.FINE, "marking observer for removal");
			toBeRemovedObservers.add(obs);
		}
		else
		{

			LOGGER.log(Level.FINE, "observer could not be marked for removal");
		}
	}

	public final synchronized void clearObserver()
	{
		LOGGER.log(Level.FINE, "marking all observer for removal");
		toBeRemovedObservers.addAll(currentObservers);
	}

	public final synchronized int getObserversCount()
	{
		return currentObservers.size() - toBeRemovedObservers.size();
	}

}
