package progetto.game;

import junit.framework.TestCase;

public class TestDice extends TestCase {

	public void test1()
	{
		Dice dice = new Dice(Value.FOUR, Color.GREEN);

		dice.decreaseValue();

		assertEquals(dice.getColor(), Color.GREEN);
		assertEquals(dice.getValue(), Value.THREE);

		dice.increaseValue();

		assertEquals(dice.getColor(), Color.GREEN);
		assertEquals(dice.getValue(), Value.FOUR);

		assertEquals(dice.getColor(), Color.GREEN);
		assertEquals(dice.getValue(), Value.FOUR);
		dice.setValue(Value.ONE);
		assertEquals(dice.getValue(), Value.ONE);

		dice.decreaseValue();
		assertEquals(dice.getValue(), Value.ONE);

		dice.increaseValue();
		assertEquals(dice.getValue(), Value.TWO);

		dice.increaseValue();
		assertEquals(dice.getValue(), Value.THREE);

		dice.increaseValue();
		assertEquals(dice.getValue(), Value.FOUR);

		dice.increaseValue();
		assertEquals(dice.getValue(), Value.FIVE);

		dice.increaseValue();
		assertEquals(dice.getValue(), Value.SIX);

		dice.increaseValue();
		assertEquals(dice.getValue(), Value.SIX);

		dice.decreaseValue();
		assertEquals(dice.getValue(), Value.FIVE);

		dice.decreaseValue();
		assertEquals(dice.getValue(), Value.FOUR);

		dice.decreaseValue();
		assertEquals(dice.getValue(), Value.THREE);

		dice.decreaseValue();
		assertEquals(dice.getValue(), Value.TWO);

		dice.decreaseValue();
		assertEquals(dice.getValue(), Value.ONE);

		dice.decreaseValue();
		assertEquals(dice.getValue(), Value.ONE);

		dice.setColor(Color.BLUE);
		assertEquals(dice.getColor(), Color.BLUE);

	}

	public void test2FlipDice()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);
		assertEquals(Value.ONE, dice.getValue());

		dice.flip();
		assertEquals(Value.SIX, dice.getValue());

		dice.flip();
		assertEquals(Value.ONE, dice.getValue());


		dice.setValue(Value.TWO);
		assertEquals(Value.TWO, dice.getValue());

		dice.flip();
		assertEquals(Value.FIVE, dice.getValue());

		dice.flip();
		assertEquals(Value.TWO, dice.getValue());


		dice.setValue(Value.THREE);
		assertEquals(Value.THREE, dice.getValue());

		dice.flip();
		assertEquals(Value.FOUR, dice.getValue());

		dice.flip();
		assertEquals(Value.THREE, dice.getValue());

	}
}
