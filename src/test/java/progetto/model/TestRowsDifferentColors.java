package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRowsDifferentColors {

	DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
	}


	@Test
	public void testOneCompleteRow()
	{

		RowsDifferentColorsPublicObjectiveCard rowsDifferentColorsPublicObjectiveCard = new RowsDifferentColorsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 3, 4);

		Assert.assertEquals(6, rowsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testTwoCompleteRows()
	{

		RowsDifferentColorsPublicObjectiveCard rowsDifferentColorsPublicObjectiveCard = new RowsDifferentColorsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 3, 4);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.GREEN), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 1, 4);

		Assert.assertEquals(12, rowsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotCompleteRows()
	{

		RowsDifferentColorsPublicObjectiveCard rowsDifferentColorsPublicObjectiveCard = new RowsDifferentColorsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 3, 4);

		Assert.assertEquals(0, rowsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotDifferent()
	{

		RowsDifferentColorsPublicObjectiveCard rowsDifferentColorsPublicObjectiveCard = new RowsDifferentColorsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 3, 4);

		Assert.assertEquals(0, rowsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}


}
