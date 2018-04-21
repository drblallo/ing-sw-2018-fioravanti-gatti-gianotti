package progetto.game;

import junit.framework.TestCase;

public class TestNineDices extends TestCase {

	public void test1()
	{
		Dice dice1 = new Dice(Value.FOUR, Color.GREEN);

		NineDices dices = new NineDices();

		dices.addDice(dice1);

		assertEquals(Color.GREEN, dices.getDice(0).getColor());
		assertEquals(Value.FOUR, dices.getDice(0).getValue());

		assertEquals(dices.getNumberOfDices(), 1);

		dices.addDice(new Dice(Value.THREE, Color.PURPLE));

		assertEquals(Color.PURPLE, dices.getDice(1).getColor());
		assertEquals(Value.THREE, dices.getDice(1).getValue());

		dices.changeDice(2, new Dice(Value.FIVE, Color.BLUE));

		assertEquals(dices.getNumberOfDices(), 2);

		dices.addDice(new Dice(Value.THREE, Color.PURPLE));

		assertEquals(Color.PURPLE, dices.getDice(2).getColor());
		assertEquals(Value.THREE, dices.getDice(2).getValue());

		assertEquals(dices.getNumberOfDices(), 3);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getNumberOfDices(), 4);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getNumberOfDices(), 5);
		dices.addDice(new Dice(Value.TWO, Color.YELLOW));
		assertEquals(dices.getNumberOfDices(), 6);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getNumberOfDices(), 7);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getNumberOfDices(), 8);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getNumberOfDices(), 9);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getNumberOfDices(), 9);

		assertEquals(Color.YELLOW, dices.getDice(5).getColor());
		assertEquals(Value.TWO, dices.getDice(5).getValue());

		dices.changeDice(5, new Dice(Value.ONE, Color.RED));

		assertEquals(Color.RED, dices.getDice(5).getColor());
		assertEquals(Value.ONE, dices.getDice(5).getValue());

		assertEquals(Value.ONE, dices.getValue(5));
		assertEquals(Color.RED, dices.getColor(5));

	}
}