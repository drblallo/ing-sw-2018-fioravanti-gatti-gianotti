package progetto.game;

import junit.framework.TestCase;

public class TestExtractedDices extends TestCase {

	public void test1() {
		Dice dice1 = new Dice(Value.FOUR, Color.GREEN);

		ExtractedDices dices = new ExtractedDices();

		dices.addDice(dice1);

		assertEquals(Color.GREEN, dices.getExtractedDicesData().getDice(0).getColor());
		assertEquals(Value.FOUR, dices.getExtractedDicesData().getDice(0).getValue());

		assertEquals(dices.getExtractedDicesData().getNumberOfDices(), 1);

		dices.addDice(new Dice(Value.THREE, Color.PURPLE));

		assertEquals(Color.PURPLE, dices.getExtractedDicesData().getDice(1).getColor());
		assertEquals(Value.THREE, dices.getExtractedDicesData().getDice(1).getValue());

		dices.changeDice(2, new Dice(Value.FIVE, Color.BLUE));

		assertEquals(2, dices.getExtractedDicesData().getNumberOfDices());

		dices.addDice(new Dice(Value.THREE, Color.PURPLE));

		assertEquals(Color.PURPLE, dices.getExtractedDicesData().getDice(2).getColor());
		assertEquals(Value.THREE, dices.getExtractedDicesData().getDice(2).getValue());

		assertEquals(dices.getExtractedDicesData().getNumberOfDices(), 3);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getExtractedDicesData().getNumberOfDices(), 4);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getExtractedDicesData().getNumberOfDices(), 5);
		dices.addDice(new Dice(Value.TWO, Color.YELLOW));
		assertEquals(dices.getExtractedDicesData().getNumberOfDices(), 6);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getExtractedDicesData().getNumberOfDices(), 7);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getExtractedDicesData().getNumberOfDices(), 8);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getExtractedDicesData().getNumberOfDices(), 9);
		dices.addDice(new Dice(Value.THREE, Color.PURPLE));
		assertEquals(dices.getExtractedDicesData().getNumberOfDices(), 10);

		assertEquals(Color.YELLOW, dices.getExtractedDicesData().getDice(5).getColor());
		assertEquals(Value.TWO, dices.getExtractedDicesData().getDice(5).getValue());

		dices.changeDice(5, new Dice(Value.ONE, Color.RED));

		assertEquals(Color.RED, dices.getExtractedDicesData().getDice(5).getColor());
		assertEquals(Value.ONE, dices.getExtractedDicesData().getDice(5).getValue());

		assertEquals(Value.ONE, dices.getExtractedDicesData().getDice(5).getValue());
		assertEquals(Color.RED, dices.getExtractedDicesData().getDice(5).getColor());

		assertEquals(10, dices.getExtractedDicesData().getNumberOfDices());

		dices.removeDice(5);

		assertEquals(9, dices.getExtractedDicesData().getNumberOfDices());

		Dice dice = dices.removeDice(15);

		assertEquals(null, dice);
		assertEquals(9, dices.getExtractedDicesData().getNumberOfDices());

	}
}
