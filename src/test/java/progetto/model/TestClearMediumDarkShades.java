package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test public objective card - clear/medium/dark shades
 */
public class TestClearMediumDarkShades {

	DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
	}

	/**
	 * Test clear shades case 1
	 */
	@Test
	public void testClearShades1()
	{
		ClearShadesPublicObjectiveCard clearShadesPublicObjectiveCard = new ClearShadesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 1, 4);

		Assert.assertEquals(0, clearShadesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	/**
	 * Test clear shades case2
	 */
	@Test
	public void testClearShades2()
	{
		ClearShadesPublicObjectiveCard clearShadesPublicObjectiveCard = new ClearShadesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.TWO, GameColor.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 1, 4);

		Assert.assertEquals(4, clearShadesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	/**
	 * Test dark shades - case 1
	 */
	@Test
	public void testDarkShades1()
	{
		DarkShadesPublicObjectiveCard darkShadesPublicObjectiveCard = new DarkShadesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 1, 4);

		Assert.assertEquals(0, darkShadesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	/**
	 * Test dark shades case 2
	 */
	@Test
	public void testDarkShades2()
	{
		DarkShadesPublicObjectiveCard darkShadesPublicObjectiveCard = new DarkShadesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.ONE, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.RED), 1, 4);

		Assert.assertEquals(2, darkShadesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	/**
	 * Test medium shades case 1
	 */
	@Test
	public void testMediumShades3()
	{
		MediumShadesPublicObjectiveCard mediumShadesPublicObjectiveCard = new MediumShadesPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.RED), 1, 4);

		Assert.assertEquals(6, mediumShadesPublicObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

}
