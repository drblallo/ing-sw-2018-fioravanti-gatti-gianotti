package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test private objective cards
 */
public class TestPrivateObjective {

	DicePlacedFrame dicePlacedFrame;

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
		dicePlacedFrame.addDice(new Dice(Value.THREE, GameColor.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, GameColor.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, GameColor.PURPLE), 3, 4);
	}

	/**
	 * Test private objective card purple shades
	 */
	@Test
	public void testPurpleShades()
	{
		ColorShadesPrivateObjectiveCard colorShadesPrivateObjectiveCard = new ColorShadesPrivateObjectiveCard(GameColor.PURPLE);
		Assert.assertEquals("Sfumature Viola Somma dei valori su tutti i dadi Viola" , colorShadesPrivateObjectiveCard.getToolTip());
		Assert.assertEquals(9, colorShadesPrivateObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	/**
	 * Test private objective card yellow shades
	 */
	@Test
	public void testYellowShades()
	{
		ColorShadesPrivateObjectiveCard colorShadesPrivateObjectiveCard = new ColorShadesPrivateObjectiveCard(GameColor.YELLOW);
		Assert.assertEquals(3, colorShadesPrivateObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	/**
	 * Test private objective card blue shades
	 */
	@Test
	public void testBlueShades()
	{
		ColorShadesPrivateObjectiveCard colorShadesPrivateObjectiveCard = new ColorShadesPrivateObjectiveCard(GameColor.BLUE);
		Assert.assertEquals(0, colorShadesPrivateObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	/**
	 * Test private objective card red shades
	 */
	@Test
	public void testRedShades()
	{
		ColorShadesPrivateObjectiveCard colorShadesPrivateObjectiveCard = new ColorShadesPrivateObjectiveCard(GameColor.RED);
		Assert.assertEquals(0, colorShadesPrivateObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	/**
	 * Test private objective card green shades
	 */
	@Test
	public void testGreenShades()
	{
		ColorShadesPrivateObjectiveCard colorShadesPrivateObjectiveCard = new ColorShadesPrivateObjectiveCard(GameColor.GREEN);
		Assert.assertEquals(0, colorShadesPrivateObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

}
