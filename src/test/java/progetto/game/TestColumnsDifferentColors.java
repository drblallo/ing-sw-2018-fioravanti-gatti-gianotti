package progetto.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestColumnsDifferentColors {

	DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
	}


	@Test
	public void testOneCompleteColumn()
	{

		ColumnsDifferentColorsPublicObjectiveCard columnsDifferentColorsPublicObjectiveCard = new ColumnsDifferentColorsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.BLUE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 3, 4);

		Assert.assertEquals(5, columnsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testTwoCompleteColumns()
	{

		ColumnsDifferentColorsPublicObjectiveCard columnsDifferentColorsPublicObjectiveCard = new ColumnsDifferentColorsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 0, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 2, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 0);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 0, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 2, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 3);

		Assert.assertEquals(10, columnsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotCompleteColumns()
	{

		ColumnsDifferentColorsPublicObjectiveCard columnsDifferentColorsPublicObjectiveCard = new ColumnsDifferentColorsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 0, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 2, 0);

		Assert.assertEquals(0, columnsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNotDifferent()
	{

		ColumnsDifferentColorsPublicObjectiveCard columnsDifferentColorsPublicObjectiveCard = new ColumnsDifferentColorsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);

		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.BLUE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 3, 2);

		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 3, 4);

		Assert.assertEquals(0, columnsDifferentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}


}
