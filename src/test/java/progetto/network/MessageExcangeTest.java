package progetto.network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import progetto.network.connectionstate.PlayerInfo;
import progetto.utils.ObserverStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class MessageExcangeTest extends SocketServerTestStub {

	public MessageExcangeTest(INetworkModuleFactory m, INetworkClientFactory f) {
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
		assertEquals(0, networkServer.getServerStateClone().getRoomCount());
		assertEquals(0, cl.getServerState().getRoomCount());
		getClientConnection(0).createGame("testRoom");
		wait(SHORT_WAIT);
		assertEquals(1, networkServer.getServerStateClone().getRoomCount());
		cl.fetchServerState();
		wait(SHORT_WAIT);
		assertEquals(1, cl.getServerState().getRoomCount());

		assertEquals(false, cl.isReady());
		wait(SHORT_WAIT);
		int roomID = cl.getServerState().getRooms().get(0).getRoomID();
		cl.joinGame(roomID, "randomName");
		wait(MEDIUM_WAIT);
		cl.setReady(true);
		assertEquals("testRoom", cl.getServerState().getRoom(roomID).getName());
		wait(LONG_WAIT);

		assertNotNull(cl.getRoom());
		assertEquals(1, cl.getRoom().getPlayers().size());
		assertEquals("testRoom", cl.getRoom().getName());
		PlayerInfo info = networkServer.getServerStateClone().getRoom(roomID).getInfoFromID(cl.getPlayerID());
		assertNotNull(info);
		assertNotNull(cl.getRoom().getInfoFromID(info.getPlayerID()));
		assertEquals(info.getPlayerName(), cl.getRoom().getInfoFromID(info.getPlayerID()).getPlayerName());
		assertEquals("randomName", cl.getPlayerName());
		assertEquals(true, cl.isReady());
		assertEquals(true, networkServer.getServerStateClone().playerExists(info.getPlayerID()));
		wait(SHORT_WAIT);
		networkServer.getServerStateClone().deleteRoom(roomID);
		wait(LONG_WAIT);
		assertEquals(0, cl.getRoom().getRoomID());
	}

	@Test
	public void testJoinMissingRoom() {
		getClientConnection(0).joinGame(10, "test");
		wait(SHORT_WAIT);
		assertEquals(-1, getClientConnection(0).getRoom().getRoomID());
	}

	@Test
	public void testLeave() {
		ClientConnection cl = getClientConnection(0);
		cl.createGame("testRoom");
		wait(SHORT_WAIT);
		cl.fetchServerState();
		wait(MEDIUM_WAIT);
		int roomID = cl.getServerState().getRooms().get(0).getRoomID();
		cl.joinGame(roomID, "randomName");
		wait(SHORT_WAIT);
		cl.joinGame(-1, "noName");
		wait(LONG_WAIT);
		assertEquals(0, cl.getRoom().getPlayers().size());
		assertEquals(null, networkServer.getServerStateClone().getPlayer(0));
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
		int roomID = cl.getServerState().getRooms().get(0).getRoomID();
		cl.joinGame(roomID, "randomName");

		wait(SHORT_WAIT);
		cl.pickChair(1);
		wait(LONG_WAIT);
		wait(MEDIUM_WAIT);
		assertEquals(1, cl.getChair());

		assertEquals(cl.getPlayerID(), cl.getRoom().getPlayerOfChair(1).getPlayerID());
		assertEquals(null, cl.getRoom().getPlayerOfChair(-1));
		cl.pickChair(-1);
		wait(SHORT_WAIT);
		assertEquals(-1, cl.getChair());
	}


	@Test
	public void testPrivateMessage() {

		ClientConnection cl = getClientConnection(0);
		cl.createGame("testRoom");
		wait(SHORT_WAIT);
		cl.fetchServerState();
		wait(SHORT_WAIT);
		wait(SHORT_WAIT);
		int roomID = cl.getServerState().getRooms().get(0).getRoomID();
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
