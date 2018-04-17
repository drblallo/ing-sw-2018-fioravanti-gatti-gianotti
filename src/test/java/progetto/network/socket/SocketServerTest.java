package progetto.network.socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import progetto.utils.Waiter;

import static org.junit.Assert.assertEquals;

public class SocketServerTest {

	private SocketServer stub = new SocketServer(8527);
	private Waiter john = new Waiter();


	@Before
	public void setUp() {
		stub.start();
	}

	@After
	public void shutDown() {
		john.wait(50);
		stub.stop();
		john.wait(50);
	}

	@Test
	public void testServerOpeningAndClosing() {
		stub.start();
		john.wait(50);
		assertEquals(true, stub.isRunning());

		SocketServer st2 = new SocketServer(8527);
		st2.start();
		john.wait(50);
		assertEquals(false, st2.isRunning());
		st2.start();
		john.wait(50);
		stub.stop();
		john.wait(50);
		st2.stop();
		john.wait(50);
		st2.stop();
		john.wait(50);


		assertEquals(false, st2.isRunning());
		assertEquals(false, stub.isRunning());
	}

}
