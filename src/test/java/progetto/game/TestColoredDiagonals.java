package progetto.game;

		import org.junit.Assert;
		import org.junit.Before;
		import org.junit.Test;

public class TestColoredDiagonals {

	DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
	}

	@Test
	public void testNoColoredDiagonals()
	{

		ColoredDiagonalsPublicObjectiveCard coloredDiagonalsPublicObjectiveCard = new ColoredDiagonalsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 1, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 2, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 2, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 2, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 3, 4);

		Assert.assertEquals(0, coloredDiagonalsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testNoDices()
	{

		ColoredDiagonalsPublicObjectiveCard coloredDiagonalsPublicObjectiveCard = new ColoredDiagonalsPublicObjectiveCard();
		Assert.assertEquals(0, coloredDiagonalsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testMaxNDices()
	{

		ColoredDiagonalsPublicObjectiveCard coloredDiagonalsPublicObjectiveCard = new ColoredDiagonalsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 1, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 2, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 2, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 2, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 3, 4);

		Assert.assertEquals(20, coloredDiagonalsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testOneDiagonal()
	{

		ColoredDiagonalsPublicObjectiveCard coloredDiagonalsPublicObjectiveCard = new ColoredDiagonalsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 0, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 1, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 2, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 2, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 2, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.PURPLE), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.PURPLE), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.PURPLE), 3, 4);

		Assert.assertEquals(4, coloredDiagonalsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}

	@Test
	public void testTwoDiagonal()
	{

		ColoredDiagonalsPublicObjectiveCard coloredDiagonalsPublicObjectiveCard = new ColoredDiagonalsPublicObjectiveCard();

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 0, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.YELLOW), 0, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 0, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 1, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 1, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 1, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 1, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.GREEN), 1, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 2, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 2, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 2, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 2, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.RED), 2, 4);

		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.PURPLE), 3, 0);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 3, 1);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.PURPLE), 3, 2);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.BLUE), 3, 3);
		dicePlacedFrame.addDice(new Dice(Value.THREE, Color.PURPLE), 3, 4);

		Assert.assertEquals(7, coloredDiagonalsPublicObjectiveCard.evaluateFrame(dicePlacedFrame));

	}


}
