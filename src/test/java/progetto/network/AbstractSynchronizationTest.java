package progetto.network;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.utils.ObserverStub;

public abstract class AbstractSynchronizationTest extends AbstractNetworkTestStub {

	public AbstractSynchronizationTest(INetworkModuleFactory m, INetworkClientFactory f) {
		super(m, f);
	}

	@Before
	public void setUp() {
		startServer();
		wait(SHORT_WAIT);
		startClient(0);
		startClient(1);
		wait(SHORT_WAIT);
	}

	@After
	public void shutDown() {
		wait(SHORT_WAIT);
		tearDown();
		wait(SHORT_WAIT);
	}

	@Test
	public void testSync() {

		ClientConnection cl1 = getClientConnection(0);
		ClientConnection cl2 = getClientConnection(1);
		cl1.createGame("testRoom");
		wait(SHORT_WAIT);
		cl1.fetchServerState();
		wait(SHORT_WAIT);
		wait(SHORT_WAIT);
		int roomID = cl1.getServerState().getRoomFromName("testRoom").roomID;
		cl1.sendSynString("SyncMeUp");
		cl1.joinGame(roomID, "randomName");
		cl2.joinGame(roomID, "extraName");


		wait(MEDIUM_WAIT);
		for (int a = 0; a < NetworkSettings.CHECK_SYNC_RATEO + 1; a++) {
			cl1.sendSynString("SyncMeUp");
			cl1.sendSynString("SyncMeUpNot");
			wait(MEDIUM_WAIT);
			Assert.assertEquals(
					cl1.getSynchronizedObject().getHash(a + 1),
					cl2.getSynchronizedObject().getHash(a + 1));
		}

		cl1.joinGame(-1, "randomName");
		wait(SHORT_WAIT);
		cl1.joinGame(roomID, "randomName");

		wait(LONG_WAIT);
		Assert.assertEquals(cl1.getSynchronizedObject().getHash(),
				cl2.getSynchronizedObject().getHash());

	}


	@Test
	public void testFailSync() {
		ClientConnection clientConnection = getClientConnection(0);
		ClientConnection clientConnection2 = getClientConnection(1);
		clientConnection.createGame("testRoom");
		wait(SHORT_WAIT);
		clientConnection.fetchServerState();
		wait(MEDIUM_WAIT);
		int roomID = clientConnection.getServerState().getRoomFromName("testRoom").roomID;
		clientConnection.joinGame(roomID, "randomName");
		clientConnection2.joinGame(roomID, "extraName");

		ObserverStub<ClientConnection> obs = new ObserverStub<ClientConnection>();
		obs.currentVal = null;
		clientConnection2.getSyncronizationFailedCallback().addObserver(obs);

		wait(LONG_WAIT);
		for (int a = 0; a < NetworkSettings.CHECK_SYNC_RATEO + 1; a++) {
			clientConnection.sendSynString("SyncMeUp");
			wait(MEDIUM_WAIT);
			clientConnection2.getSynchronizedObject().clear();
			Assert.assertNotEquals(
					clientConnection.getSynchronizedObject().getHash(),
					clientConnection2.getSynchronizedObject().getHash());
		}

		Assert.assertNotNull(obs.currentVal);

	}


}
