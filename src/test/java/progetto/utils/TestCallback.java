package progetto.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCallback
{
	@Test
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
