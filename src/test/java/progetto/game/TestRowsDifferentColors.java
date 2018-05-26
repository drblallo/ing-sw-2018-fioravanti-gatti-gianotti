package progetto.game;

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

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 3, 4);

		Assert.assertEquals(6, rowsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testTwoCompleteRows()
	{

		RowsDifferentColorsPublicObjectiveCard rowsDifferentColorsPublicObjectiveCard = new RowsDifferentColorsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 3, 4);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 1, 4);

		Assert.assertEquals(12, rowsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotCompleteRows()
	{

		RowsDifferentColorsPublicObjectiveCard rowsDifferentColorsPublicObjectiveCard = new RowsDifferentColorsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 3, 4);

		Assert.assertEquals(0, rowsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotDifferent()
	{

		RowsDifferentColorsPublicObjectiveCard rowsDifferentColorsPublicObjectiveCard = new RowsDifferentColorsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 3, 4);

		Assert.assertEquals(0, rowsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}


}
