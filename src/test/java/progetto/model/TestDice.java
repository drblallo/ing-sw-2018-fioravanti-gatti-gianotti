package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class Dice
 */
public class TestDice extends TestCase {

	private Dice dice;

	/**
	 * Test constructor
	 */
	@Test
	public void testConstructor()
	{
		dice = new Dice(Value.ONE, GameColor.YELLOW);

		Assert.assertEquals(Value.ONE, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
	}

	/**
	 * Test setter
	 */
	@Test
	public void testSetter()
	{
		dice = new Dice(Value.ONE, GameColor.YELLOW);

		dice = dice.setValue(Value.TWO);
		Assert.assertEquals(Value.TWO, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.setGameColor(GameColor.BLUE);
		Assert.assertEquals(GameColor.BLUE, dice.getGameColor());
		Assert.assertEquals(Value.TWO, dice.getValue());
	}

	/**
	 * Test increase value method
	 */
	@Test
	public void testIncreaseValue()
	{
		dice = new Dice(Value.ONE, GameColor.YELLOW);

		dice = dice.increaseValue();
		Assert.assertEquals(Value.TWO, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.increaseValue();
		Assert.assertEquals(Value.THREE, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.increaseValue();
		Assert.assertEquals(Value.FOUR, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.increaseValue();
		Assert.assertEquals(Value.FIVE, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.increaseValue();
		Assert.assertEquals(Value.SIX, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.increaseValue();
		Assert.assertEquals(Value.SIX, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
	}

	/**
	 * Test decrease value method
	 */
	@Test
	public void testDecreaseValue()
	{
		dice = new Dice(Value.SIX, GameColor.YELLOW);

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.FIVE, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.FOUR, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.THREE, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.TWO, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.ONE, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.decreaseValue();
		Assert.assertEquals(Value.ONE, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
	}

	/**
	 * Test flid dice method
	 */
	@Test
	public void testFlip()
	{
		dice = new Dice(Value.ONE, GameColor.YELLOW);

		dice = dice.flip();
		Assert.assertEquals(Value.SIX, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		dice = dice.flip();
		Assert.assertEquals(Value.ONE, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.setValue(Value.TWO);
		dice = dice.flip();
		Assert.assertEquals(Value.FIVE, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		dice = dice.flip();
		Assert.assertEquals(Value.TWO, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

		dice = dice.setValue(Value.THREE);
		dice = dice.flip();
		Assert.assertEquals(Value.FOUR, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		dice = dice.flip();
		Assert.assertEquals(Value.THREE, dice.getValue());
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());

	}

	/**
	 * Test to string method
	 */
	@Test
	public void testToString()
	{
		Dice dice = new Dice(Value.TWO, GameColor.RED);
		Assert.assertEquals(dice.getValue()+" "+dice.getGameColor(), dice.toString());

	}

}
