package progetto.network.socket;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.network.INetworkClient;
import progetto.network.NetworkSettings;
import progetto.utils.ObserverStub;

public class SyncronizationTest {

	private static SocketServerTestStub stub = new SocketServerTestStub();

	@Before
	public void setUp() {
		stub.startServer();
		stub.wait(50);
		stub.startClient();
		stub.startClient2();
		stub.wait(50);
	}

	@After
	public void shutDown() {
		stub.wait(50);
		stub.tearDown();
		stub.wait(50);
	}

	@Test
	public void testSync()
	{

		stub.socketClient.createGame("testRoom");
		stub.wait(50);
		stub.socketClient.fetchServerState();
		stub.wait(50);
		stub.wait(50);
		int roomID = stub.socketClient.getServerState().getRooms().get(0).getRoomID();
		stub.socketClient.sendSyncCommand("SyncMeUp");
		stub.socketClient.joinGame(roomID, "randomName");
		stub.socketClient2.joinGame(roomID, "extraName");


		stub.wait(100);
		for (int a = 0; a < NetworkSettings.CHECK_SYNC_RATEO + 1; a++) {
			stub.socketClient.sendSyncCommand("SyncMeUp");
			stub.socketClient.sendSyncCommand("SyncMeUpNot");
			stub.wait(100);
			Assert.assertEquals(
					stub.socketClient.getSynchronizedObject().getHash(a+1),
					stub.socketClient2.getSynchronizedObject().getHash(a+1));
		}

		stub.socketClient.joinGame(-1, "randomName");
		stub.wait(50);
		stub.socketClient.joinGame(roomID, "randomName");

		stub.wait(500);
		Assert.assertEquals(stub.socketClient.getSynchronizedObject().getHash(),
							stub.socketClient2.getSynchronizedObject().getHash());

	}


	@Test
	public void testFailSync()
	{
		stub.socketClient.createGame("testRoom");
		stub.wait(50);
		stub.socketClient.fetchServerState();
		stub.wait(50);
		stub.wait(50);
		int roomID = stub.socketClient.getServerState().getRooms().get(0).getRoomID();
		stub.socketClient.joinGame(roomID, "randomName");
		stub.socketClient2.joinGame(roomID, "extraName");

		ObserverStub<INetworkClient> obs = new ObserverStub<INetworkClient>();
		obs.currentVal = null;
		stub.socketClient2.getSyncronizationFailedCallback().addObserver(obs);

		stub.wait(500);
		for (int a = 0; a < NetworkSettings.CHECK_SYNC_RATEO + 1; a++) {
			stub.socketClient.sendSyncCommand("SyncMeUp");
			stub.wait(100);
			stub.socketClient2.getSynchronizedObject().clear();
			Assert.assertNotEquals(
					stub.socketClient.getSynchronizedObject().getHash(),
					stub.socketClient2.getSynchronizedObject().getHash());
		}

		Assert.assertNotNull(obs.currentVal);

	}

	@Test
	public void testJoinWithSameName()
	{
		stub.socketClient.createGame("testRoom");
		stub.wait(50);
		stub.socketClient.fetchServerState();
		stub.wait(50);
		stub.wait(50);
		int roomID = stub.socketClient.getServerState().getRooms().get(0).getRoomID();
		stub.socketClient.joinGame(roomID, "randomName");
		stub.socketClient2.joinGame(roomID, "randomName");
		stub.wait(100);
		Assert.assertEquals(roomID, stub.socketClient2.getRoom().getRoomID());
		Assert.assertEquals(roomID, stub.socketClient.getRoom().getRoomID());
		Assert.assertEquals(2, stub.socketClient2.getRoom().getPlayers().size());
		Assert.assertEquals(2, stub.socketClient.getRoom().getPlayers().size());


	}

	@Test
	public void testTTL()
	{
		ObserverStub<AbstractSocketManager> obs = new ObserverStub<AbstractSocketManager>();
		ObserverStub<INetworkClient> obs2 = new ObserverStub<INetworkClient>();
		obs.currentVal = null;

		stub.socketClient.getConnectionLostCallback().addObserver(obs2);
		stub.socketClient.getConnectionClosedCallback().addObserver(obs);
		stub.socketClient.disconnect(false);
		stub.wait(NetworkSettings.DEFAULT_TIME_TO_LIVE * (NetworkSettings.MAX_TIME_TO_LIVE_SKIPPED + 2));
		Assert.assertEquals(false, stub.socketClient.isRunning());
		Assert.assertEquals(true, stub.socketClient2.isRunning());
		Assert.assertEquals(false,obs.currentVal.isRunning());
		Assert.assertNotNull(obs.currentVal);
	}
}
