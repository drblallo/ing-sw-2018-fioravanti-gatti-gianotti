package progetto.utils;

import junit.framework.TestCase;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestObserver extends TestCase {

	public void testAttach()
	{
		ObserverStub obs = new ObserverStub();
		ObservableStub s = new ObservableStub();
		obs.currentVal = "NONE";
		s.triggerChange("val");
		assertEquals(obs.currentVal, "NONE");
		assertEquals(s.getObserversCount(), 0);

		s.addObserver(obs);
		assertEquals(s.getObserversCount(), 1);
		s.triggerChange("val");
		assertEquals(obs.currentVal,"val");
	}

	public void testDetach()
	{
		ObserverStub obs = new ObserverStub();
		ObservableStub s = new ObservableStub();
		obs.currentVal = "NONE";

		s.removeObserver(obs);
		s.addObserver(obs);
		s.addObserver(obs);
		assertEquals(s.getObserversCount(), 1);
		s.removeObserver(obs);
		s.removeObserver(obs);
		assertEquals(s.getObserversCount(), 0);
		s.triggerChange("val");
		assertEquals(obs.currentVal,"NONE");

	}

	public void testClear()
	{
		ObserverStub obs = new ObserverStub();
		ObservableStub s = new ObservableStub();
		obs.currentVal = "NONE";

		s.addObserver(obs);
		assertEquals(s.getObserversCount(), 1);
		s.clearObserver();
		assertEquals(s.getObserversCount(), 0);
		s.triggerChange("val");
		assertEquals(obs.currentVal,"NONE");

	}
}
