package progetto.network.rmi;

import progetto.network.AbstractMessageExchangeTest;

public class RMIMessageExcangeTest extends AbstractMessageExchangeTest {


	public RMIMessageExcangeTest() {
		super(new RMIServerFactory(), new RMIClientFactory());
		timeModifier = 1;
	}
}
