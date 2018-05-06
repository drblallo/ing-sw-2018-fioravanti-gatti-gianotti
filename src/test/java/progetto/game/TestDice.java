package progetto.game;

import junit.framework.TestCase;

public class TestDice extends TestCase {

	public void test1()
	{
		Dice dice = new Dice(Value.FOUR, Color.GREEN);

		dice = dice.decreaseValue();

		assertEquals(dice.getColor(), Color.GREEN);
		assertEquals(dice.getValue(), Value.THREE);

		dice = dice.increaseValue();

		assertEquals(dice.getColor(), Color.GREEN);
		assertEquals(dice.getValue(), Value.FOUR);

		assertEquals(dice.getColor(), Color.GREEN);
		assertEquals(dice.getValue(), Value.FOUR);
		dice = dice.setValue(Value.ONE);
		assertEquals(dice.getValue(), Value.ONE);

		dice = dice.decreaseValue();
		assertEquals(dice.getValue(), Value.ONE);

		dice = dice.increaseValue();
		assertEquals(dice.getValue(), Value.TWO);

		dice = dice.increaseValue();
		assertEquals(dice.getValue(), Value.THREE);

		dice = dice.increaseValue();
		assertEquals(dice.getValue(), Value.FOUR);

		dice = dice.increaseValue();
		assertEquals(dice.getValue(), Value.FIVE);

		dice = dice.increaseValue();
		assertEquals(dice.getValue(), Value.SIX);

		dice = dice.increaseValue();
		assertEquals(dice.getValue(), Value.SIX);

		dice = dice.decreaseValue();
		assertEquals(dice.getValue(), Value.FIVE);

		dice = dice.decreaseValue();
		assertEquals(dice.getValue(), Value.FOUR);

		dice = dice.decreaseValue();
		assertEquals(dice.getValue(), Value.THREE);

		dice = dice.decreaseValue();
		assertEquals(dice.getValue(), Value.TWO);

		dice = dice.decreaseValue();
		assertEquals(dice.getValue(), Value.ONE);

		dice = dice.decreaseValue();
		assertEquals(dice.getValue(), Value.ONE);

		dice = dice.setColor(Color.BLUE);
		assertEquals(dice.getColor(), Color.BLUE);

	}

	public void test2FlipDice()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);
		assertEquals(Value.ONE, dice.getValue());

		dice = dice.flip();
		assertEquals(Value.SIX, dice.getValue());

		dice = dice.flip();
		assertEquals(Value.ONE, dice.getValue());


		dice = dice.setValue(Value.TWO);
		assertEquals(Value.TWO, dice.getValue());

		dice = dice.flip();
		assertEquals(Value.FIVE, dice.getValue());

		dice = dice.flip();
		assertEquals(Value.TWO, dice.getValue());


		dice = dice.setValue(Value.THREE);
		assertEquals(Value.THREE, dice.getValue());

		dice = dice.flip();
		assertEquals(Value.FOUR, dice.getValue());

		dice = dice.flip();
		assertEquals(Value.THREE, dice.getValue());

	}
}
