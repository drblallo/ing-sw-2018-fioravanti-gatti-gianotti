package progetto.game;

import junit.framework.TestCase;

public class TestRNGenerator extends TestCase {

	public void test1()
	{
		RNGenerator rnGenerator1 = new RNGenerator(1234123412);
		RNGenerator rnGenerator2 = new RNGenerator(1234123412);

		assertEquals(1234123412, rnGenerator1.getSeed());
		assertEquals(1234123412, rnGenerator2.getSeed());

		for(int i=0; i<500; i++)
		{
			assertEquals(rnGenerator1.getNextInt(6), rnGenerator2.getNextInt(6));
		}

		rnGenerator1.setSeed(1212121212);
		rnGenerator2.setSeed(1212121212);

		assertEquals(1212121212, rnGenerator1.getSeed());
		assertEquals(1212121212, rnGenerator2.getSeed());

		for(int i=0; i<500; i++)
		{
			assertEquals(rnGenerator1.getNextInt(6), rnGenerator2.getNextInt(6));
		}

	}

	public void test2()
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
			//System.out.println("Dado estratto: \n\tColore: " + dice1.getColor() + "\n\tValore: " + dice1.getValue());
			assertEquals(dice1.getColor(), dice2.getColor());
			assertEquals(dice1.getValue(), dice2.getValue());
		}
	}

	public void test3PlatformIndependent()
	{
		RNGenerator rng1 = new RNGenerator(432143214);
		DiceBag db = new DiceBag();
		Dice dice;

		dice = rng1.extractDice(db);
		assertEquals(Color.BLUE, dice.getColor());
		assertEquals(Value.TWO, dice.getValue());

		dice = rng1.extractDice(db);
		assertEquals(Color.BLUE, dice.getColor());
		assertEquals(Value.THREE, dice.getValue());

		dice = rng1.extractDice(db);
		assertEquals(Color.YELLOW, dice.getColor());
		assertEquals(Value.TWO, dice.getValue());

		dice = rng1.extractDice(db);
		assertEquals(Color.GREEN, dice.getColor());
		assertEquals(Value.FIVE, dice.getValue());

		dice = rng1.extractDice(db);
		assertEquals(Color.BLUE, dice.getColor());
		assertEquals(Value.TWO, dice.getValue());

		dice = rng1.extractDice(db);
		assertEquals(Color.GREEN, dice.getColor());
		assertEquals(Value.FIVE, dice.getValue());

		dice = rng1.extractDice(db);
		assertEquals(Color.YELLOW, dice.getColor());
		assertEquals(Value.FIVE, dice.getValue());

		dice = rng1.extractDice(db);
		assertEquals(Color.GREEN, dice.getColor());
		assertEquals(Value.FIVE, dice.getValue());

		dice = rng1.extractDice(db);
		assertEquals(Color.GREEN, dice.getColor());
		assertEquals(Value.SIX, dice.getValue());

		dice = rng1.extractDice(db);
		assertEquals(Color.RED, dice.getColor());
		assertEquals(Value.THREE, dice.getValue());

	}

	public void test4RollAgain()
	{
		RNGenerator rng = new RNGenerator(0);
		Dice dice = new Dice(Value.FOUR, Color.RED);


		dice=rng.rollAgain(dice);
		assertEquals(Value.ONE, dice.getValue());

		dice=rng.rollAgain(dice);
		assertEquals(Value.FIVE, dice.getValue());

		dice=rng.rollAgain(dice);
		assertEquals(Value.TWO, dice.getValue());

		dice=rng.rollAgain(dice);
		assertEquals(Value.SIX, dice.getValue());

		dice=rng.rollAgain(dice);
		assertEquals(Value.SIX, dice.getValue());

		dice=rng.rollAgain(dice);
		assertEquals(Value.SIX, dice.getValue());

		dice=rng.rollAgain(dice);
		assertEquals(Value.SIX, dice.getValue());

		dice=rng.rollAgain(dice);
		assertEquals(Value.FOUR, dice.getValue());

		dice=rng.rollAgain(dice);
		assertEquals(Value.FOUR, dice.getValue());

		dice=rng.rollAgain(dice);
		assertEquals(Value.THREE, dice.getValue());

	}
}
