package progetto.game;

import junit.framework.TestCase;
import org.json.JSONArray;

public class TestPlayerBoard extends TestCase
{

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

	public void test1()
	{

		startJA();

		PlayerBoard playerBoard = new PlayerBoard();

		WindowFrame windowFrame = new WindowFrame(ja);

		playerBoard.setWindowFrame(windowFrame);

		assertEquals(windowFrame, playerBoard.getPlayerBoardData().getWindowFrame());

		assertEquals(0, playerBoard.getNDicesPlaced());

		Dice dice = new Dice(Value.ONE, Color.YELLOW);

		playerBoard.addDiceInPlacedFrame(dice, 0, 0);

		assertEquals(dice, playerBoard.getDiceFromPlacedFrame(0, 0));

		assertEquals(dice, playerBoard.getDicePlacedFrame().getDicePlacedFrameData().getDice(0,0));

		assertEquals(1, playerBoard.getNDicesPlaced());

		playerBoard.removeDiceFromDicesPlaced(0, 0);

		assertEquals(null, playerBoard.getDiceFromPlacedFrame(0, 0));

		assertEquals(0, playerBoard.getNDicesPlaced());

		assertEquals(null, playerBoard.getDicePlacedFrame().getDicePlacedFrameData().getDice(0,0));

		assertEquals(0, playerBoard.getNPickedDices());

		playerBoard.addDiceToPickedSlot(dice);

		assertEquals(1, playerBoard.getPickedDicesSlot().getNDices());

		WindowFrameCouple windowFrameCouple = new WindowFrameCouple(ja, ja);

		playerBoard.setWindowFrame(windowFrameCouple, 0);

		assertEquals(windowFrameCouple.getWindowFrame(0), playerBoard.getPlayerBoardData().getWindowFrame());

	}

}
