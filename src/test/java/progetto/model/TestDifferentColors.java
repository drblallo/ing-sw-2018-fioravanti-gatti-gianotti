package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDifferentColors {

	DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();
	DifferentColorsPublicObjectiveCard differentColorsPublicObjectiveCard;

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
		differentColorsPublicObjectiveCard = new DifferentColorsPublicObjectiveCard();

	}


	@Test
	public void testN0()
	{
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 1, 4);

		Assert.assertEquals(0, differentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	@Test
	public void testN1()
	{
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.RED), 1, 4);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.RED), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.SIX, Color.RED), 0, 3);

		Assert.assertEquals(4, differentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	@Test
	public void testN3()
	{
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.ONE, Color.RED), 1, 4);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.RED), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.SIX, Color.YELLOW), 0, 3);
		dicePlacedFrame.addDice(new Dice(Value.TWO, Color.RED), 0, 4);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.RED), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.GREEN), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.BLUE), 2, 0);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.RED), 2, 3);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 4);
		dicePlacedFrame.addDice(new Dice(Value.SIX, Color.RED), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.RED), 3, 4);

		Assert.assertEquals(12, differentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));
	}


}
