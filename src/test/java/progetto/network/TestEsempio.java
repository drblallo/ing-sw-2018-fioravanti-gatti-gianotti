package progetto.network;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEsempio {

	@Test
	public void testSample() {
		NetworkSettings sett = new NetworkSettings();
		assertEquals(42, sett.getEasterEgg());
	}
}
