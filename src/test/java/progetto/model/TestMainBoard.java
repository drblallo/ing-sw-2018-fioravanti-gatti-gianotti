package progetto.model;

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

		Assert.assertEquals(1, mainBoard.getData().getCurrentFirstPlayer());

		mainBoard.setCurrentFirstPlayer(2);

		Assert.assertEquals(2, mainBoard.getData().getCurrentFirstPlayer());

	}

	@Test
	public void testSetGetCurrentPlayer()
	{
		mainBoard.setCurrentPlayer(0);

		Assert.assertEquals(0, mainBoard.getData().getCurrentPlayer());

		mainBoard.setCurrentPlayer(1);

		Assert.assertEquals(1, mainBoard.getData().getCurrentPlayer());

	}

	@Test
	public void testSetGetPlayerCount()
	{
		mainBoard.setPlayerCount(3);

		Assert.assertEquals(3, mainBoard.getData().getPlayerCount());

		mainBoard.setPlayerCount(2);

		Assert.assertEquals(2, mainBoard.getData().getPlayerCount());

	}

	@Test
	public void testSetGetCurrentRound()
	{
		mainBoard.setCurrentRound(2);

		Assert.assertEquals(2, mainBoard.getData().getCurrentRound());

		mainBoard.setCurrentRound(3);

		Assert.assertEquals(3, mainBoard.getData().getCurrentRound());

	}

	@Test
	public void testSetGetGameState()
	{
		mainBoard.setGameState(new PreGameState());

		Assert.assertEquals("Pre model", mainBoard.getData().getGameState().getName());

		mainBoard.setGameState(new StartRoundState());

		Assert.assertEquals("Start round", mainBoard.getData().getGameState().getName());

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

		assertEquals(windowFrameCouple1, mainBoard.getData().getWindowFrame(0));
		assertEquals(windowFrameCouple2, mainBoard.getData().getWindowFrame(1));
	}

	@Test
	public void testGetSetExtractedDices()
	{
		Dice dice1 = new Dice(Value.ONE, Color.YELLOW);
		mainBoard.getExtractedDices().addDice(dice1);

		Dice dice2 = new Dice(Value.TWO, Color.BLUE);
		mainBoard.getExtractedDices().addDice(dice2);

		Assert.assertEquals(dice1, mainBoard.getExtractedDices().getData().getDice(0));
		Assert.assertEquals(dice2, mainBoard.getExtractedDices().getData().getDice(1));

		Assert.assertEquals(2, mainBoard.getExtractedDices().getData().getNumberOfDices());

		Dice dice3 = new Dice(Value.THREE, Color.GREEN);

		mainBoard.getExtractedDices().changeDice(1, dice3);

		assertEquals(dice3, mainBoard.getExtractedDices().getData().getDice(1));

		assertEquals(2, mainBoard.getExtractedDices().getData().getNumberOfDices());

	}

	@Test
	public void testNCallToolCards()
	{
		mainBoard.addToolCard(new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", Color.PURPLE ,1));
		Assert.assertEquals(0, (int)mainBoard.getData().getNCallToolCard(0));

		mainBoard.incNCallToolCard(0);
		Assert.assertEquals(1, (int)mainBoard.getData().getNCallToolCard(0));
	}

	@Test
	public void testToolCards()
	{
		mainBoard.addToolCard(new ToolCard("", "", Color.YELLOW, 1));
		Assert.assertEquals(1, mainBoard.getData().getToolCards().size());

		mainBoard.removeToolCard(0);
		Assert.assertEquals(0, mainBoard.getData().getToolCards().size());

	}

	@Test
	public void testNCallToolCardFail()
	{
		mainBoard.addToolCard(new ToolCard("", "", Color.YELLOW, 1));

		mainBoard.incNCallToolCard(-1);

		Assert.assertEquals(0, (int)mainBoard.getData().getNCallToolCard(0));

		Assert.assertEquals(0, (int)mainBoard.getData().getNCallToolCard(-1));

	}

}
