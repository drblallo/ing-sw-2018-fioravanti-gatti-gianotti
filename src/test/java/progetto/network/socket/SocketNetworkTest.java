package progetto.network.socket;

import progetto.network.AbstractStandardTest;

public class SocketNetworkTest extends AbstractStandardTest {

	public SocketNetworkTest() {
		super(new SocketServerFactory(), new ClientSocketFactory());
	}
}
