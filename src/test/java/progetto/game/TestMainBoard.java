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

		assertEquals(windowFrameCouple1, mainBoard.getMainBoardData().getWindowFrame(0));
		assertEquals(windowFrameCouple2, mainBoard.getMainBoardData().getWindowFrame(1));

		Dice dice1 = new Dice(Value.ONE, Color.YELLOW);
		mainBoard.getExtractedDices().addDice(dice1);

		Dice dice2 = new Dice(Value.TWO, Color.BLUE);
		mainBoard.getExtractedDices().addDice(dice2);

		assertEquals(dice1, mainBoard.getExtractedDices().getExtractedDicesData().getDice(0));
		assertEquals(dice2, mainBoard.getExtractedDices().getExtractedDicesData().getDice(1));

		assertEquals(dice1, mainBoard.getExtractedDices().getExtractedDicesData().getDice(0));
		assertEquals(dice2, mainBoard.getExtractedDices().getExtractedDicesData().getDice(1));

		assertEquals(2, mainBoard.getExtractedDices().getExtractedDicesData().getNumberOfDices());

		Dice dice3 = new Dice(Value.THREE, Color.GREEN);

		mainBoard.getExtractedDices().changeDice(1, dice3);

		assertEquals(dice3, mainBoard.getExtractedDices().getExtractedDicesData().getDice(1));

		assertEquals(2, mainBoard.getExtractedDices().getExtractedDicesData().getNumberOfDices());

		assertEquals(4, mainBoard.getMainBoardData().getPlayerCount());
		mainBoard.setPlayerCount(5);
		assertEquals(5, mainBoard.getMainBoardData().getPlayerCount());

		AbstractGameState state;
		state = new PreGameState();

		assertEquals(true, state.getName().equals(mainBoard.getMainBoardData().getGameState().getName()));

		mainBoard.setGameState(state);

		assertEquals(state, mainBoard.getMainBoardData().getGameState());

	}

}
