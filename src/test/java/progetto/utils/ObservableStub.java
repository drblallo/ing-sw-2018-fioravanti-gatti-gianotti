package progetto.utils;

public class ObservableStub extends AbstractObservable<String> {

	public void triggerChange(String s)
	{
		change(s);
	}
}