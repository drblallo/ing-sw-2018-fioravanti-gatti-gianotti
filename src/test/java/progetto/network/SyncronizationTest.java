package progetto.network;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.network.socket.ClientSocketFactory;
import progetto.network.socket.SocketServerFactory;
import progetto.utils.ObserverStub;

public abstract class SyncronizationTest extends SocketServerTestStub {

	@Before
	public void setUp() {
		startServer();
		wait(50);
		startClient(0);
		startClient(1);
		wait(50);
	}

	@After
	public void shutDown() {
		wait(50);
		tearDown();
		wait(50);
	}

	public SyncronizationTest(INetworkModuleFactory m, INetworkClientFactory f)
	{
		super(m, f);
	}

	@Test
	public void testSync()
	{

		ClientConnection cl1 = getClientConnection(0);
		ClientConnection cl2 = getClientConnection(1);
		cl1.createGame("testRoom");
		wait(50);
		cl1.fetchServerState();
		wait(50);
		wait(50);
		int roomID = cl1.getServerState().getRooms().get(0).getRoomID();
		cl1.sendSynString("SyncMeUp");
		cl1.joinGame(roomID, "randomName");
		cl2.joinGame(roomID, "extraName");


		wait(100);
		for (int a = 0; a < NetworkSettings.CHECK_SYNC_RATEO + 1; a++) {
			cl1.sendSynString("SyncMeUp");
			cl1.sendSynString("SyncMeUpNot");
			wait(100);
			Assert.assertEquals(
					cl1.getSynchronizedObject().getHash(a+1),
					cl2.getSynchronizedObject().getHash(a+1));
		}

		cl1.joinGame(-1, "randomName");
		wait(50);
		cl1.joinGame(roomID, "randomName");

		wait(500);
		Assert.assertEquals(cl1.getSynchronizedObject().getHash(),
							cl2.getSynchronizedObject().getHash());

	}


	@Test
	public void testFailSync()
	{
		ClientConnection clientConnection = getClientConnection(0);
		ClientConnection clientConnection2 = getClientConnection(1);
		clientConnection.createGame("testRoom");
		wait(50);
		clientConnection.fetchServerState();
		wait(50);
		wait(50);
		int roomID = clientConnection.getServerState().getRooms().get(0).getRoomID();
		clientConnection.joinGame(roomID, "randomName");
		clientConnection2.joinGame(roomID, "extraName");

		ObserverStub<ClientConnection> obs = new ObserverStub<ClientConnection>();
		obs.currentVal = null;
		clientConnection2.getSyncronizationFailedCallback().addObserver(obs);

		wait(500);
		for (int a = 0; a < NetworkSettings.CHECK_SYNC_RATEO + 1; a++) {
			clientConnection.sendSynString("SyncMeUp");
			wait(100);
			clientConnection2.getSynchronizedObject().clear();
			Assert.assertNotEquals(
					clientConnection.getSynchronizedObject().getHash(),
					clientConnection2.getSynchronizedObject().getHash());
		}

		Assert.assertNotNull(obs.currentVal);

	}

	@Test
	public void testJoinWithSameName()
	{
		ClientConnection clientConnection = getClientConnection(0);
		ClientConnection clientConnection2 = getClientConnection(1);
		clientConnection.createGame("testRoom");
		wait(50);
		clientConnection.fetchServerState();
		wait(50);
		wait(50);
		int roomID = clientConnection.getServerState().getRooms().get(0).getRoomID();
		clientConnection.joinGame(roomID, "randomName");
		clientConnection2.joinGame(roomID, "randomName");
		wait(100);
		Assert.assertEquals(roomID, clientConnection2.getRoom().getRoomID());
		Assert.assertEquals(roomID, clientConnection.getRoom().getRoomID());
		Assert.assertEquals(2, clientConnection2.getRoom().getPlayers().size());
		Assert.assertEquals(2, clientConnection.getRoom().getPlayers().size());


	}

	@Test
	public void testTTL()
	{
		ClientConnection clientConnection = getClientConnection(0);
		ClientConnection clientConnection2 = getClientConnection(1);

		ObserverStub<ClientConnection> obs2 = new ObserverStub<ClientConnection>();

		clientConnection.getConnectionEndedCallback().addObserver(obs2);
		getClientSocket(0).disconnect(false);
		wait(NetworkSettings.DEFAULT_TIME_TO_LIVE * (NetworkSettings.MAX_TIME_TO_LIVE_SKIPPED + 2));
		Assert.assertEquals(false, clientConnection.isRunning());
		Assert.assertEquals(true, clientConnection2.isRunning());
	}
}
