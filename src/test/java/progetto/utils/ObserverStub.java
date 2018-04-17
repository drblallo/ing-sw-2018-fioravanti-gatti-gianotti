package progetto.utils;

public class ObserverStub<T> implements IObserver<T> {

	public T currentVal;

	public void notifyChange(T ogg) {
		currentVal = ogg;
	}
}
