package progetto.network.socket;

import progetto.network.NetworkTest;

public class SocketNetworkTest extends NetworkTest {

	public SocketNetworkTest() {
		super(new SocketServerFactory(), new ClientSocketFactory());
	}
}
