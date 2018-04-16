package progetto.network.socket;

import progetto.network.TestNetwork;

public class SocketTestNetwork extends TestNetwork{

	public SocketTestNetwork()
	{
		super(new SocketServerFactory(), new ClientSocketFactory());
	}
}
