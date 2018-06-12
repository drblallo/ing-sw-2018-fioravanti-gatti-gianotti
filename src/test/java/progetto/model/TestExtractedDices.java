package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestExtractedDices extends TestCase {

	ExtractedDices dices;

	@Before
	public void setUp()
	{
		dices = new ExtractedDices();
	}

	@Test
	public void testAddDice()
	{
		Dice dice1 = new Dice(Value.FOUR, GameColor.GREEN);
		dices.addDice(dice1);
		Assert.assertEquals(GameColor.GREEN, dices.getData().getDice(0).getGameColor());
		Assert.assertEquals(Value.FOUR, dices.getData().getDice(0).getValue());

		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(GameColor.PURPLE, dices.getData().getDice(1).getGameColor());
		Assert.assertEquals(Value.THREE, dices.getData().getDice(1).getValue());

	}

	@Test
	public void testGetNumberOfDices()
	{
		Assert.assertEquals(0, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.FOUR, GameColor.GREEN));
		Assert.assertEquals(1, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(2, dices.getData().getNumberOfDices());

		dices.changeDice(2, new Dice(Value.FIVE, GameColor.BLUE));
		Assert.assertEquals(2, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(3, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(4, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(5, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.TWO, GameColor.YELLOW));
		Assert.assertEquals(6, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(7, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(8, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(9, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(10, dices.getData().getNumberOfDices());

		dices.removeDice(3);
		Assert.assertEquals(9, dices.getData().getNumberOfDices());

	}

	@Test
	public void testChangeDice()
	{
		dices.addDice(new Dice(Value.FOUR, GameColor.GREEN));
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));

		Assert.assertEquals(2, dices.getData().getNumberOfDices());

		dices.changeDice(1, new Dice(Value.FIVE, GameColor.BLUE));

		Assert.assertEquals(2, dices.getData().getNumberOfDices());

		Assert.assertEquals(GameColor.BLUE, dices.getData().getDice(1).getGameColor());
		Assert.assertEquals(Value.FIVE, dices.getData().getDice(1).getValue());

		dices.changeDice(0, new Dice(Value.ONE, GameColor.YELLOW));

		Assert.assertEquals(2, dices.getData().getNumberOfDices());

		Assert.assertEquals(GameColor.YELLOW, dices.getData().getDice(0).getGameColor());
		Assert.assertEquals(Value.ONE, dices.getData().getDice(0).getValue());

	}

	@Test
	public void testRemoveDice()
	{
		dices.addDice(new Dice(Value.FOUR, GameColor.GREEN));
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		dices.addDice(new Dice(Value.ONE, GameColor.YELLOW));
		dices.addDice(new Dice(Value.FIVE, GameColor.PURPLE));
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		dices.addDice(new Dice(Value.TWO, GameColor.YELLOW));
		dices.removeDice(2);
		Assert.assertEquals(5, dices.getData().getNumberOfDices());
		Assert.assertEquals(Value.THREE, dices.getData().getDice(1).getValue());
		Assert.assertEquals(Value.FIVE, dices.getData().getDice(2).getValue());
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));

		Dice dice = dices.removeDice(15);
		Assert.assertNull(dice);

	}

	@Test
	public void testRemoveDiceFail()
	{
		Dice dice = dices.removeDice(15);
		Assert.assertNull(dice);

		dices.addDice(new Dice(Value.FOUR, GameColor.GREEN));
		Assert.assertNotNull(dices.removeDice(0));
		Assert.assertNull(dices.removeDice(0));
	}

	@Test
	public void testAddChangeRemoveGetNumber() {

		Dice dice1 = new Dice(Value.FOUR, GameColor.GREEN);
		dices.addDice(dice1);
		Assert.assertEquals(dice1, dices.getData().getDice(0));
		Assert.assertEquals(1, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(GameColor.PURPLE, dices.getData().getDice(1).getGameColor());
		Assert.assertEquals(Value.THREE, dices.getData().getDice(1).getValue());

		dices.changeDice(2, new Dice(Value.FIVE, GameColor.BLUE));
		Assert.assertEquals(2, dices.getData().getNumberOfDices());

		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(3, dices.getData().getNumberOfDices());
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(4, dices.getData().getNumberOfDices());
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(5, dices.getData().getNumberOfDices());
		dices.addDice(new Dice(Value.TWO, GameColor.YELLOW));
		Assert.assertEquals(6, dices.getData().getNumberOfDices());
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(7, dices.getData().getNumberOfDices());
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(8, dices.getData().getNumberOfDices());
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(9, dices.getData().getNumberOfDices());
		dices.addDice(new Dice(Value.THREE, GameColor.PURPLE));
		Assert.assertEquals(10, dices.getData().getNumberOfDices());

		Assert.assertEquals(GameColor.YELLOW, dices.getData().getDice(5).getGameColor());
		Assert.assertEquals(Value.TWO, dices.getData().getDice(5).getValue());

		dices.changeDice(5, new Dice(Value.ONE, GameColor.RED));
		Assert.assertEquals(GameColor.RED, dices.getData().getDice(5).getGameColor());
		Assert.assertEquals(Value.ONE, dices.getData().getDice(5).getValue());
		Assert.assertEquals(10, dices.getData().getNumberOfDices());

		dices.removeDice(5);
		Assert.assertEquals(9, dices.getData().getNumberOfDices());

		Dice dice = dices.removeDice(15);
		Assert.assertEquals(null, dice);
		Assert.assertEquals(9, dices.getData().getNumberOfDices());

	}
}
