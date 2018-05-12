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

		PlayerBoard playerBoard = new PlayerBoard();

		WindowFrame wf = new WindowFrame(ja);

		playerBoard.setWindowFrame(wf);

		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false);

		DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();


		assertEquals(false, dicePlacementCondition.canBePlaced(1, 1, playerBoard));


	}

	public void test2() {

		startJA();

		PlayerBoard playerBoard = new PlayerBoard();

		WindowFrame wf = new WindowFrame(ja);

		playerBoard.setWindowFrame(wf);

		DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, false, false );

		assertEquals(false, dicePlacementCondition.canBePlaced(0, 0, playerBoard));


	}

	public void test3() {

		startJA();

		PlayerBoard playerBoard = new PlayerBoard();

		WindowFrame wf = new WindowFrame(ja);

		playerBoard.setWindowFrame(wf);

		DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();

		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, false, true, false );

		assertEquals(true, dicePlacementCondition.canBePlaced(0, 0, playerBoard));

		playerBoard.addDiceInPlacedFrame(dice, 0,0);

		Dice dice1 = new Dice(Value.ONE, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice1, false, false, false );

		assertEquals(false, dicePlacementCondition.canBePlaced(1, 0, playerBoard));

		Dice dice11 = new Dice(Value.THREE, Color.RED);

		dicePlacementCondition = new DicePlacementCondition(dice11, false, false, false );

		assertEquals(true, dicePlacementCondition.canBePlaced(1, 0, playerBoard));

		playerBoard.addDiceInPlacedFrame(dice11, 1, 0);

		Dice dice2 = new Dice(Value.ONE, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice2, false, false, false );

		assertEquals(false, dicePlacementCondition.canBePlaced(2, 0, playerBoard));

		Dice dice3 = new Dice(Value.ONE, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice3, false, false, false );

		assertEquals(false, dicePlacementCondition.canBePlaced(4, 0, playerBoard));

		Dice dice4 = new Dice(Value.ONE, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice4, false, false, false );

		assertEquals(false, dicePlacementCondition.canBePlaced(0, 2, playerBoard));

		Dice dice5 = new Dice(Value.ONE, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice5, true, false, false);

		assertEquals(false, dicePlacementCondition.canBePlaced(3, 1, playerBoard));

		Dice dice6 = new Dice(Value.TWO, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice6, false, false, false);

		assertEquals(true, dicePlacementCondition.canBePlaced(2, 0, playerBoard));

		playerBoard.addDiceInPlacedFrame(dice6, 2,0);

		dicePlacementCondition = new DicePlacementCondition(dice5, true, false, false);

		assertEquals(true, dicePlacementCondition.canBePlaced(3, 1, playerBoard));

		playerBoard.addDiceInPlacedFrame(dice5, 3,1);

		assertEquals(false, dicePlacementCondition.canBePlaced(3, 1, playerBoard));

		assertEquals(false, dicePlacementCondition.canBePlaced(-1, 1, playerBoard));

		Dice dice7 = new Dice(Value.TWO, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice7, false, false, true);

		assertEquals(true, dicePlacementCondition.canBePlaced(4, 3, playerBoard));

		playerBoard.addDiceInPlacedFrame(dice7, 4,3);

		Dice dice8 = new Dice(Value.SIX, Color.YELLOW);

		dicePlacementCondition = new DicePlacementCondition(dice8, false, false, false);

		assertEquals(false, dicePlacementCondition.canBePlaced(0, 1, playerBoard));

		assertEquals(dice8, dicePlacementCondition.getDice());
		assertEquals(false, (boolean)dicePlacementCondition.getIgnoreAdjacent());
		assertEquals(false, (boolean)dicePlacementCondition.getIgnoreColor());
		assertEquals(false, (boolean)dicePlacementCondition.getIgnoreValue());

		dicePlacementCondition = dicePlacementCondition.setIgnoreAdjacent(true);
		assertEquals(true, (boolean)dicePlacementCondition.getIgnoreAdjacent());

		dicePlacementCondition = dicePlacementCondition.setIgnoreColor(true);
		assertEquals(true, (boolean)dicePlacementCondition.getIgnoreColor());

		dicePlacementCondition = dicePlacementCondition.setIgnoreValue(true);
		assertEquals(true, (boolean)dicePlacementCondition.getIgnoreValue());

	}
}
