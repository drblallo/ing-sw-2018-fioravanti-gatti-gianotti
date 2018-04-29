package progetto.game;

import junit.framework.TestCase;

public class TestPickedDicesSlot extends TestCase {

	public void test1()
	{

		PickedDicesSlot pickedDicesSlot = new PickedDicesSlot();

		Dice dice0 = new Dice (Value.ONE , Color.YELLOW);

		assertEquals(0, pickedDicesSlot.getNDices());

		pickedDicesSlot.add(dice0, false, false, false);

		assertEquals(1, pickedDicesSlot.getNDices());

		Dice dice1 = new Dice (Value.TWO , Color.RED);

		pickedDicesSlot.add(dice1, true, false, false);

		assertEquals(2, pickedDicesSlot.getNDices());

		pickedDicesSlot.remove(1);

		assertEquals(1, pickedDicesSlot.getNDices());
	}

}
