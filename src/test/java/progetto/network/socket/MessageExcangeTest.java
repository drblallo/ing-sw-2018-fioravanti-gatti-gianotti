package progetto.network.socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import progetto.network.connectionstate.PlayerInfo;
import progetto.utils.ObserverStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MessageExcangeTest {

	private static SocketServerTestStub stub = new SocketServerTestStub();

	@Before
	public void setUp()
	{
		stub.startServer();
		stub.wait(50);
		stub.startClient();
		stub.wait(50);
	}

	@After
	public void shutDown()
	{
		stub.wait(50);
		stub.tearDown();
		stub.wait(50);
	}

	@Test
	public void testCreateJoinRoomCommand()
	{
		stub.socketClient.setReady(true);
		assertEquals(0, stub.networkServer.getServerState().getRoomCount());
		assertEquals(0, stub.socketClient.getServerState().getRoomCount());
		stub.socketClient.createGame("testRoom");
		stub.wait(50);
		assertEquals(1, stub.networkServer.getServerState().getRoomCount());
		stub.socketClient.fetchServerState();
		stub.wait(50);
		assertEquals(1, stub.socketClient.getServerState().getRoomCount());

		assertEquals(false, stub.socketClient.isReady());
		stub.wait(50);
		int roomID = stub.socketClient.getServerState().getRooms().get(0).getRoomID();
		stub.socketClient.joinGame(roomID, "randomName");
		stub.socketClient.setReady(true);
		assertEquals("testRoom", stub.socketClient.getServerState().getRoom(roomID).getName());
		stub.wait(500);

		assertNotNull(stub.socketClient.getRoom());
		assertEquals(1, stub.socketClient.getRoom().getPlayers().size());
		assertEquals("testRoom", stub.socketClient.getRoom().getName());
		PlayerInfo info = stub.networkServer.getServerState().getRoom(roomID).getInfoFromID(stub.socketClient.getPlayerID());
		assertNotNull(info);
		assertNotNull(stub.socketClient.getRoom().getInfoFromID(info.getPlayerID()));
		assertEquals(info.getPlayerName(), stub.socketClient.getRoom().getInfoFromID(info.getPlayerID()).getPlayerName());
		assertEquals("randomName", stub.socketClient.getPlayerName());
		assertEquals(true, stub.socketClient.isReady());
		assertEquals(true, stub.networkServer.getServerState().playerExists(info.getPlayerID()));
		stub.wait(50);
	}

	@Test
	public void testJoinMissingRoom()
	{
		stub.socketClient.joinGame(10, "test");
		stub.wait(50);
		assertEquals(-1, stub.socketClient.getRoom().getRoomID());
	}

	@Test
	public void testLeave()
	{
		stub.socketClient.createGame("testRoom");
		stub.wait(50);
		stub.socketClient.fetchServerState();
		stub.wait(50);
		stub.wait(50);
		int roomID = stub.socketClient.getServerState().getRooms().get(0).getRoomID();
		stub.socketClient.joinGame(roomID, "randomName");
		stub.wait(50);
		stub.socketClient.joinGame(-1, "noName");
		stub.wait(500);
		assertEquals(0,stub.socketClient.getRoom().getPlayers().size());
		assertEquals(null, stub.networkServer.getServerState().getPlayer(0));
	}

	@Test
	public void testPickChair()
	{
		stub.socketClient.createGame("testRoom");
		stub.socketClient.pickChair(10);
		stub.wait(50);
		stub.socketClient.fetchServerState();
		stub.wait(50);
		stub.wait(50);
		assertEquals(-1, stub.socketClient.getChair());
		int roomID = stub.socketClient.getServerState().getRooms().get(0).getRoomID();
		stub.socketClient.joinGame(roomID, "randomName");

		stub.socketClient.pickChair(1);
		stub.wait(500);
		assertEquals(1, stub.socketClient.getChair());
		assertEquals(stub.socketClient.getPlayerID(), stub.socketClient.getRoom().getPlayerOfChair(1).getPlayerID());
		assertEquals(null, stub.socketClient.getRoom().getPlayerOfChair(-1));
		stub.socketClient.pickChair(-1);
		stub.wait(50);
		assertEquals(-1, stub.socketClient.getChair());
	}


	@Test
	public void testPrivateMessage()
	{

		stub.socketClient.createGame("testRoom");
		stub.wait(50);
		stub.socketClient.fetchServerState();
		stub.wait(50);
		stub.wait(50);
		int roomID = stub.socketClient.getServerState().getRooms().get(0).getRoomID();
		stub.socketClient.joinGame(roomID, "randomName");

		stub.socketClient.pickChair(1);
		stub.wait(500);
		ObserverStub<String> obs = new ObserverStub<String>();
		obs.currentVal = "none";
		stub.socketClient.getMessageCallback().addObserver(obs);
		stub.socketClient.sendPrivateMessage("Message", stub.socketClient.getPlayerID());
		stub.socketClient.sendPrivateMessage("MEssage", 1000);
		stub.wait(50);
		assertEquals("randomName: Message", obs.currentVal);
	}

}
