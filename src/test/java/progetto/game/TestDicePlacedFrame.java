package progetto.game;

import junit.framework.TestCase;

public class TestDicePlacedFrame extends TestCase {

	public void test1()
	{
		Dice dice1 = new Dice(Value.FOUR, Color.GREEN);
		Dice dice2 = new Dice (Value.TWO, Color.RED);
		Dice dice3 = new Dice(Value.SIX, Color.PURPLE);

		DicePlacedFrame dpf = new DicePlacedFrame();

		dpf.addDice(dice1, 2, 2);

		assertEquals(1, dpf.getDicePlacedFrameData().getNDices());

		dpf.addDice(dice2, 4, 3);

		assertEquals(2, dpf.getDicePlacedFrameData().getNDices());

		dpf.addDice(dice3, 0, 0);

		assertEquals(3, dpf.getDicePlacedFrameData().getNDices());

		assertEquals(dice1, dpf.getDicePlacedFrameData().getDice(2,2));

		assertEquals(3, dpf.getDicePlacedFrameData().getNDices());

		assertEquals(dice3, dpf.getDicePlacedFrameData().getDice(0,0));
		assertEquals(3, dpf.getDicePlacedFrameData().getNDices());
		assertEquals(dice2, dpf.getDicePlacedFrameData().getDice(4,3));
		assertEquals(3, dpf.getDicePlacedFrameData().getNDices());

		dpf.removeDice(2,2);

		assertEquals(2, dpf.getDicePlacedFrameData().getNDices());

		assertEquals(null, dpf.getDicePlacedFrameData().getDice(2,2));

		assertEquals(2, dpf.getDicePlacedFrameData().getNDices());

	}
}
