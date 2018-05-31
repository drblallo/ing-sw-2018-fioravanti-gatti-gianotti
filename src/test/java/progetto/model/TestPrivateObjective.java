package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPrivateObjective {

	DicePlacedFrame dicePlacedFrame;

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.FOUR, Color.PURPLE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.FIVE, Color.PURPLE), 3, 4);
	}

	@Test
	public void testPurpleShades()
	{
		ColorShadesPrivateObjectiveCard colorShadesPrivateObjectiveCard = new ColorShadesPrivateObjectiveCard(Color.PURPLE);
		Assert.assertEquals("Sfumature Viola Somma dei valori su tutti i dadi Viola" , colorShadesPrivateObjectiveCard.getToolTip());
		Assert.assertEquals(9, colorShadesPrivateObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	@Test
	public void testYellowShades()
	{
		ColorShadesPrivateObjectiveCard colorShadesPrivateObjectiveCard = new ColorShadesPrivateObjectiveCard(Color.YELLOW);
		Assert.assertEquals(3, colorShadesPrivateObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	@Test
	public void testBlueShades()
	{
		ColorShadesPrivateObjectiveCard colorShadesPrivateObjectiveCard = new ColorShadesPrivateObjectiveCard(Color.BLUE);
		Assert.assertEquals(0, colorShadesPrivateObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	@Test
	public void testRedShades()
	{
		ColorShadesPrivateObjectiveCard colorShadesPrivateObjectiveCard = new ColorShadesPrivateObjectiveCard(Color.RED);
		Assert.assertEquals(0, colorShadesPrivateObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

	@Test
	public void testGreenShades()
	{
		ColorShadesPrivateObjectiveCard colorShadesPrivateObjectiveCard = new ColorShadesPrivateObjectiveCard(Color.GREEN);
		Assert.assertEquals(0, colorShadesPrivateObjectiveCard.evaluateFrame(dicePlacedFrame));
	}

}
