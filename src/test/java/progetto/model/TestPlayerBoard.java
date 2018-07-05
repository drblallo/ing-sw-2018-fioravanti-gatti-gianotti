package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Test playerBoard class
 */
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

	/**
	 * Test get number of picked dices
	 */
	@Test
	public void testGetNPickedDices()
	{
		Assert.assertEquals(0, playerBoard.getNPickedDices());

		playerBoard.addDiceToPickedSlot(new Dice(Value.ONE, GameColor.YELLOW));
		Assert.assertEquals(1, playerBoard.getNPickedDices());

		playerBoard.addDiceToPickedSlot(new Dice(Value.ONE, GameColor.YELLOW));
		Assert.assertEquals(2, playerBoard.getNPickedDices());

		playerBoard.addDiceToPickedSlot(new Dice(Value.ONE, GameColor.YELLOW));
		Assert.assertEquals(3, playerBoard.getNPickedDices());

		playerBoard.addDiceToPickedSlot(new Dice(Value.ONE, GameColor.YELLOW));
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

	/**
	 * Test get number of picked dices - fail - not added dices
	 */
	@Test
	public void testGetNPickedDicesFail()
	{
		Assert.assertEquals(0, playerBoard.getNPickedDices());

		playerBoard.getPickedDicesSlot().remove(0);
		Assert.assertEquals(0, playerBoard.getNPickedDices());

	}

	/**
	 * Test get number of placed dices
	 */
	@Test
	public void testNPlacedDices() {
		Assert.assertEquals(0, playerBoard.getNDicesPlaced());

		playerBoard.addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		Assert.assertEquals(1, playerBoard.getNDicesPlaced());

		playerBoard.addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 1);
		Assert.assertEquals(2, playerBoard.getNDicesPlaced());

		playerBoard.addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 2);
		Assert.assertEquals(3, playerBoard.getNDicesPlaced());

		playerBoard.addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 2, 2);
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

	/**
	 * Test get number of placed dices - fail - no dices
	 */
	@Test
	public void testGetNPlacedDicesFail()
	{
		playerBoard.getDicePlacedFrame().removeDice(0, 0);
		Assert.assertEquals(0, playerBoard.getNPickedDices());

	}

	/**
	 * Test set windowFrame
	 */
	@Test
	public void testSetWindowFrame()
	{
		playerBoard.setWindowFrame(windowFrame);
		Assert.assertEquals(windowFrame, playerBoard.getData().getWindowFrame());

	}

	/**
	 * Test set windowFrame side
	 */
	@Test
	public void testSetWindowFrameSide()
	{
		WindowFrameCouple windowFrameCouple = windowFrameCouples.get(5);
		playerBoard.setWindowFrame(windowFrameCouple, 1);
		Assert.assertEquals(windowFrameCouple.getWindowFrame(1), playerBoard.getData().getWindowFrame());

	}

	/**
	 * Test get and remove placed dice from placed frame
	 */
	@Test
	public void testGetRemovePlacedDice()
	{
		Dice dice = new Dice(Value.ONE, GameColor.YELLOW);
		playerBoard.addDiceInPlacedFrame(dice, 0, 0);

		Assert.assertEquals(dice, playerBoard.getDiceFromPlacedFrame(0, 0));
		playerBoard.removeDiceFromDicesPlaced(0, 0);
		Assert.assertEquals(0, playerBoard.getNDicesPlaced());
	}

	/**
	 * Test and and remove add and remove dice from placed frame
	 */
	@Test
	public void testAddRemoveDicePlacedFrame()
	{
		Dice dice = new Dice(Value.ONE, GameColor.YELLOW);

		playerBoard.addDiceInPlacedFrame(dice, 0, 0);

		Assert.assertEquals(dice, playerBoard.getDiceFromPlacedFrame(0, 0));

		playerBoard.removeDiceFromDicesPlaced(0, 0);

		Assert.assertNull(playerBoard.getDiceFromPlacedFrame(0, 0));

		Assert.assertEquals(0, playerBoard.getNDicesPlaced());

		Assert.assertNull(playerBoard.getDicePlacedFrame().getData().getDice(0,0));

	}

	/**
	 * Test get private objective card id (from PlayerBoard)
	 */
	@Test
	public void testCardID()
	{
		playerBoard.addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.YELLOW));
		playerBoard.addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.GREEN));


		Assert.assertEquals(0, playerBoard.getData().getPrivateObjectiveCard().get(0).getCardID());
		Assert.assertEquals(3, playerBoard.getData().getPrivateObjectiveCard().get(1).getCardID());

	}

}
