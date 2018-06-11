package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestPlayerBoard extends TestCase
{

	List<WindowFrameCouple>  windowFrameCouples;
	PlayerBoard playerBoard;
	WindowFrame windowFrame;

	@Before
	public void setUp()
	{
		windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		playerBoard = new PlayerBoard();
		windowFrame = windowFrameCouples.get(1).getWindowFrame(0);
		playerBoard.setWindowFrame(windowFrame);
	}

	@Test
	public void testGetNPickedDices()
	{
		Assert.assertEquals(0, playerBoard.getNPickedDices());

		playerBoard.addDiceToPickedSlot(new Dice(Value.ONE, Color.YELLOW));
		Assert.assertEquals(1, playerBoard.getNPickedDices());

		playerBoard.addDiceToPickedSlot(new Dice(Value.ONE, Color.YELLOW));
		Assert.assertEquals(2, playerBoard.getNPickedDices());

		playerBoard.addDiceToPickedSlot(new Dice(Value.ONE, Color.YELLOW));
		Assert.assertEquals(3, playerBoard.getNPickedDices());

		playerBoard.addDiceToPickedSlot(new Dice(Value.ONE, Color.YELLOW));
		Assert.assertEquals(4, playerBoard.getNPickedDices());

		playerBoard.getPickedDicesSlot().remove(0);
		Assert.assertEquals(3, playerBoard.getNPickedDices());

		playerBoard.getPickedDicesSlot().remove(0);
		Assert.assertEquals(2, playerBoard.getNPickedDices());

		playerBoard.getPickedDicesSlot().remove(0);
		Assert.assertEquals(1, playerBoard.getNPickedDices());

		playerBoard.getPickedDicesSlot().remove(0);
		Assert.assertEquals(0, playerBoard.getNPickedDices());

	}

	@Test
	public void testGetNPickedDicesFail()
	{
		Assert.assertEquals(0, playerBoard.getNPickedDices());

		playerBoard.getPickedDicesSlot().remove(0);
		Assert.assertEquals(0, playerBoard.getNPickedDices());

	}

	@Test
	public void testNPlacedDices() {
		Assert.assertEquals(0, playerBoard.getNDicesPlaced());

		playerBoard.addDiceInPlacedFrame(new Dice(Value.ONE, Color.YELLOW), 0, 0);
		Assert.assertEquals(1, playerBoard.getNDicesPlaced());

		playerBoard.addDiceInPlacedFrame(new Dice(Value.ONE, Color.YELLOW), 0, 1);
		Assert.assertEquals(2, playerBoard.getNDicesPlaced());

		playerBoard.addDiceInPlacedFrame(new Dice(Value.ONE, Color.YELLOW), 0, 2);
		Assert.assertEquals(3, playerBoard.getNDicesPlaced());

		playerBoard.addDiceInPlacedFrame(new Dice(Value.ONE, Color.YELLOW), 2, 2);
		Assert.assertEquals(4, playerBoard.getNDicesPlaced());

		playerBoard.getDicePlacedFrame().removeDice(0, 0);
		Assert.assertEquals(3, playerBoard.getNDicesPlaced());

		playerBoard.getDicePlacedFrame().removeDice(0, 1);
		Assert.assertEquals(2, playerBoard.getNDicesPlaced());

		playerBoard.getDicePlacedFrame().removeDice(0, 2);
		Assert.assertEquals(1, playerBoard.getNDicesPlaced());

		playerBoard.getDicePlacedFrame().removeDice(2, 2);
		Assert.assertEquals(0, playerBoard.getNDicesPlaced());

	}

	@Test
	public void testGetNPlacedDicesFail()
	{
		playerBoard.getDicePlacedFrame().removeDice(0, 0);
		Assert.assertEquals(0, playerBoard.getNPickedDices());

	}

	@Test
	public void testSetWindowFrame()
	{
		playerBoard.setWindowFrame(windowFrame);
		Assert.assertEquals(windowFrame, playerBoard.getData().getWindowFrame());

	}

	@Test
	public void testSetWindowFrameSide()
	{
		WindowFrameCouple windowFrameCouple = windowFrameCouples.get(5);
		playerBoard.setWindowFrame(windowFrameCouple, 1);
		Assert.assertEquals(windowFrameCouple.getWindowFrame(1), playerBoard.getData().getWindowFrame());

	}

	@Test
	public void testGetRemovePlacedDice()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);
		playerBoard.addDiceInPlacedFrame(dice, 0, 0);

		Assert.assertEquals(dice, playerBoard.getDiceFromPlacedFrame(0, 0));
		playerBoard.removeDiceFromDicesPlaced(0, 0);
		Assert.assertEquals(0, playerBoard.getNDicesPlaced());
	}

	@Test
	public void testAddRemoveDicePlacedFrame()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		playerBoard.addDiceInPlacedFrame(dice, 0, 0);

		Assert.assertEquals(dice, playerBoard.getDiceFromPlacedFrame(0, 0));

		playerBoard.removeDiceFromDicesPlaced(0, 0);

		Assert.assertNull(playerBoard.getDiceFromPlacedFrame(0, 0));

		Assert.assertEquals(0, playerBoard.getNDicesPlaced());

		Assert.assertNull(playerBoard.getDicePlacedFrame().getData().getDice(0,0));

	}

}
