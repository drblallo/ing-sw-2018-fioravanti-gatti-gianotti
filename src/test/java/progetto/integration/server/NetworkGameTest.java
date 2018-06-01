package progetto.integration.server;


import org.junit.*;
import progetto.controller.*;
import progetto.integration.client.ClientController;
import progetto.model.EndTurnAction;
import progetto.model.FrameSelectionState;
import progetto.model.FrameSetAction;
import progetto.model.RoundState;
import progetto.network.INetworkModule;
import progetto.network.NetworkServer;
import progetto.network.socket.SocketServer;
import progetto.utils.Waiter;

public class NetworkGameTest
{

	private Waiter paul = new Waiter();
	private static NetworkServer server = new NetworkServer(new ServerGameFactory());
	private static INetworkModule module = new SocketServer(8527);
	private ClientController game =new ClientController();

	@BeforeClass
	public static void beforeClass()
	{
		server.addModules(module);
	}

	public void wait(int milliseconds)
	{
		paul.wait(milliseconds);
	}

	@After
	public void after()
	{
		paul.wait(100);
		server.stop();
		if (game != null)
			game.disconnect();

		paul.wait(300);
	}

	@Before
	public void before()
	{
		server.start();
		paul.wait(50);
		game.createConnection("127.0.0.1", false);
		paul.wait(100);
		game.createGame("room");
		game.joinGame(0,"test");
		paul.wait(100);

	}

	@Test
	public void testGameStart()
	{
		game.sendAction(new StartGameAction());
		wait(1000);
		Assert.assertEquals(FrameSelectionState.class, game.getModel().getMainBoard().getData().getGameState().getClass());

		game.getModel().getCommandQueue();
		game.getModel().getMainBoard().getData().getGameState();
		game.getModel().getMainBoard();
		game.getModel().getPlayerBoard(0);
		game.getModel().getMainBoard().getData().getPlayerCount();
		game.getModel().getRoundTrack();
		game.processAction();
		game.processAllPendingAction();
	}

	@Test
	public void testMultipleSetSeed()
	{
		wait(500);
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(3));
		game.sendAction(new SetPlayerCountAction(3));

		wait(500);
		Assert.assertEquals(3, game.getModel().getMainBoard().getData().getPlayerCount());
	}


	@Test
	public void testAbstractGameSync()
	{
		ServerGame game = new ServerGame();
		Assert.assertFalse(game.isItemGood(null, -1));

		Assert.assertFalse(game.isItemGood(new StartGameAction(), 2));
	}

	@Test
	public void testProxy()
	{
		//Logger.getLogger(ServerState.class.getPackage().getName()).setLevel(Level.FINE);
		//Logger.getLogger(ServerState.class.getPackage().getName()).getParent().getHandlers()[0].setLevel(Level.FINE);
		ClientController g = (ClientController) game;
		wait(400);
		game.sendAction(new SetPlayerCountAction(1));
		game.sendAction(new StartGameAction());
		game.sendAction(new FrameSetAction(0, 0, 1));
		wait(500);
		Assert.assertEquals(
				"room",
				g.getCurrentRoom().getRoomName());
		Assert.assertEquals(
				RoundState.class.getName(),
				game.getModel().getMainBoard().getData().getGameState().getClass().getName());
		Assert.assertEquals(
				RoundState.class.getName(),
				game.getModel().getMainBoard().getData().getGameState().getClass().getName());

		g.pickChair(
				g.getModel().getMainBoard().getData().getCurrentPlayer()
		);

		wait(400);



		game.sendAction(new PickDiceAction(game.getModel().getMainBoard().getData().getCurrentPlayer(), 1));
		game.sendAction(new PlaceDiceAction(game.getModel().getMainBoard().getData().getCurrentPlayer(), 0, 0, 0));
		game.sendAction(new EndTurnAction(game.getModel().getMainBoard().getData().getCurrentPlayer()));
		game.sendAction(new EndTurnAction(game.getModel().getMainBoard().getData().getCurrentPlayer()));
		game.sendAction(new EndTurnAction(game.getModel().getMainBoard().getData().getCurrentPlayer()));
		wait(500);

	}

}
