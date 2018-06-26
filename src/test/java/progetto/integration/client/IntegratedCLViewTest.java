package progetto.integration.client;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.integration.client.view.cl.CommandLineView;
import progetto.integration.client.view.cl.RoundViewState;
import progetto.integration.server.ServerGameFactory;
import progetto.network.NetworkServer;
import progetto.network.rmi.RMIModule;
import progetto.network.socket.SocketServer;
import progetto.utils.Waiter;

public class IntegratedCLViewTest {
	private NetworkServer networkServer;
	private Waiter timoty = new Waiter();

	private CommandLineView view;
	private ClientController controller;

	@Before
	public void before()
	{
		networkServer = new NetworkServer(new ServerGameFactory());
		networkServer.addModules(new RMIModule());
		networkServer.addModules(new SocketServer(8527));
		networkServer.start();
		timoty.wait(100);

		controller = new ClientController();
		view = new CommandLineView(controller, System.out);
		new Thread(view).start();
		timoty.wait(100);
	}

	@After
	public void after()
	{
		networkServer.stop();
		timoty.wait(100);
	}

	@Test
	public void testPickWindow()
	{
		/*Logger.getLogger(ClientMain.class.getPackage().getName()).getParent().getHandlers()[0].setLevel(Level.FINE);
		Logger.getLogger(ClientMain.class.getPackage().getName()).setLevel(Level.FINE);*/
		view.execute("1");
		timoty.wait(400);
		view.execute("1 localhost");
		timoty.wait(400);
		view.execute("1 test");
		timoty.wait(400);
		view.execute("2"); //join room
		timoty.wait(400);
		view.execute("2");//select room
		timoty.wait(400);
		view.execute("2 1"); //setPlayer count
		timoty.wait(400);
		view.execute("3"); //pick chair
		timoty.wait(400);
		view.execute("1");
		timoty.wait(400);
		view.execute("1"); //start game
		timoty.wait(400);
		view.execute("1"); //select frame
		timoty.wait(400);
		Assert.assertEquals(view.getAbstractCLViewState().getClass(), RoundViewState.class);
	}
}
