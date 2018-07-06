package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class nine dices
 */
public class TestNineDices extends TestCase {

	NineDices nineDices;

	@Before
	public void setUp()
	{
		nineDices = new NineDices();
	}

	/**
	 * Test constructor
	 */
	@Test
	public void testConstructor()
	{
		for(int i=0; i<50; i++)
		{
			Assert.assertNull(nineDices.getDice(i));
		}
	}

	/**
	 * test copy constructor
	 */
	@Test
	public void testCopyConstructor()
	{
		nineDices = nineDices.addDice(new Dice(Value.FOUR, GameColor.GREEN));

		NineDices nineDices1 = new NineDices(nineDices);

		for(int i=0; i<20; i++)
		{
			Assert.assertEquals(nineDices.getDice(i), nineDices1.getDice(i));
		}
	}

	/**
	 * Test get dice from a position of the roundTrack
	 */
	@Test
	public void testGetDice()
	{
		Dice dice1 = new Dice(Value.FOUR, GameColor.GREEN);

		nineDices = nineDices.addDice(dice1);

		Assert.assertEquals(dice1, nineDices.getDice(0));

		nineDices = nineDices.addDice(new Dice(Value.THREE, GameColor.PURPLE));

		Assert.assertEquals(GameColor.PURPLE, nineDices.getDice(1).getGameColor());
		Assert.assertEquals(Value.THREE, nineDices.getDice(1).getValue());

	}

	/**
	 * Test get dice - fail - wrong index - dice not added
	 */
	@Test
	public void testGetDiceFail()
	{
		Assert.assertNull(nineDices.getDice(0));
		Assert.assertNull(nineDices.getDice(3));
		Assert.assertNull(nineDices.getDice(-1));

	}

	/**
	 * Test add dice
	 */
	@Test
	public void testAddDice()
	{
		Dice dice1 = new Dice(Value.ONE, GameColor.YELLOW);
		nineDices = nineDices.addDice(dice1);
		Assert.assertEquals(dice1, nineDices.getDice(0));

		Dice dice2 = new Dice(Value.THREE, GameColor.BLUE);
		nineDices = nineDices.addDice(dice2);
		Assert.assertEquals(dice2, nineDices.getDice(1));

		Dice dice3 = new Dice(Value.FIVE, GameColor.RED);
		nineDices = nineDices.addDice(dice3);
		Assert.assertEquals(dice3, nineDices.getDice(2));

		Dice dice4 = new Dice(Value.TWO, GameColor.GREEN);
		nineDices = nineDices.addDice(dice4);
		Assert.assertEquals(dice4, nineDices.getDice(3));

		Dice dice5 = new Dice(Value.SIX, GameColor.PURPLE);
		nineDices = nineDices.addDice(dice5);
		Assert.assertEquals(dice5, nineDices.getDice(4));

	}

	/**
	 * Test isFree method
	 */
	public void testIsFree()
	{
		Dice dice1 = new Dice(Value.FOUR, GameColor.GREEN);

		Assert.assertTrue(nineDices.isFree(0));

		nineDices = nineDices.addDice(dice1);
		Assert.assertFalse(nineDices.isFree(0));

		nineDices = nineDices.addDice(new Dice(Value.THREE, GameColor.PURPLE));

		Assert.assertFalse(nineDices.isFree(1));

	}

	/**
	 * Test get number of dices in a position of the round track
	 */
	@Test
	public void testGetNumberOfDices()
	{
		Assert.assertEquals(0, nineDices.getNumberOfDices());

		nineDices = nineDices.addDice(new Dice(Value.ONE, GameColor.YELLOW));
		Assert.assertEquals(1, nineDices.getNumberOfDices());

		nineDices = nineDices.addDice(new Dice(Value.ONE, GameColor.YELLOW));
		Assert.assertEquals(2, nineDices.getNumberOfDices());

		nineDices = nineDices.addDice(new Dice(Value.ONE, GameColor.YELLOW));
		Assert.assertEquals(3, nineDices.getNumberOfDices());

		nineDices = nineDices.addDice(new Dice(Value.ONE, GameColor.YELLOW));
		Assert.assertEquals(4, nineDices.getNumberOfDices());

		nineDices = nineDices.addDice(new Dice(Value.ONE, GameColor.YELLOW));
		Assert.assertEquals(5, nineDices.getNumberOfDices());

		nineDices = nineDices.addDice(new Dice(Value.ONE, GameColor.YELLOW));
		Assert.assertEquals(6, nineDices.getNumberOfDices());

	}

	/**
	 * Test change dice
	 */
	@Test
	public void testChangeDice()
	{
		Dice dice1 = new Dice(Value.ONE, GameColor.YELLOW);
		Dice dice2 = new Dice(Value.TWO, GameColor.GREEN);
		Dice dice3 = new Dice(Value.THREE, GameColor.PURPLE);
		Dice dice4 = new Dice(Value.FOUR, GameColor.BLUE);

		nineDices = nineDices.addDice(dice1);
		nineDices = nineDices.addDice(dice2);

		nineDices = nineDices.changeDice(1, dice3);
		Assert.assertEquals(dice1, nineDices.getDice(0));
		Assert.assertEquals(dice3, nineDices.getDice(1));

		nineDices = nineDices.changeDice(0, dice4);
		Assert.assertEquals(dice4, nineDices.getDice(0));
		Assert.assertEquals(dice3, nineDices.getDice(1));

	}

	/**
	 * Test change dice - fail - wrong index - dice not added
	 */
	@Test
	public void testChangeDiceFail()
	{
		NineDices nineDices1;

		nineDices1 = nineDices.changeDice(0, new Dice(Value.ONE, GameColor.BLUE));

		Assert.assertEquals(0, nineDices1.getNumberOfDices());
		Assert.assertEquals(nineDices, nineDices1);

		nineDices = nineDices.addDice(new Dice (Value.THREE, GameColor.GREEN));

		nineDices1 = nineDices.changeDice(1, new Dice(Value.TWO, GameColor.PURPLE));
		Assert.assertEquals(1, nineDices1.getNumberOfDices());
		Assert.assertEquals(nineDices, nineDices1);


	}

}