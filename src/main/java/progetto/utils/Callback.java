package progetto.utils;

/**
 * A extension of AbstractObservable that allows for composition.
 *
 * @param <T> The type of argument that is passed to observers.
 * @author Massimo
 */
public final class Callback<T> extends AbstractObservable<T> {

	/**
	 * notify every observer.
	 *
	 * @param param value received from the observable
	 */
	public void call(T param) {
		change(param);
	}
}
