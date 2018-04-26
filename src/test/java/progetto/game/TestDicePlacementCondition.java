package progetto.game;

import junit.framework.TestCase;
import org.json.JSONArray;

public class TestDicePlacementCondition extends TestCase {

	JSONArray ja;

	public void startJA()
	{
		ja = new JSONArray();
		ja.put("Virtus"); ja.put(5); ja.put(9); ja.put(0); ja.put(0); ja.put(Value.FOUR); ja.put(2); ja.put(0);
		ja.put(Value.TWO); ja.put(3); ja.put(0); ja.put(Value.FIVE); ja.put(2); ja.put(1); ja.put(Value.SIX);
		ja.put(4); ja.put(1); ja.put(Value.TWO); ja.put(1); ja.put(2); ja.put(Value.THREE); ja.put(3);
		ja.put(2); ja.put(Value.FOUR); ja.put(0); ja.put(3); ja.put(Value.FIVE); ja.put(2); ja.put(3);
		ja.put(Value.ONE); ja.put(4); ja.put(4); ja.put(0); ja.put(Color.GREEN); ja.put(3); ja.put(1);
		ja.put(Color.GREEN); ja.put(2); ja.put(2); ja.put(Color.GREEN); ja.put(1); ja.put(3); ja.put(Color.GREEN);
	}

	public void test1() {

		startJA();

		WindowFrame wf = new WindowFrame(ja);

		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

		assertEquals(false, dicePlacementCondition.canBePlaced(1, 1, wf, dicePlacedFrame));


	}

	public void test2() {

		startJA();

		WindowFrame wf = new WindowFrame(ja);

		DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false );

		assertEquals(false, dicePlacementCondition.canBePlaced(0, 0, wf, dicePlacedFrame));


	}

	public void test3() {

		startJA();

		WindowFrame wf = new WindowFrame(ja);

		DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, true, false );

		assertEquals(true, dicePlacementCondition.canBePlaced(0, 0, wf, dicePlacedFrame));

		dicePlacedFrame.addDice(dice, 0,0);

		Dice dice1 = new Dice(Value.ONE, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice1, false, false, false );

		assertEquals(false, dicePlacementCondition.canBePlaced(1, 0, wf, dicePlacedFrame));

		Dice dice11 = new Dice(Value.THREE, Color.RED);

		dicePlacementCondition = new DicePlacementCondition(dice11, false, false, false );

		assertEquals(true, dicePlacementCondition.canBePlaced(1, 0, wf, dicePlacedFrame));

		dicePlacedFrame.addDice(dice11, 1,0);

		Dice dice2 = new Dice(Value.ONE, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice2, false, false, false );

		assertEquals(false, dicePlacementCondition.canBePlaced(2, 0, wf, dicePlacedFrame));

		Dice dice3 = new Dice(Value.ONE, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice3, false, false, false );

		assertEquals(false, dicePlacementCondition.canBePlaced(4, 0, wf, dicePlacedFrame));

		Dice dice4 = new Dice(Value.ONE, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice4, false, false, false );

		assertEquals(false, dicePlacementCondition.canBePlaced(0, 2, wf, dicePlacedFrame));

		Dice dice5 = new Dice(Value.ONE, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice5, true, false, false);

		assertEquals(false, dicePlacementCondition.canBePlaced(3, 1, wf, dicePlacedFrame));

		Dice dice6 = new Dice(Value.TWO, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice6, false, false, false);

		assertEquals(true, dicePlacementCondition.canBePlaced(2, 0, wf, dicePlacedFrame));

		dicePlacedFrame.addDice(dice6, 2,0);

		dicePlacementCondition = new DicePlacementCondition(dice5, true, false, false);

		assertEquals(true, dicePlacementCondition.canBePlaced(3, 1, wf, dicePlacedFrame));

		dicePlacedFrame.addDice(dice5, 3,1);

		assertEquals(false, dicePlacementCondition.canBePlaced(3, 1, wf, dicePlacedFrame));

		assertEquals(false, dicePlacementCondition.canBePlaced(-1, 1, wf, dicePlacedFrame));

		Dice dice7 = new Dice(Value.TWO, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice7, false, false, true);

		assertEquals(true, dicePlacementCondition.canBePlaced(4, 3, wf, dicePlacedFrame));

		dicePlacedFrame.addDice(dice7, 4,3);

		Dice dice8 = new Dice(Value.SIX, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice8, false, false, false);

		assertEquals(false, dicePlacementCondition.canBePlaced(0, 1, wf, dicePlacedFrame));

	}
}
