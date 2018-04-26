package progetto.game;

import junit.framework.TestCase;

public class TestCouple extends TestCase{
	public void test1()
	{
		Couple couple = new Couple(1, -1);

		assertEquals(1, couple.getDx());
		assertEquals(-1, couple.getDy());
	}
}
