package progetto.utils;

import junit.framework.TestCase;

public class TestCallback extends TestCase
{
	public void testCallback()
	{
		ObserverStub<String> stub = new ObserverStub<String>();
		stub.currentVal = "NONE";
		Callback<String> callback = new Callback<String>();
		callback.addObserver(stub);
		callback.call("newValue");
		assertEquals(stub.currentVal, "newValue");
	}


}
