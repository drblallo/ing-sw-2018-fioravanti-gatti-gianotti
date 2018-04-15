package progetto.network.socket.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import progetto.network.socket.SocketServerTestStub;

import static org.junit.Assert.assertEquals;

public class TestSocketServer
{

	private SocketServerTestStub stub = new SocketServerTestStub();

	@Before
	public void setUp()
	{
		stub.startServer();
	}

	@After
	public void shutDown()
	{
		stub.wait(50);
		stub.tearDown();
		stub.wait(50);
	}

	@Test
	public void testServerOpening()
	{
		stub.networkServer.addModules(stub.socketServer);
		stub.networkServer.start();
		stub.wait(50);
		assertEquals(true, stub.socketServer.isRunning());

		SocketServerTestStub st2 = new SocketServerTestStub();
		st2.startServer();
		stub.wait(50);
		assertEquals(false, st2.socketServer.isRunning());
		stub.socketServer.start();
		stub.tearDown();
		st2.tearDown();
		stub.wait(50);


		assertEquals(false, st2.socketServer.isRunning());
		assertEquals(false, stub.socketServer.isRunning());
	}

	@Test
	public void testServerClosing()
	{
		stub.wait(50);

		assertEquals(true, stub.socketServer.isRunning());

		stub.tearDown();
		stub.wait(50);
		assertEquals(false, stub.networkServer.isRunning());

		SocketServerTestStub stub2 = new SocketServerTestStub();
		stub2.startServer();
		assertEquals(true, stub2.networkServer.isRunning());
		stub2.networkServer.stop();
		assertEquals(false, stub2.networkServer.isRunning());

		stub.wait(50);
		stub2.tearDown();
	}
}
