package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.assertions.api.ColorAssert;

public class TestDicePlacementCondition extends TestCase {

	WindowFrameCoupleArray windowFrameCoupleArray;
	PlayerBoard playerBoard;

	@Before
	public void setUp()
	{
		windowFrameCoupleArray = new WindowFrameCoupleArray();
		playerBoard = new PlayerBoard();
		WindowFrame windowFrame = windowFrameCoupleArray.getWindowFrameCouples().get(1).getWindowFrame(0);
		playerBoard.setWindowFrame(windowFrame);

	}

	@Test
	public void testGetSet()
	{
		Dice dice = new Dice(Value.FOUR, Color.GREEN);
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, true, false);

		Assert.assertEquals(dice, dicePlacementCondition.getDice());
		Assert.assertTrue(dicePlacementCondition.getIgnoreValue());
		Assert.assertFalse(dicePlacementCondition.getIgnoreColor());
		Assert.assertFalse(dicePlacementCondition.getIgnoreAdjacent());

		dicePlacementCondition = dicePlacementCondition.setIgnoreValue(false);
		Assert.assertFalse(dicePlacementCondition.getIgnoreValue());
		dicePlacementCondition = dicePlacementCondition.setIgnoreColor(true);
		Assert.assertTrue(dicePlacementCondition.getIgnoreColor());
		dicePlacementCondition = dicePlacementCondition.setIgnoreAdjacent(true);
		Assert.assertTrue(dicePlacementCondition.getIgnoreAdjacent());

	}

	@Test
	public void testPlacementConditionNearEdge()
	{
		Dice dice = new Dice(Value.FOUR, Color.GREEN);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		//First dice near edge
		Assert.assertTrue(dicePlacementCondition.canBePlaced(0, 1, playerBoard));

	}

	@Test
	public void testPlacementConditionValueBondRespected()
	{
		Dice dice = new Dice(Value.FOUR, Color.GREEN);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		//Value bond respected
		Assert.assertTrue(dicePlacementCondition.canBePlaced(0, 0, playerBoard));

	}

	@Test
	public void testPlacementConditionColorBondRespected()
	{
		Dice dice = new Dice(Value.FOUR, Color.GREEN);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		//Color bond respected
		Assert.assertTrue(dicePlacementCondition.canBePlaced(0, 4, playerBoard));

	}

	@Test
	public void testPlacementConditionAdjacentBondRespected()
	{
		Dice dice = new Dice(Value.FOUR, Color.GREEN);

		playerBoard.addDiceInPlacedFrame(dice, 0, 1);

		dice = new Dice(Value.ONE, Color.YELLOW);
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		//Adjacent bond respected
		Assert.assertTrue(dicePlacementCondition.canBePlaced(1, 1, playerBoard));

	}

	@Test
	public void testPlacementConditionFailDiceNotNearEdge()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		//First dice not near edge
		Assert.assertFalse(dicePlacementCondition.canBePlaced(1, 1, playerBoard));

	}

	@Test
	public void testPlacementConditionFailValueBondNotRespected()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		//Value bond not respected
		Assert.assertFalse(dicePlacementCondition.canBePlaced(0, 0, playerBoard));

	}

	@Test
	public void testPlacementConditionFailColorBondNotRespected()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		//Color bond not respected
		Assert.assertFalse(dicePlacementCondition.canBePlaced(0, 4, playerBoard));

	}

	@Test
	public void testPlacementConditionFailAdjacentBondNotRespected()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		playerBoard.addDiceInPlacedFrame(dice, 0, 1);

		//Adjacent bond not respected
		Assert.assertFalse(dicePlacementCondition.canBePlaced(2, 0, playerBoard));

	}

	@Test
	public void testPlacementConditionFailWrongPosition()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		Assert.assertFalse(dicePlacementCondition.canBePlaced(5, 5, playerBoard));

	}

	@Test
	public void testPlacementConditionSameColorNear()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);
		playerBoard.getDicePlacedFrame().addDice(dice, 0, 1);
		dice = new Dice(Value.TWO, Color.YELLOW);
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);
		Assert.assertFalse(dicePlacementCondition.canBePlaced(1, 1, playerBoard));

	}

	@Test
	public void testPlacementConditionSameValueNear()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);
		playerBoard.getDicePlacedFrame().addDice(dice, 0, 1);
		dice = new Dice(Value.ONE, Color.BLUE);
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);
		Assert.assertFalse(dicePlacementCondition.canBePlaced(1, 1, playerBoard));

	}

	@Test
	public void testPlacementConditionIgnoreColorBond()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);
		playerBoard.getDicePlacedFrame().addDice(dice, 0, 1);
		dice = new Dice(Value.TWO, Color.YELLOW);
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, true, false, false);
		Assert.assertTrue(dicePlacementCondition.canBePlaced(1, 1, playerBoard));

	}

	@Test
	public void testPlacementConditionIgnoreValueBond()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);
		playerBoard.getDicePlacedFrame().addDice(dice, 0, 1);
		dice = new Dice(Value.ONE, Color.BLUE);
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, true, false);
		Assert.assertTrue(dicePlacementCondition.canBePlaced(1, 1, playerBoard));

	}

	@Test
	public void testPlacementConditionIgnoreAdjacentBond()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);
		playerBoard.getDicePlacedFrame().addDice(dice, 0, 1);
		dice = new Dice(Value.TWO, Color.BLUE);
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, true);
		Assert.assertTrue(dicePlacementCondition.canBePlaced(3, 4, playerBoard));

	}

	@Test
	public void testPlacementConditionFailTwoDiceSamePosition()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);
		playerBoard.getDicePlacedFrame().addDice(dice, 0, 1);
		dice = new Dice(Value.TWO, Color.BLUE);
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, true, true, true);
		Assert.assertFalse(dicePlacementCondition.canBePlaced(0, 1, playerBoard));

	}

	@Test
	public void testChangeDice()
	{
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(new Dice(Value.THREE, Color.PURPLE));
		dicePlacementCondition = dicePlacementCondition.changeDice(new Dice(Value.TWO, Color.RED));

		Assert.assertEquals(Value.TWO, dicePlacementCondition.getDice().getValue());
		Assert.assertEquals(Color.RED, dicePlacementCondition.getDice().getColor());

	}

}
