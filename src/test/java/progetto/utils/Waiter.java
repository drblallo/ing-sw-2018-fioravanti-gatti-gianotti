package progetto.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Waiter {
	public CountDownLatch latch = new CountDownLatch(1);

	public void wait(int milliseconds) {
		try {
			latch.await(milliseconds, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {

		}
	}
}
