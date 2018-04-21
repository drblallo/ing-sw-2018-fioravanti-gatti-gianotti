package progetto.game;

import junit.framework.TestCase;

public class TestDice extends TestCase {

	public void test1()
	{
		Dice dice = new Dice(Value.FOUR, Color.GREEN);
		assertEquals(dice.getColor(), Color.GREEN);
		assertEquals(dice.getValue(), Value.FOUR);
		dice.setValue(Value.ONE);
		assertEquals(dice.getValue(), Value.ONE);
		dice.setColor(Color.BLUE);
		assertEquals(dice.getColor(), Color.BLUE);
	}
}
