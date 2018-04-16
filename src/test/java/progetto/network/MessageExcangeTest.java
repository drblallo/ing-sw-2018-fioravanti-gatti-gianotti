package progetto.network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import progetto.network.connectionstate.PlayerInfo;
import progetto.network.socket.ClientSocketFactory;
import progetto.network.socket.SocketServerFactory;
import progetto.utils.ObserverStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class MessageExcangeTest extends SocketServerTestStub {

	@Before
	public void setUp()
	{
		startServer();
		wait(50);
		startClient(0);
		wait(50);
	}

	@After
	public void shutDown()
	{
		wait(50);
		tearDown();
		wait(50);
	}

	public MessageExcangeTest(INetworkModuleFactory m, INetworkClientFactory f)
	{
		super(m, f);
	}

	@Test
	public void testCreateJoinRoomCommand()
	{
		ClientConnection cl = getClientConnection(0);

		cl.setReady(true);
		assertEquals(0, networkServer.getServerState().getRoomCount());
		assertEquals(0, cl.getServerState().getRoomCount());
		getClientConnection(0).createGame("testRoom");
		wait(50);
		assertEquals(1, networkServer.getServerState().getRoomCount());
		cl.fetchServerState();
		wait(50);
		assertEquals(1, cl.getServerState().getRoomCount());

		assertEquals(false, cl.isReady());
		wait(50);
		int roomID = cl.getServerState().getRooms().get(0).getRoomID();
		cl.joinGame(roomID, "randomName");
		cl.setReady(true);
		assertEquals("testRoom", cl.getServerState().getRoom(roomID).getName());
		wait(500);

		assertNotNull(cl.getRoom());
		assertEquals(1, cl.getRoom().getPlayers().size());
		assertEquals("testRoom", cl.getRoom().getName());
		PlayerInfo info = networkServer.getServerState().getRoom(roomID).getInfoFromID(cl.getPlayerID());
		assertNotNull(info);
		assertNotNull(cl.getRoom().getInfoFromID(info.getPlayerID()));
		assertEquals(info.getPlayerName(), cl.getRoom().getInfoFromID(info.getPlayerID()).getPlayerName());
		assertEquals("randomName", cl.getPlayerName());
		assertEquals(true, cl.isReady());
		assertEquals(true, networkServer.getServerState().playerExists(info.getPlayerID()));
		wait(50);
		networkServer.getServerState().deleteRoom(roomID);
		wait(500);
		assertEquals(-1, cl.getRoom().getRoomID());
	}

	@Test
	public void testJoinMissingRoom()
	{
		getClientConnection(0).joinGame(10, "test");
		wait(50);
		assertEquals(-1, getClientConnection(0).getRoom().getRoomID());
	}

	@Test
	public void testLeave()
	{
		ClientConnection cl = getClientConnection(0);
		cl.createGame("testRoom");
		wait(50);
		cl.fetchServerState();
		wait(50);
		wait(50);
		int roomID = cl.getServerState().getRooms().get(0).getRoomID();
		cl.joinGame(roomID, "randomName");
		wait(50);
		cl.joinGame(-1, "noName");
		wait(500);
		assertEquals(0, cl.getRoom().getPlayers().size());
		assertEquals(null, networkServer.getServerState().getPlayer(0));
	}

	@Test
	public void testPickChair()
	{
		ClientConnection cl = getClientConnection(0);
		cl.createGame("testRoom");
		cl.pickChair(10);
		wait(50);
		cl.fetchServerState();
		wait(100);
		assertEquals(-1, cl.getChair());
		int roomID = cl.getServerState().getRooms().get(0).getRoomID();
		cl.joinGame(roomID, "randomName");

		cl.pickChair(1);
		wait(500);
		assertEquals(1, cl.getChair());
		assertEquals(cl.getPlayerID(), cl.getRoom().getPlayerOfChair(1).getPlayerID());
		assertEquals(null, cl.getRoom().getPlayerOfChair(-1));
		cl.pickChair(-1);
		wait(50);
		assertEquals(-1, cl.getChair());
	}


	@Test
	public void testPrivateMessage()
	{

		ClientConnection cl = getClientConnection(0);
		cl.createGame("testRoom");
		wait(50);
		cl.fetchServerState();
		wait(50);
		wait(50);
		int roomID = cl.getServerState().getRooms().get(0).getRoomID();
		cl.joinGame(roomID, "randomName");

		cl.pickChair(1);
		wait(500);
		ObserverStub<String> obs = new ObserverStub<String>();
		obs.currentVal = "none";
		cl.getMessageCallback().addObserver(obs);
		cl.sendPrivateMessage("Message", cl.getPlayerID());
		cl.sendPrivateMessage("MEssage", 1000);
		wait(100);
		assertEquals("randomName: Message", obs.currentVal);
	}

}
