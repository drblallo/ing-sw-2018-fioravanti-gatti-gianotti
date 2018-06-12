package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRowsDifferentValues {

	DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
	}

	@Test
	public void testOneCompleteRow()
	{

		RowsDifferentValuesPublicObjectiveCard rowsDifferentValuesPublicObjectiveCard = new RowsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 3, 4);

		Assert.assertEquals(5, rowsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testTwoCompleteRows()
	{

		RowsDifferentValuesPublicObjectiveCard rowsDifferentValuesPublicObjectiveCard = new RowsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 3, 4);

		dicePlacedFrame.addDice(new Dice(Value.SIX, GameColor.PURPLE), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.GREEN), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.BLUE), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.RED), 1, 4);

		Assert.assertEquals(10, rowsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotCompleteRows()
	{

		RowsDifferentValuesPublicObjectiveCard rowsDifferentValuesPublicObjectiveCard = new RowsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.RED), 3, 4);

		Assert.assertEquals(0, rowsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotDifferent()
	{

		RowsDifferentValuesPublicObjectiveCard rowsDifferentValuesPublicObjectiveCard = new RowsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 3, 4);

		Assert.assertEquals(0, rowsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}


}
