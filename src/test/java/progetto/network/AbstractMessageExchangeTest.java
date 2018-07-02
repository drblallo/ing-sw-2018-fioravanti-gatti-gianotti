package progetto.network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import progetto.utils.ObserverStub;

import static org.junit.Assert.*;

public abstract class AbstractMessageExchangeTest extends AbstractNetworkTestStub {

	public AbstractMessageExchangeTest(INetworkModuleFactory m, INetworkClientFactory f) {
		super(m, f);
	}

	@Before
	public void setUp() {
		startServer();
		wait(SHORT_WAIT);
		startClient(0);
		wait(SHORT_WAIT);
	}

	@After
	public void shutDown() {
		wait(SHORT_WAIT);
		tearDown();
		wait(SHORT_WAIT);
	}

	@Test
	public void testCreateJoinRoomCommand() {
		ClientConnection cl = getClientConnection(0);

		cl.setReady(true);
		assertEquals(1, networkServer.getServerStateClone().getRoomCount());
		assertEquals(0, cl.getServerState().getRoomCount());
		getClientConnection(0).createGame("testRoom");
		wait(SHORT_WAIT);
		assertEquals(2, networkServer.getServerStateClone().getRoomCount());
		cl.fetchServerState();
		wait(SHORT_WAIT);
		assertEquals(2, cl.getServerState().getRoomCount());

		assertEquals(false, cl.isReady());
		wait(SHORT_WAIT);
		int roomID = cl.getServerState().getRoomFromName("testRoom").roomID;
		cl.joinGame(roomID, "randomName");
		wait(MEDIUM_WAIT);
		cl.setReady(true);
		assertEquals("testRoom", cl.getServerState().getRoom(roomID).roomName);
		wait(LONG_WAIT);

		assertNotNull(cl.getRoom());
		assertEquals(1, cl.getRoom().getPlayerCount());
		assertEquals("testRoom", cl.getRoom().getRoomName());
		PlayerView info = networkServer.getRoomView(roomID).getPlayer(cl.getPlayerID());
		assertNotNull(info);
		assertNotNull(cl.getRoom().getPlayer(info.getId()));
		assertEquals(info.getName(), cl.getRoom().getPlayer(info.getId()).getName());
		assertEquals("randomName", cl.getPlayerName());
		assertEquals(true, cl.isReady());
		assertEquals(true, networkServer.getRoomView(cl.getRoom().getRoomID()).getPlayer(info.getId()) != null);
		wait(SHORT_WAIT);
		networkServer.stop();
		wait(LONG_WAIT);
		assertEquals(0, cl.getRoom().getRoomID());
	}

	@Test
	public void testJoinMissingRoom() {
		getClientConnection(0).joinGame(10, "test");
		wait(SHORT_WAIT);
		assertEquals(-1, getClientConnection(0).getRoom().getRoomID());
		assertNull(networkServer.getServerStateClone().getRoomFromName("false"));
	}

	@Test
	public void testLeave()
	{
		ClientConnection cl = getClientConnection(0);
		cl.createGame("testRoom");
		wait(SHORT_WAIT);
		cl.fetchServerState();
		wait(MEDIUM_WAIT);
		int roomID = cl.getServerState().getRoomFromName("testRoom").roomID;
		cl.joinGame(roomID, "randomName");
		wait(SHORT_WAIT);
		cl.joinGame(-1, "noName");
		wait(LONG_WAIT);
		assertEquals(0, cl.getRoom().getPlayerCount());
		assertEquals(null, networkServer.getRoomView(roomID));
	}

	@Test
	public void testPickChair() {
		ClientConnection cl = getClientConnection(0);
		cl.createGame("testRoom");
		cl.pickChair(10);
		wait(SHORT_WAIT);
		cl.fetchServerState();
		wait(MEDIUM_WAIT);
		assertEquals(-1, cl.getChair());
		int roomID = cl.getServerState().getRoomFromName("testRoom").roomID;
		cl.joinGame(roomID, "randomName");

		wait(SHORT_WAIT);
		cl.pickChair(1);
		cl.pickChair(1);
		wait(LONG_WAIT);
		wait(MEDIUM_WAIT);
		assertEquals(1, cl.getChair());

		assertEquals(cl.getPlayerID(), cl.getRoom().getPlayerOfChair(1).getId());
		assertEquals(null, cl.getRoom().getPlayerOfChair(-1));
		cl.pickChair(-1);
		wait(SHORT_WAIT);
		assertEquals(-1, cl.getChair());
		assertNull(cl.getRoom().getPlayerOfChair(10));
	}


	@Test
	public void testPrivateMessage() {

		ClientConnection cl = getClientConnection(0);
		cl.createGame("testRoom");
		wait(SHORT_WAIT);
		cl.fetchServerState();
		wait(SHORT_WAIT);
		wait(SHORT_WAIT);
		int roomID = cl.getServerState().getRoomFromName("testRoom").roomID;
		cl.joinGame(roomID, "randomName");

		cl.pickChair(1);
		wait(LONG_WAIT);
		ObserverStub<String> obs = new ObserverStub<String>();
		obs.currentVal = "none";
		cl.getMessageCallback().addObserver(obs);
		cl.sendPrivateMessage("Message", cl.getPlayerID());
		cl.sendPrivateMessage("MEssage", 1000);
		wait(LONG_WAIT);
		assertEquals("randomName: Message", obs.currentVal);
	}

}
