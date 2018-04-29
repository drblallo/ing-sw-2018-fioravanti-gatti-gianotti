package progetto.game;

import junit.framework.TestCase;
import org.json.JSONArray;

public class TestMainBoard extends TestCase {

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

	JSONArray jb;

	public void startJB() {
		jb = new JSONArray(); jb.put("Via Lux"); jb.put(4); jb.put(7); jb.put(2); jb.put(0);
		jb.put(Value.SIX); jb.put(1); jb.put(1); jb.put(Value.ONE); jb.put(2); jb.put(1);
		jb.put(Value.FIVE); jb.put(4); jb.put(1); jb.put(Value.TWO); jb.put(0); jb.put(2);
		jb.put(Value.THREE); jb.put(2); jb.put(3); jb.put(Value.FOUR); jb.put(3); jb.put(3);
		jb.put(Value.THREE); jb.put(5); jb.put(0); jb.put(0); jb.put(Color.YELLOW); jb.put(1);
		jb.put(2); jb.put(Color.YELLOW); jb.put(2); jb.put(2); jb.put(Color.RED); jb.put(3);
		jb.put(2); jb.put(Color.PURPLE); jb.put(4); jb.put(3); jb.put(Color.RED);
	}

	public void test1()
	{

		startJA();
		startJB();

		MainBoard mainBoard = new MainBoard();

		WindowFrameCouple windowFrameCouple1 = new WindowFrameCouple(ja, jb);
		mainBoard.addWindowFrameCouple(windowFrameCouple1);

		WindowFrameCouple windowFrameCouple2 = new WindowFrameCouple(ja, ja);
		mainBoard.addWindowFrameCouple(windowFrameCouple2);

		assertEquals(windowFrameCouple1, mainBoard.getWindowFrame(0));
		assertEquals(windowFrameCouple2, mainBoard.getWindowFrame(1));

		Dice dice1 = new Dice(Value.ONE, Color.YELLOW);
		mainBoard.addDiceExtracted(dice1);

		Dice dice2 = new Dice(Value.TWO, Color.BLUE);
		mainBoard.addDiceExtracted(dice2);

		assertEquals(dice1, mainBoard.getDiceExtracted(0));
		assertEquals(dice2, mainBoard.getDiceExtracted(1));

		assertEquals(dice1, mainBoard.getExtractedDices().getDice(0));
		assertEquals(dice2, mainBoard.getExtractedDices().getDice(1));

		assertEquals(2, mainBoard.getNExtractedDices());

		Dice dice3 = new Dice(Value.THREE, Color.GREEN);

		mainBoard.changeExtracredDice(1, dice3);

		assertEquals(dice3, mainBoard.getDiceExtracted(1));

		assertEquals(2, mainBoard.getNExtractedDices());

	}

}
