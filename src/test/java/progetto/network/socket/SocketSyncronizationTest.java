package progetto.network.socket;

import progetto.network.SyncronizationTest;

public class SocketSyncronizationTest extends SyncronizationTest {
	public SocketSyncronizationTest() {
		super(new SocketServerFactory(), new ClientSocketFactory());
	}
}
