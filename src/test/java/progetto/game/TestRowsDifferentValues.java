package progetto.game;

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

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 3, 4);

		Assert.assertEquals(5, rowsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testTwoCompleteRows()
	{

		RowsDifferentValuesPublicObjectiveCard rowsDifferentValuesPublicObjectiveCard = new RowsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 3, 4);

		dicePlacedFrame.addDice(new Dice(Value.SIX, Color.PURPLE), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.GREEN), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.RED), 1, 4);

		Assert.assertEquals(10, rowsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotCompleteRows()
	{

		RowsDifferentValuesPublicObjectiveCard rowsDifferentValuesPublicObjectiveCard = new RowsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.PURPLE), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.RED), 3, 4);

		Assert.assertEquals(0, rowsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotDifferent()
	{

		RowsDifferentValuesPublicObjectiveCard rowsDifferentValuesPublicObjectiveCard = new RowsDifferentValuesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 3, 4);

		Assert.assertEquals(0, rowsDifferentValuesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}


}
