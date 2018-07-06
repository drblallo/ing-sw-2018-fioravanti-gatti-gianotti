package progetto.network.socket;

import progetto.network.AbstractMessageExchangeTest;

public class SocketMessageExcangeTest extends AbstractMessageExchangeTest {

	public SocketMessageExcangeTest() {
		super(new SocketServerFactory(), new ClientSocketFactory());
	}
}
