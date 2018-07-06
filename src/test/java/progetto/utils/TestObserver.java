package progetto.utils;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestObserver
{

	@Test
	public void testAttach()
	{
		ObserverStub<String> obs = new ObserverStub<>();
		ObservableStub s = new ObservableStub();
		obs.currentVal = "NONE";
		s.triggerChange("val");
		assertEquals("NONE", obs.currentVal);
		assertEquals(0, s.getObserversCount());

		s.addObserver(obs);
		assertEquals(1, s.getObserversCount());
		s.triggerChange("val");
		assertEquals( "val", obs.currentVal);
	}

	@Test
	public void testDetach()
	{
		ObserverStub<String> obs = new ObserverStub<String>();
		ObservableStub s = new ObservableStub();
		obs.currentVal = "NONE";

		s.removeObserver(obs);
		s.addObserver(obs);
		s.addObserver(obs);
		assertEquals(1, s.getObserversCount());
		s.removeObserver(obs);
		s.removeObserver(obs);
		assertEquals(0, s.getObserversCount());
		s.triggerChange("val");
		assertEquals("NONE", obs.currentVal);

	}

	@Test
	public void testAttachDeatchBeforeCall()
	{
		ObserverStub<String> obs = new ObserverStub<String>();
		ObservableStub s = new ObservableStub();

		s.addObserver(obs);
		s.removeObserver(obs);
		s.addObserver(obs);

		assertEquals(1, s.getObserversCount());
		s.removeObserver(obs);
		assertEquals(0, s.getObserversCount());
	}

	@Test
	public void testStop()
	{
		ObserverStub<String> obs = new ObserverStub<String>();
		ObservableStub s = new ObservableStub();
		obs.currentVal = "NONE";
		s.triggerChange("val");
		assertEquals("NONE", obs.currentVal);
		assertEquals(0, s.getObserversCount());

		s.addObserver(obs);
		assertEquals(1, s.getObserversCount() );
		s.stop();
		s.triggerChange("val");
		s.start();
		s.start();
		assertEquals( "NONE", obs.currentVal);
		s.triggerChange("val");
		assertEquals( "val", obs.currentVal);

	}

	@Test
	public void testClear()
	{
		ObserverStub<String> obs = new ObserverStub<String>();
		ObservableStub s = new ObservableStub();
		obs.currentVal = "NONE";

		s.addObserver(obs);
		assertEquals(1, s.getObserversCount() );
		s.clearObserver();
		assertEquals(0, s.getObserversCount());
		s.triggerChange("val");
		assertEquals( "NONE", obs.currentVal);

	}
}
