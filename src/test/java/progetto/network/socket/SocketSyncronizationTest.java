package progetto.network.socket;

import progetto.network.AbstractSynchronizationTest;

public class SocketSyncronizationTest extends AbstractSynchronizationTest {
	public SocketSyncronizationTest() {
		super(new SocketServerFactory(), new ClientSocketFactory());
	}
}
