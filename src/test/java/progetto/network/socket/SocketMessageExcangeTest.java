package progetto.network.socket;

import progetto.network.MessageExcangeTest;

public class SocketMessageExcangeTest extends MessageExcangeTest {

	public SocketMessageExcangeTest()
	{
		super(new SocketServerFactory(), new ClientSocketFactory());
	}
}
