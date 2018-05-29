package progetto.game;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRNGenerator extends TestCase {

	RNGenerator rnGenerator;

	@Before
	public void setUp()
	{
		rnGenerator = new RNGenerator(0);
	}

	@Test
	public void testConstructor()
	{
		Assert.assertEquals(0, rnGenerator.getSeed());
	}

	@Test
	public void testRandomWithSeed()
	{
		RNGenerator rnGenerator1 = new RNGenerator(1234123412);
		RNGenerator rnGenerator2 = new RNGenerator(1234123412);

		Assert.assertEquals(1234123412, rnGenerator1.getSeed());
		Assert.assertEquals(1234123412, rnGenerator2.getSeed());

		for(int i=0; i<500; i++)
		{
			assertEquals(rnGenerator1.getNextInt(6), rnGenerator2.getNextInt(6));
		}

		rnGenerator1.setSeed(1212121212);
		rnGenerator2.setSeed(1212121212);

		Assert.assertEquals(1212121212, rnGenerator1.getSeed());
		Assert.assertEquals(1212121212, rnGenerator2.getSeed());

		for(int i=0; i<500; i++)
		{
			assertEquals(rnGenerator1.getNextInt(6), rnGenerator2.getNextInt(6));
		}

	}

	@Test
	public void testExtract()
	{
		RNGenerator rng1 = new RNGenerator(1010101010);
		RNGenerator rng2 = new RNGenerator(1010101010);
		DiceBag db1 = new DiceBag();
		DiceBag db2 = new DiceBag();
		Dice dice1;
		Dice dice2;

		for(int i=0; i<90; i++)
		{
			dice1 = rng1.extractDice(db1);
			dice2 = rng2.extractDice(db2);
			//System.out.println("Extracted dice: \n\tColor: " + dice1.getColor() + "\n\tValue: " + dice1.getValue());
			Assert.assertEquals(dice1.getColor(), dice2.getColor());
			Assert.assertEquals(dice1.getValue(), dice2.getValue());
		}
	}

	@Test
	public void testPlatformIndependent()
	{
		RNGenerator rng1 = new RNGenerator(432143214);
		DiceBag db = new DiceBag();
		Dice dice;

		dice = rng1.extractDice(db);
		Assert.assertEquals(Color.BLUE, dice.getColor());
		Assert.assertEquals(Value.TWO, dice.getValue());

		dice = rng1.extractDice(db);
		Assert.assertEquals(Color.BLUE, dice.getColor());
		Assert.assertEquals(Value.THREE, dice.getValue());

		dice = rng1.extractDice(db);
		Assert.assertEquals(Color.YELLOW, dice.getColor());
		Assert.assertEquals(Value.TWO, dice.getValue());

		dice = rng1.extractDice(db);
		Assert.assertEquals(Color.GREEN, dice.getColor());
		Assert.assertEquals(Value.FIVE, dice.getValue());

		dice = rng1.extractDice(db);
		Assert.assertEquals(Color.BLUE, dice.getColor());
		Assert.assertEquals(Value.TWO, dice.getValue());

		dice = rng1.extractDice(db);
		Assert.assertEquals(Color.GREEN, dice.getColor());
		Assert.assertEquals(Value.FIVE, dice.getValue());

		dice = rng1.extractDice(db);
		Assert.assertEquals(Color.YELLOW, dice.getColor());
		Assert.assertEquals(Value.FIVE, dice.getValue());

		dice = rng1.extractDice(db);
		Assert.assertEquals(Color.GREEN, dice.getColor());
		Assert.assertEquals(Value.FIVE, dice.getValue());

		dice = rng1.extractDice(db);
		Assert.assertEquals(Color.GREEN, dice.getColor());
		Assert.assertEquals(Value.SIX, dice.getValue());

		dice = rng1.extractDice(db);
		Assert.assertEquals(Color.RED, dice.getColor());
		Assert.assertEquals(Value.THREE, dice.getValue());

	}

	@Test
	public void testRollAgain()
	{
		RNGenerator rng = new RNGenerator(0);
		Dice dice = new Dice(Value.FOUR, Color.RED);


		dice=rng.rollAgain(dice);
		Assert.assertEquals(Value.ONE, dice.getValue());

		dice=rng.rollAgain(dice);
		Assert.assertEquals(Value.FIVE, dice.getValue());

		dice=rng.rollAgain(dice);
		Assert.assertEquals(Value.TWO, dice.getValue());

		dice=rng.rollAgain(dice);
		Assert.assertEquals(Value.SIX, dice.getValue());

		dice=rng.rollAgain(dice);
		Assert.assertEquals(Value.SIX, dice.getValue());

		dice=rng.rollAgain(dice);
		Assert.assertEquals(Value.SIX, dice.getValue());

		dice=rng.rollAgain(dice);
		Assert.assertEquals(Value.SIX, dice.getValue());

		dice=rng.rollAgain(dice);
		Assert.assertEquals(Value.FOUR, dice.getValue());

		dice=rng.rollAgain(dice);
		Assert.assertEquals(Value.FOUR, dice.getValue());

		dice=rng.rollAgain(dice);
		Assert.assertEquals(Value.THREE, dice.getValue());

	}
}
