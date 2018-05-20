package progetto.game;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPickedDicesSlot extends TestCase {

	PickedDicesSlot pickedDicesSlot;

	@Before
	public void setUp()
	{
		pickedDicesSlot = new PickedDicesSlot();
	}

	@Test
	public void testConstructor()
	{
		Assert.assertEquals(0, pickedDicesSlot.getNDices());
		Assert.assertNull(pickedDicesSlot.getPickedDicesSlotData().getDicePlacementCondition(0));
		Assert.assertNull(pickedDicesSlot.getPickedDicesSlotData().getDicePlacementCondition(1));
	}

	@Test
	public void testGetNDices()
	{
		Assert.assertEquals(0, pickedDicesSlot.getNDices());

		pickedDicesSlot.add(new Dice(Value.ONE, Color.YELLOW), false, false, false);
		Assert.assertEquals(1, pickedDicesSlot.getNDices());

		pickedDicesSlot.add(new Dice(Value.ONE, Color.YELLOW), false, false, false);
		Assert.assertEquals(2, pickedDicesSlot.getNDices());

		pickedDicesSlot.remove(0);
		Assert.assertEquals(1, pickedDicesSlot.getNDices());

	}

	@Test
	public void testRemove()
	{
		Dice dice = new Dice(Value.ONE, Color.YELLOW);
		Dice dice1 = new Dice(Value.TWO, Color.YELLOW);

		pickedDicesSlot.add(dice, false, false, false);
		Assert.assertEquals(dice, pickedDicesSlot.remove(0).getDice());
		Assert.assertEquals(0, pickedDicesSlot.getNDices());

		pickedDicesSlot.add(dice, false, false, false);
		pickedDicesSlot.add(dice1, false, false, false);
		Assert.assertEquals(dice1, pickedDicesSlot.remove(1).getDice());
		Assert.assertEquals(1, pickedDicesSlot.getNDices());
		Assert.assertEquals(dice, pickedDicesSlot.remove(0).getDice());
		Assert.assertEquals(0, pickedDicesSlot.getNDices());

	}

	@Test
	public void testRemoveFail()
	{
		Assert.assertNull(pickedDicesSlot.remove(0));

		pickedDicesSlot.add(new Dice(Value.ONE, Color.YELLOW), false, false, false);

		Assert.assertNull(pickedDicesSlot.remove(1));

	}

	@Test
	public void testSetIgnore()
	{
		pickedDicesSlot.add(new Dice(Value.ONE, Color.YELLOW), false, true, false);

		pickedDicesSlot.setIgnoreColor(0, true);
		Assert.assertTrue(pickedDicesSlot.getPickedDicesSlotData().getDicePlacementCondition(0).getIgnoreColor());

		pickedDicesSlot.setIgnoreValue(0, false);
		Assert.assertFalse(pickedDicesSlot.getPickedDicesSlotData().getDicePlacementCondition(0).getIgnoreValue());

		pickedDicesSlot.setIgnoreAdjacent(0, true);
		Assert.assertTrue(pickedDicesSlot.getPickedDicesSlotData().getDicePlacementCondition(0).getIgnoreAdjacent());

	}

	@Test
	public void testSetIgnoreFail()
	{
		PickedDicesSlotData pickedDicesSlotData = pickedDicesSlot.getPickedDicesSlotData();

		pickedDicesSlot.setIgnoreColor(0, true);
		Assert.assertEquals(pickedDicesSlotData, pickedDicesSlot.getPickedDicesSlotData());

		pickedDicesSlot.setIgnoreValue(0, true);
		Assert.assertEquals(pickedDicesSlotData, pickedDicesSlot.getPickedDicesSlotData());

		pickedDicesSlot.setIgnoreAdjacent(0, true);
		Assert.assertEquals(pickedDicesSlotData, pickedDicesSlot.getPickedDicesSlotData());

	}

}
