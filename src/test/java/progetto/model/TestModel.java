package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.controller.GameController;

public class TestModel extends TestCase {

	Model game;

	@Before
	public void setUp()
	{
		game = new Model();
	}

	@Test
	public void testGetter()
	{

		AbstractProcessor commandQueue = game.getCommandQueue();

		RoundTrack roundTrack = game.getRoundTrack();

		PlayerBoard playerBoard0 = game.getPlayerBoard(0);
		PlayerBoard playerBoard1 = game.getPlayerBoard(1);

		MainBoard mainBoard = game.getMainBoard();

		DiceBag diceBag = game.getDiceBag();

		assertEquals(0, game.getSeed());

	}

	@Test
	public void testSeed()
	{
		Assert.assertEquals(0, game.getSeed());

		game.setSeed(12121212);
		Assert.assertEquals(12121212, game.getSeed());
	}

	@Test
	public void testGameEquality()
	{
		GameController g = new GameController();
		GameController g2 = new GameController();

		Assert.assertFalse(g.equals(null));
		Assert.assertFalse(g.equals(this));
		Assert.assertTrue(g.equals(g2));
	}

	@Test
	public void testEvaluateTargetScore()
	{
		game.getMainBoard().setPlayerCount(1);

		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 0);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.RED), 0);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 0);

		Assert.assertEquals(3, game.evaluateTargetScore());

		game.getRoundTrack().add(new Dice(Value.FOUR, GameColor.GREEN), 4);

		Assert.assertEquals(7, game.evaluateTargetScore());

		game.getRoundTrack().add(new Dice(Value.FOUR, GameColor.YELLOW), 4);
		game.getRoundTrack().add(new Dice(Value.SIX, GameColor.BLUE), 3);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 9);
		game.getRoundTrack().add(new Dice(Value.TWO, GameColor.RED), 1);
		game.getRoundTrack().add(new Dice(Value.FIVE, GameColor.YELLOW), 1);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.PURPLE), 1);

		Assert.assertEquals(30, game.evaluateTargetScore());

	}

	@Test
	public void testEvaluatePlayerFrameSinglePlayer()
	{
		game.getMainBoard().setPlayerCount(1);

		game.getMainBoard().addPublicObjectiveCards(new ColoredDiagonalsPublicObjectiveCard());
		game.getMainBoard().addPublicObjectiveCards(new ColumnsDifferentColorsPublicObjectiveCard());

		game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.YELLOW));
		game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.GREEN));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.RED), 0, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 1 ,1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.GREEN), 1, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.YELLOW), 1, 4);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.SIX, GameColor.BLUE), 2, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.YELLOW), 3, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.RED), 3, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FIVE, GameColor.YELLOW), 3, 2);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.PURPLE), 2, 2);

		Assert.assertEquals(-14, game.evaluatePlayerFrame(0));

	}

	@Test
	public void testEvaluatePlayerFrameMultiPlayer()
	{
		game.getMainBoard().setPlayerCount(4);

		game.getMainBoard().addPublicObjectiveCards(new ColoredDiagonalsPublicObjectiveCard());
		game.getMainBoard().addPublicObjectiveCards(new ColumnsDifferentColorsPublicObjectiveCard());
		game.getMainBoard().addPublicObjectiveCards(new ClearShadesPublicObjectiveCard());

		game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.YELLOW));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.RED), 0, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 1 ,1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.GREEN), 1, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.YELLOW), 1, 4);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.SIX, GameColor.BLUE), 2, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.YELLOW), 3, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.RED), 3, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FIVE, GameColor.YELLOW), 3, 2);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.PURPLE), 2, 2);

		Assert.assertEquals(8, game.evaluatePlayerFrame(0));

	}


	@Test
	public void testWinnerMultiPlayer()
	{
		game.getMainBoard().setPlayerCount(2);

		game.getMainBoard().addPublicObjectiveCards(new ColoredDiagonalsPublicObjectiveCard());
		game.getMainBoard().addPublicObjectiveCards(new ColumnsDifferentColorsPublicObjectiveCard());
		game.getMainBoard().addPublicObjectiveCards(new ClearShadesPublicObjectiveCard());

		game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.GREEN));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.RED), 0, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 1 ,1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.GREEN), 1, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.YELLOW), 1, 4);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.SIX, GameColor.BLUE), 2, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.YELLOW), 3, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.RED), 3, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FIVE, GameColor.YELLOW), 3, 2);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.PURPLE), 2, 2);

		game.getPlayerBoard(1).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.YELLOW));

		game.getPlayerBoard(1).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(1).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.RED), 0, 1);
		game.getPlayerBoard(1).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 1 ,1);
		game.getPlayerBoard(1).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.GREEN), 1, 3);
		game.getPlayerBoard(1).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.YELLOW), 1, 4);
		game.getPlayerBoard(1).addDiceInPlacedFrame(new Dice(Value.SIX, GameColor.BLUE), 2, 1);
		game.getPlayerBoard(1).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.YELLOW), 3, 1);
		game.getPlayerBoard(1).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.RED), 3, 3);
		game.getPlayerBoard(1).addDiceInPlacedFrame(new Dice(Value.FIVE, GameColor.YELLOW), 3, 2);
		game.getPlayerBoard(1).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.PURPLE), 2, 2);

		Assert.assertEquals(1, game.getWinner());

	}


	@Test
	public void testWinnerSinglePlayer()
	{
		game.getMainBoard().setPlayerCount(1);

		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 0);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.RED), 0);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 0);
		game.getRoundTrack().add(new Dice(Value.FOUR, GameColor.GREEN), 4);
		game.getRoundTrack().add(new Dice(Value.FOUR, GameColor.YELLOW), 4);
		game.getRoundTrack().add(new Dice(Value.SIX, GameColor.BLUE), 3);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 9);
		game.getRoundTrack().add(new Dice(Value.TWO, GameColor.RED), 1);
		game.getRoundTrack().add(new Dice(Value.FIVE, GameColor.YELLOW), 1);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.PURPLE), 1);


		game.getMainBoard().addPublicObjectiveCards(new ColoredDiagonalsPublicObjectiveCard());
		game.getMainBoard().addPublicObjectiveCards(new ColumnsDifferentColorsPublicObjectiveCard());

		game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.YELLOW));
		game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.GREEN));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.RED), 0, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 1 ,1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.GREEN), 1, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.YELLOW), 1, 4);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.SIX, GameColor.BLUE), 2, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.YELLOW), 3, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.RED), 3, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FIVE, GameColor.YELLOW), 3, 2);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.PURPLE), 2, 2);

		Assert.assertEquals(-1, game.getWinner());

	}


	@Test
	public void testEvaluatePlayerFrameFail()
	{
		Assert.assertEquals(0, game.evaluatePlayerFrame(10));

	}

	@Test
	public void testEvaluateTargetScoreFail()
	{
		game.getMainBoard().setPlayerCount(4);
		Assert.assertEquals(0, game.evaluateTargetScore());

	}

	@Test
	public void testEvaluateSinglePlayerFail()
	{
		game.getMainBoard().setPlayerCount(1);
		Assert.assertEquals(0, game.evaluatePlayerFrame(0));

	}

	@Test
	public void testEvaluateMultiPlayerFail()
	{
		game.getMainBoard().setPlayerCount(4);
		Assert.assertEquals(0, game.evaluatePlayerFrame(0));

	}

	@Test
	public void testGetWinnerSinglePlayer()
	{
		game.getMainBoard().setPlayerCount(1);

		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 0);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.RED), 5);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 8);

		game.getMainBoard().addPublicObjectiveCards(new ColoredDiagonalsPublicObjectiveCard());
		game.getMainBoard().addPublicObjectiveCards(new ColumnsDifferentColorsPublicObjectiveCard());

		game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.YELLOW));
		game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.GREEN));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.RED), 0, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.PURPLE), 0, 2);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.PURPLE), 0, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.PURPLE), 0, 4);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 1 ,0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 1 ,1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 1 ,2);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.GREEN), 1, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FOUR, GameColor.YELLOW), 1, 4);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.SIX, GameColor.BLUE), 2, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.SIX, GameColor.BLUE), 2, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.SIX, GameColor.BLUE), 2, 2);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.SIX, GameColor.BLUE), 2, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.SIX, GameColor.BLUE), 2, 4);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.THREE, GameColor.YELLOW), 3, 1);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.RED), 3, 3);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.FIVE, GameColor.YELLOW), 3, 2);

		Assert.assertEquals(0, game.getWinner());
	}



}
