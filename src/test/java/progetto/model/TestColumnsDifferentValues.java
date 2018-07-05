package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test public objective card - Columns different values
 */
public class TestColumnsDifferentValues {

	DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
	}

	/**
	 * Case one complete columns
	 */
	@Test
	public void testOneCompleteColumn()
	{

		ColumnsDifferentValuesPublicObjectiveCard columnsDifferentValuesPublicObjectiveCard = new ColumnsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.BLUE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.SIX, GameColor.YELLOW), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 3, 4);

		Assert.assertEquals(4, columnsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	/**
	 * Case two complete columns
	 */
	@Test
	public void testTwoCompleteColumns()
	{

		ColumnsDifferentValuesPublicObjectiveCard columnsDifferentValuesPublicObjectiveCard = new ColumnsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 0, 0);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.YELLOW), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.GREEN), 2, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 0);

		dicePlacedFrame.addDice(new Dice(Value.SIX, GameColor.PURPLE), 0, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.GREEN), 2, 3);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.BLUE), 3, 3);

		Assert.assertEquals(8, columnsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	/**
	 * Case no complete columns
	 */
	@Test
	public void testNotCompleteColumns()
	{

		ColumnsDifferentValuesPublicObjectiveCard columnsDifferentValuesPublicObjectiveCard = new ColumnsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.PURPLE), 0, 0);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.YELLOW), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.GREEN), 2, 0);

		Assert.assertEquals(0, columnsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	/**
	 * Case no columns with different values
	 */
	@Test
	public void testNotDifferent()
	{

		ColumnsDifferentValuesPublicObjectiveCard columnsDifferentValuesPublicObjectiveCard = new ColumnsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);

		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.BLUE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.SIX, GameColor.PURPLE), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.SIX, GameColor.YELLOW), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.GREEN), 3, 2);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 3, 4);

		Assert.assertEquals(0, columnsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}


}
