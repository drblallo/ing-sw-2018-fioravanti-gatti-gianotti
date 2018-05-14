package progetto.game;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class TestDice extends TestCase {

	private Dice dice;

	@Test
	public void testConstructor()
	{
		dice = new Dice(Value.ONE, Color.YELLOW);

		Assert.assertEquals(Value.ONE, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());
	}

	@Test
	public void testSetter()
	{
		dice = new Dice(Value.ONE, Color.YELLOW);

		dice = dice.setValue(Value.TWO);
		Assert.assertEquals(Value.TWO, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.setColor(Color.BLUE);
		Assert.assertEquals(Color.BLUE, dice.getColor());
		Assert.assertEquals(Value.TWO, dice.getValue());
	}

	@Test
	public void testIncreaseValue()
	{
		dice = new Dice(Value.ONE, Color.YELLOW);

		dice = dice.increaseValue();
		Assert.assertEquals(Value.TWO, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.increaseValue();
		Assert.assertEquals(Value.THREE, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.increaseValue();
		Assert.assertEquals(Value.FOUR, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.increaseValue();
		Assert.assertEquals(Value.FIVE, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.increaseValue();
		Assert.assertEquals(Value.SIX, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.increaseValue();
		Assert.assertEquals(Value.SIX, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());
	}

	@Test
	public void testDecreaseValue()
	{
		dice = new Dice(Value.SIX, Color.YELLOW);

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.FIVE, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.FOUR, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.THREE, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.TWO, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.ONE, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.ONE, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());
	}

	@Test
	public void testFlip()
	{
		dice = new Dice(Value.ONE, Color.YELLOW);

		dice = dice.flip();
		Assert.assertEquals(Value.SIX, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());
		dice = dice.flip();
		Assert.assertEquals(Value.ONE, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.setValue(Value.TWO);
		dice = dice.flip();
		Assert.assertEquals(Value.FIVE, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());
		dice = dice.flip();
		Assert.assertEquals(Value.TWO, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

		dice = dice.setValue(Value.THREE);
		dice = dice.flip();
		Assert.assertEquals(Value.FOUR, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());
		dice = dice.flip();
		Assert.assertEquals(Value.THREE, dice.getValue());
		Assert.assertEquals(Color.YELLOW, dice.getColor());

	}

}
