package progetto.game;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMainBoard extends TestCase {

	MainBoard mainBoard;

	@Before
	public void setUp()
	{
		mainBoard = new MainBoard();
	}

	@Test
	public void testSetGetCurrentFirstPlayer()
	{
		mainBoard.setCurrentFirstPlayer(1);

		Assert.assertEquals(1, mainBoard.getMainBoardData().getCurrentFirstPlayer());

		mainBoard.setCurrentFirstPlayer(2);

		Assert.assertEquals(2, mainBoard.getMainBoardData().getCurrentFirstPlayer());

	}

	@Test
	public void testSetGetCurrentPlayer()
	{
		mainBoard.setCurrentPlayer(0);

		Assert.assertEquals(0, mainBoard.getMainBoardData().getCurrentPlayer());

		mainBoard.setCurrentPlayer(1);

		Assert.assertEquals(1, mainBoard.getMainBoardData().getCurrentPlayer());

	}

	@Test
	public void testSetGetPlayerCount()
	{
		mainBoard.setPlayerCount(3);

		Assert.assertEquals(3, mainBoard.getMainBoardData().getPlayerCount());

		mainBoard.setPlayerCount(2);

		Assert.assertEquals(2, mainBoard.getMainBoardData().getPlayerCount());

	}

	@Test
	public void testSetGetCurrentRound()
	{
		mainBoard.setCurrentRound(2);

		Assert.assertEquals(2, mainBoard.getMainBoardData().getCurrentRound());

		mainBoard.setCurrentRound(3);

		Assert.assertEquals(3, mainBoard.getMainBoardData().getCurrentRound());

	}

	@Test
	public void testSetGetGameState()
	{
		mainBoard.setGameState(new PreGameState());

		Assert.assertEquals("Pre game", mainBoard.getMainBoardData().getGameState().getName());

		mainBoard.setGameState(new StartRoundState());

		Assert.assertEquals("Start round", mainBoard.getMainBoardData().getGameState().getName());

	}

	@Test
	public void testPlayerQueue()
	{
		mainBoard.addPlayerQueue(1);
		mainBoard.addPlayerQueue(2);
		mainBoard.addPlayerQueue(3);
		mainBoard.addPlayerQueue(0);
		mainBoard.addPlayerQueue(0);
		mainBoard.addPlayerQueue(3);
		mainBoard.addPlayerQueue(2);
		mainBoard.addPlayerQueue(1);

		Assert.assertEquals(1, (int)mainBoard.getNextPlayer());
		Assert.assertEquals(2, (int)mainBoard.getNextPlayer());
		Assert.assertEquals(3, (int)mainBoard.getNextPlayer());
		Assert.assertEquals(0, (int)mainBoard.getNextPlayer());
		Assert.assertEquals(0, (int)mainBoard.getNextPlayer());
		Assert.assertEquals(3, (int)mainBoard.getNextPlayer());
		Assert.assertEquals(2, (int)mainBoard.getNextPlayer());
		Assert.assertEquals(1, (int)mainBoard.getNextPlayer());

	}

	@Test
	public void testPlayerQueueFail()
	{
		mainBoard.addPlayerQueue(0);
		mainBoard.addPlayerQueue(1);
		mainBoard.addPlayerQueue(2);
		mainBoard.addPlayerQueue(2);
		mainBoard.addPlayerQueue(1);
		mainBoard.addPlayerQueue(0);

		mainBoard.getNextPlayer();
		mainBoard.getNextPlayer();
		mainBoard.getNextPlayer();
		mainBoard.getNextPlayer();
		mainBoard.getNextPlayer();
		mainBoard.getNextPlayer();

		Assert.assertEquals(-1, (int)mainBoard.getNextPlayer());

	}

	@Test
	public void testSetGetWindowFrameCouples()
	{
		WindowFrameCoupleArray windowFrameCoupleArray = new WindowFrameCoupleArray();

		WindowFrameCouple windowFrameCouple1 = windowFrameCoupleArray.getWindowFrameCouples().get(1);
		mainBoard.addWindowFrameCouple(windowFrameCouple1);

		WindowFrameCouple windowFrameCouple2 = windowFrameCoupleArray.getWindowFrameCouples().get(1);
		mainBoard.addWindowFrameCouple(windowFrameCouple2);

		assertEquals(windowFrameCouple1, mainBoard.getMainBoardData().getWindowFrame(0));
		assertEquals(windowFrameCouple2, mainBoard.getMainBoardData().getWindowFrame(1));
	}

	@Test
	public void testGetSetExtractedDices()
	{
		Dice dice1 = new Dice(Value.ONE, Color.YELLOW);
		mainBoard.getExtractedDices().addDice(dice1);

		Dice dice2 = new Dice(Value.TWO, Color.BLUE);
		mainBoard.getExtractedDices().addDice(dice2);

		Assert.assertEquals(dice1, mainBoard.getExtractedDices().getExtractedDicesData().getDice(0));
		Assert.assertEquals(dice2, mainBoard.getExtractedDices().getExtractedDicesData().getDice(1));

		Assert.assertEquals(2, mainBoard.getExtractedDices().getExtractedDicesData().getNumberOfDices());

		Dice dice3 = new Dice(Value.THREE, Color.GREEN);

		mainBoard.getExtractedDices().changeDice(1, dice3);

		assertEquals(dice3, mainBoard.getExtractedDices().getExtractedDicesData().getDice(1));

		assertEquals(2, mainBoard.getExtractedDices().getExtractedDicesData().getNumberOfDices());

	}

}
