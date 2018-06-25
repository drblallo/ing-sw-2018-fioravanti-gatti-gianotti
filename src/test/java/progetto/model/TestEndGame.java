package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestEndGame {

	Model game;

	@Before
	public void setUp()
	{
		game = new Model();
	}

	@Test
	public void testEvaluateTargetScore()
	{
		game.getMainBoard().setPlayerCount(1);

		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 0);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.RED), 0);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 0);

		game.setState(new EndGameState());

		Assert.assertEquals(3, game.getMainBoard().getData().getSinglePlayerTarget());

		game.getRoundTrack().add(new Dice(Value.FOUR, GameColor.GREEN), 4);

		game.setState(new EndGameState());

		Assert.assertEquals(7, game.getMainBoard().getData().getSinglePlayerTarget());

		game.getRoundTrack().add(new Dice(Value.FOUR, GameColor.YELLOW), 4);
		game.getRoundTrack().add(new Dice(Value.SIX, GameColor.BLUE), 3);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 9);
		game.getRoundTrack().add(new Dice(Value.TWO, GameColor.RED), 1);
		game.getRoundTrack().add(new Dice(Value.FIVE, GameColor.YELLOW), 1);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.PURPLE), 1);

		game.setState(new EndGameState());

		Assert.assertEquals(30, game.getMainBoard().getData().getSinglePlayerTarget());

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

		game.setState(new EndGameState());

		Assert.assertEquals(-14, game.getPlayerBoard(0).getData().getScore());

	}

	@Test
	public void testCardID()
	{
		game.getMainBoard().addPublicObjectiveCards(new ColoredDiagonalsPublicObjectiveCard());
		game.getMainBoard().addPublicObjectiveCards(new ColumnsDifferentColorsPublicObjectiveCard());

		game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.YELLOW));
		game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(GameColor.GREEN));


		Assert.assertEquals(0, game.getPlayerBoard(0).getData().getPrivateObjectiveCard().get(0).getCardID());
		Assert.assertEquals(3, game.getPlayerBoard(0).getData().getPrivateObjectiveCard().get(1).getCardID());

		Assert.assertEquals(8, game.getMainBoard().getData().getPublicObjectiveCards().get(0).getCardID());
		Assert.assertEquals(1, game.getMainBoard().getData().getPublicObjectiveCards().get(1).getCardID());

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

		game.setState(new EndGameState());
		Assert.assertEquals(8, game.getPlayerBoard(0).getData().getScore());

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

		game.setState(new EndGameState());
		Assert.assertTrue(game.getPlayerBoard(1).getData().getScore()>game.getPlayerBoard(0).getData().getScore());

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

		game.setState(new EndGameState());
		Assert.assertFalse(game.getPlayerBoard(0).getData().getScore()>game.getMainBoard().getData().getSinglePlayerTarget());

	}


	@Test
	public void testEvaluateTargetScoreFail()
	{
		game.getMainBoard().setPlayerCount(4);
		game.setState(new EndGameState());
		Assert.assertEquals(0, game.getMainBoard().getData().getSinglePlayerTarget());

	}

	@Test
	public void testEvaluateSinglePlayerFail()
	{
		game.getMainBoard().setPlayerCount(1);
		game.setState(new EndGameState());
		Assert.assertEquals(0, game.getPlayerBoard(0).getData().getScore());

	}

	@Test
	public void testEvaluateMultiPlayerFail()
	{
		game.getMainBoard().setPlayerCount(4);
		game.setState(new EndGameState());
		Assert.assertEquals(0, game.getPlayerBoard(0).getData().getScore());

	}

}
