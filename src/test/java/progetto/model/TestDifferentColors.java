package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test public objective card different colors
 */
public class TestDifferentColors {

	DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();
	DifferentColorsPublicObjectiveCard differentColorsPublicObjectiveCard;

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
		differentColorsPublicObjectiveCard = new DifferentColorsPublicObjectiveCard();

	}

	/**
	 * Test - no set of dices with different colors
	 */
	@Test
	public void testN0()
	{
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 1, 4);

		Assert.assertEquals(0, differentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	/**
	 * Test - one set of dices with different colors
	 */
	@Test
	public void testN1()
	{
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 1, 4);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.RED), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.SIX, GameColor.RED), 0, 3);

		Assert.assertEquals(4, differentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	/**
	 * Test - three set of dices with different colors
	 */
	@Test
	public void testN3()
	{
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.RED), 1, 4);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.RED), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.SIX, GameColor.YELLOW), 0, 3);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.RED), 0, 4);
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.BLUE), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.RED), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.GREEN), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 2, 0);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.RED), 2, 3);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 4);
		dicePlacedFrame.addDice(new Dice(Value.SIX, GameColor.RED), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.GREEN), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.RED), 3, 4);

		Assert.assertEquals(12, differentColorsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));
	}


}
