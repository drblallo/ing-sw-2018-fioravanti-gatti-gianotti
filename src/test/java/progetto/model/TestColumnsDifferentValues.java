package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestColumnsDifferentValues {

	DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
	}

	@Test
	public void testOneCompleteColumn()
	{

		ColumnsDifferentValuesPublicObjectiveCard columnsDifferentValuesPublicObjectiveCard = new ColumnsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.BLUE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.SIX, Color.YELLOW), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 3, 4);

		Assert.assertEquals(4, columnsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testTwoCompleteColumns()
	{

		ColumnsDifferentValuesPublicObjectiveCard columnsDifferentValuesPublicObjectiveCard = new ColumnsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.PURPLE), 0, 0);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.YELLOW), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 2, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 0);

		dicePlacedFrame.addDice(new Dice(Value.SIX, Color.PURPLE), 0, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.GREEN), 2, 3);
		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.BLUE), 3, 3);

		Assert.assertEquals(8, columnsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotCompleteColumns()
	{

		ColumnsDifferentValuesPublicObjectiveCard columnsDifferentValuesPublicObjectiveCard = new ColumnsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.PURPLE), 0, 0);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.YELLOW), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 2, 0);

		Assert.assertEquals(0, columnsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotDifferent()
	{

		ColumnsDifferentValuesPublicObjectiveCard columnsDifferentValuesPublicObjectiveCard = new ColumnsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);

		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.BLUE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.SIX, Color.PURPLE), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.SIX, Color.YELLOW), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.GREEN), 3, 2);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 3, 4);

		Assert.assertEquals(0, columnsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}


}
