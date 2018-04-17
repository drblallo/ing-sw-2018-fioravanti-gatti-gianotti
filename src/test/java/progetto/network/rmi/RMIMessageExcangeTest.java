package progetto.network.rmi;

import progetto.network.MessageExcangeTest;

public class RMIMessageExcangeTest extends MessageExcangeTest {


	public RMIMessageExcangeTest() {
		super(new RMIServerFactory(), new RMIClientFactory());
		timeModifier = 1;
	}
}
