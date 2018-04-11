package progetto.utils;

public class ObserverStub implements IObserver<String> {

	public String currentVal;
	public void notifyChange(String ogg) {
		currentVal = ogg;
	}
}
