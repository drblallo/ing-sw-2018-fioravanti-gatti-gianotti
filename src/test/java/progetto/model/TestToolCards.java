package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.controller.*;

import java.util.ArrayList;
import java.util.List;

public class TestToolCards {

	Model game;

	@Before
	public void setUp()
	{
		game = new Model();
	}

	@Test
	public void testToolCardState()
	{
		List<Class> actionList = new ArrayList<>();
		actionList.add(ToolCardSetPickedDiceAction.class);
		actionList.add(ToolCardSetIncreaseDecreaseAction.class);
		game.getMainBoard().addToolCard(new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,1, actionList));

		AbstractGameState state = new FrameSelectionState();
		game.setState(state);
		state = new RoundState();
		game.setState(state);
		game.getPlayerBoard(0).setToken(5);
		AbstractGameAction gameAction = new UseToolCardAction(0, 0);;
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);
		game.getMainBoard().setCurrentPlayer(0);
		game.getMainBoard().setParamToolCard("nDice", 0);
		game.getMainBoard().setParamToolCard("increaseDecrease", 0);
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.YELLOW));
		gameAction = new ExecuteToolCard1Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));

	}


	@Test
	public void testToolTip()
	{
		List<Class> actionList = new ArrayList<>();
		actionList.add(ToolCardSetPickedDiceAction.class);
		actionList.add(ToolCardSetIncreaseDecreaseAction.class);
		ToolCard toolCard = new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,1, actionList);
		Assert.assertEquals("1 Viola Pinza Sgrossatrice Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", toolCard.getToolTip());

	}

	@Test
	public void testExecuteRoughingForcepsToolCardAction()
	{
		AbstractGameAction gameAction = new ExecuteToolCard1Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		List<Class> actionList = new ArrayList<>();
		actionList.add(ToolCardSetPickedDiceAction.class);
		actionList.add(ToolCardSetIncreaseDecreaseAction.class);
		ToolCard toolCard = new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,1, actionList);

		game.setState(new ToolCardState(1));

		game.getMainBoard().setCurrentPlayer(0);
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.YELLOW), false, false, false);


		AbstractGameAction supportAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertTrue(supportAction.canBeExecuted(game));
		supportAction.execute(game);

		supportAction = new ToolCardSetIncreaseDecreaseAction(0, 0);
		Assert.assertTrue(supportAction.canBeExecuted(game));
		supportAction.execute(game);

		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(Value.TWO, game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice().getValue());

	}

	@Test
	public void testExecuteRoughingForcepsToolCardActionDec()
	{
		AbstractGameAction gameAction = new ExecuteToolCard1Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		List<Class> actionList = new ArrayList<>();
		actionList.add(ToolCardSetPickedDiceAction.class);
		actionList.add(ToolCardSetIncreaseDecreaseAction.class);
		ToolCard toolCard = new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,1, actionList);

		game.setState(new ToolCardState(1));

		game.getMainBoard().setCurrentPlayer(0);
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.YELLOW));

		AbstractGameAction supportAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertTrue(supportAction.canBeExecuted(game));
		supportAction.execute(game);

		supportAction = new ToolCardSetIncreaseDecreaseAction(0, 2);
		Assert.assertFalse(supportAction.canBeExecuted(game));

		supportAction = new ToolCardSetIncreaseDecreaseAction(0, 1);
		Assert.assertTrue(supportAction.canBeExecuted(game));
		supportAction.execute(game);

		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(Value.ONE, game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice().getValue());

	}

	@Test
	public void testExecuteRoughingForcepsToolCardActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard1Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ExecuteToolCard1Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(0));

		game.getMainBoard().setCurrentPlayer(0);

		AbstractGameAction supportAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertFalse(supportAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(1);

		supportAction = new ToolCardSetIncreaseDecreaseAction(0, 0);
		Assert.assertFalse(supportAction.canBeExecuted(game));

		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	@Test
	public void testToolCardSelectDiceRoundTrackAction()
	{
		AbstractGameAction gameAction = new ToolCardSetDiceRoundTrackAction();

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 0, 0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(5));

		game.getMainBoard().setCurrentPlayer(0);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 0);

		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(0, (int)game.getMainBoard().getData().getParamToolCard().get("round"));
		Assert.assertEquals(0, (int)game.getMainBoard().getData().getParamToolCard().get("nDiceRT"));

	}

	@Test
	public void testToolCardSelectDiceRoundTrackActionFail()
	{
		AbstractGameAction gameAction = new ToolCardSetDiceRoundTrackAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 1, 0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(0));

		game.getMainBoard().setCurrentPlayer(0);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 0);

		Assert.assertFalse(gameAction.canBeExecuted(game));

		Assert.assertFalse(game.getMainBoard().getData().getParamToolCard().containsKey("round"));
		Assert.assertFalse(game.getMainBoard().getData().getParamToolCard().containsKey("nDiceRT"));

	}

	@Test
	public void testToolCardSelectDiceValueAction()
	{
		List<Class> actionList = new ArrayList<>();
		actionList.add(ToolCardSetPickedDiceAction.class);
		actionList.add(ToolCardSetIncreaseDecreaseAction.class);
		game.getMainBoard().addToolCard(new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,11, actionList));
		game.getMainBoard().setCurrentPlayer(0);
		game.getMainBoard().setPlayerCount(1);
		game.getMainBoard().setGameState(new RoundState());
		AbstractGameAction gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetDiceValueAction(0, 1);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(1, (int)game.getMainBoard().getData().getParamToolCard().get("value"));

	}

	@Test
	public void testToolCardSelectDiceValueActionValue()
	{
		List<Class> actionList = new ArrayList<>();
		actionList.add(ToolCardSetPickedDiceAction.class);
		actionList.add(ToolCardSetIncreaseDecreaseAction.class);
		game.getMainBoard().addToolCard(new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,11, actionList));
		game.getMainBoard().setCurrentPlayer(0);
		game.getMainBoard().setPlayerCount(1);
		game.getMainBoard().setGameState(new RoundState());
		AbstractGameAction gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetDiceValueAction(0, Value.ONE);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(1, (int)game.getMainBoard().getData().getParamToolCard().get("value"));

	}

	@Test
	public void testToolCardSelectDiceValueActionFail()
	{
		AbstractGameAction gameAction = new ToolCardSetDiceValueAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetDiceValueAction(0, 1);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(0));
		game.getMainBoard().setCurrentPlayer(1);

		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new RoundState());

		Assert.assertFalse(gameAction.canBeExecuted(game));

		Assert.assertFalse(game.getMainBoard().getData().getParamToolCard().containsKey("value"));

	}

	@Test
	public void testToolCardSelectPlacedDiceAction()
	{
		game.setState(new ToolCardState(2));
		game.getMainBoard().setCurrentPlayer(0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);

		AbstractGameAction gameAction = new ToolCardSetPlacedDiceAction(0, 0 ,0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(0, (int)game.getMainBoard().getData().getParamToolCard().get("YPlacedDice"));
		Assert.assertEquals(0, (int)game.getMainBoard().getData().getParamToolCard().get("XPlacedDice"));

	}

	@Test
	public void testToolCardSelectPlacedDiceActionFail()
	{
		AbstractGameAction gameAction = new ToolCardSetPlacedDiceAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPlacedDiceAction(0, 0 ,0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(0));
		game.getMainBoard().setCurrentPlayer(0);

		Assert.assertFalse(gameAction.canBeExecuted(game));

		Assert.assertFalse(game.getMainBoard().getData().getParamToolCard().containsKey("YPlacedDice"));
		Assert.assertFalse(game.getMainBoard().getData().getParamToolCard().containsKey("XPlacedDice"));

	}

	@Test
	public void testToolCardSetPickedDiceAction()
	{
		AbstractGameAction gameAction = new ToolCardSetPickedDiceAction(0, 0);

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(1));
		game.getMainBoard().setCurrentPlayer(0);
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.YELLOW), false, false, false);

		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(0, (int)game.getMainBoard().getData().getParamToolCard().get("nDice"));

	}

	@Test
	public void testToolCardSetPickedDiceActionFail()
	{
		AbstractGameAction gameAction = new ToolCardSetPickedDiceAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(0));
		game.getMainBoard().setCurrentPlayer(1);
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.YELLOW), false, false, false);

		Assert.assertFalse(gameAction.canBeExecuted(game));

		Assert.assertFalse(game.getMainBoard().getData().getParamToolCard().containsKey("nDice"));

	}

	@Test
	public void testUseToolCardAction()
	{
		AbstractGameAction gameAction = new UseToolCardAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.getMainBoard().setCurrentPlayer(0);
		game.getPlayerBoard(0).setToken(5);
		gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(ToolCardState.class, game.getMainBoard().getData().getGameState().getClass());


	}

	@Test
	public void testToolCardSetIncreaseDecreaseActionFail()
	{
		AbstractGameAction gameAction = new ToolCardSetIncreaseDecreaseAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	@Test
	public void testCardIndexColorToolTip()
	{
		game.setState(new FrameSelectionState());
		game.setState(new RoundState());
		game.getPlayerBoard(0).setToken(5);
		AbstractGameAction gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertTrue(game.getMainBoard().getData().getGameState() instanceof ToolCardState);

		ToolCardState toolCardState = (ToolCardState) game.getMainBoard().getData().getGameState();

		Assert.assertEquals(10, toolCardState.getIndex());

		Assert.assertEquals(10 ,game.getMainBoard().getData().getToolCards().get(0).getIndex());
		Assert.assertEquals(GameColor.GREEN ,game.getMainBoard().getData().getToolCards().get(0).getGameColor());

		Assert.assertEquals("10 Verde Tampone Diamantato Dopo aver scelto un dado, giralo sulla faccia opposta" ,game.getMainBoard().getData().getToolCards().get(0).getToolTip());

	}

	@Test
	public void testCancelToolCardUseAction()
	{
		AbstractGameAction gameAction = new CancelToolCardUseAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(1));
		game.getMainBoard().setParamToolCard("Test", 1);

		gameAction = new CancelToolCardUseAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

	}

	@Test
	public void testExecuteToolCard11Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard11Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(11));
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.TWO, GameColor.RED));

		gameAction = new ToolCardSetPickedDiceAction(0,0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard11Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Dice dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice();
		Assert.assertEquals(GameColor.BLUE, dice.getGameColor());

		Assert.assertEquals(ToolCardState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertFalse(game.getMainBoard().getData().getParamToolCard().isEmpty());

		gameAction = new ToolCardSetDiceValueAction(0, 3);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

		dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice();
		Assert.assertEquals(GameColor.BLUE, dice.getGameColor());
		Assert.assertEquals(Value.THREE, dice.getValue());

	}

	@Test
	public void testExecuteToolCard11ActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard11Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(11));
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.TWO, GameColor.RED));

		gameAction = new ExecuteToolCard11Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPickedDiceAction(0,5);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPickedDiceAction(0,0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard11Action(1);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new RoundState());

		gameAction = new ExecuteToolCard11Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	@Test
	public void testExecuteToolCard12Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard12Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(12));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.YELLOW), 0, 1);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 0);

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard12Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 1);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard12Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Dice dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.ONE, dice.getValue());

		dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(1).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.TWO, dice.getValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

	}



	@Test
	public void testExecuteToolCard12ActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard12Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(12));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.YELLOW), 0, 1);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 0);

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard12Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 1);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard12Action(1);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new RoundState());

		gameAction = new ExecuteToolCard12Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	@Test
	public void testExecuteToolCard12ActionDifferentColorFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard12Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(12));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.RED), 0, 1);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 0);

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard12Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 1);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard12Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	@Test
	public void testExecuteToolCard12ActionDifferentColor2Fail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard12Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(12));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.RED), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.YELLOW), 0, 1);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 0);

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard12Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 1);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard12Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}



	@Test
	public void testExecuteToolCard2ActionAction()
	{
		AbstractGameAction gameAction = new ExecuteToolCard2Or3Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(2));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);


		gameAction = new ExecuteToolCard2Or3Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0);
		Assert.assertEquals(GameColor.YELLOW, dicePlacementCondition.getDice().getGameColor());
		Assert.assertEquals(Value.ONE, dicePlacementCondition.getDice().getValue());
		Assert.assertTrue(dicePlacementCondition.getIgnoreColor());
		Assert.assertFalse(dicePlacementCondition.getIgnoreAdjacent());
		Assert.assertFalse(dicePlacementCondition.getIgnoreValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

	}

	@Test
	public void testExecuteToolCard3ActionAction()
	{
		AbstractGameAction gameAction = new ExecuteToolCard2Or3Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(3));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);


		gameAction = new ExecuteToolCard2Or3Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0);
		Assert.assertEquals(GameColor.YELLOW, dicePlacementCondition.getDice().getGameColor());
		Assert.assertEquals(Value.ONE, dicePlacementCondition.getDice().getValue());
		Assert.assertFalse(dicePlacementCondition.getIgnoreColor());
		Assert.assertFalse(dicePlacementCondition.getIgnoreAdjacent());
		Assert.assertTrue(dicePlacementCondition.getIgnoreValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

	}

	@Test
	public void testExecuteToolCard9ActionAction()
	{
		AbstractGameAction gameAction = new ExecuteToolCard9Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(9));

		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.YELLOW));

		Assert.assertFalse(game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getIgnoreAdjacent());

		gameAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard9Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0);
		Assert.assertEquals(GameColor.YELLOW, dicePlacementCondition.getDice().getGameColor());
		Assert.assertEquals(Value.ONE, dicePlacementCondition.getDice().getValue());
		Assert.assertFalse(dicePlacementCondition.getIgnoreColor());
		Assert.assertTrue(dicePlacementCondition.getIgnoreAdjacent());
		Assert.assertFalse(dicePlacementCondition.getIgnoreValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

	}

	@Test
	public void testExecuteToolCard9ActionActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard9Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(9));

		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.YELLOW));

		Assert.assertFalse(game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getIgnoreAdjacent());

		gameAction = new ExecuteToolCard9Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard9Action(1);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new RoundState());

		gameAction = new ExecuteToolCard9Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	@Test
	public void testExecuteToolCard4Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard4Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(4));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.YELLOW), 0, 1);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 1);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard4Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Dice dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.ONE, dice.getValue());

		dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(1).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.TWO, dice.getValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

	}

	@Test
	public void testExecuteToolCard4ActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard4Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(4));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.YELLOW), 0, 1);

		gameAction = new ExecuteToolCard4Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard4Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 1);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard4Action(1);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new ToolCardState(1));

		gameAction = new ExecuteToolCard4Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new RoundState());

		gameAction = new ExecuteToolCard4Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	@Test
	public void testExecuteToolCard5Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard5Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(5));

		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.RED));
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 0);

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard5Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Dice dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.THREE, dice.getValue());

		dice = game.getRoundTrack().getData().getDice(0, 0);
		Assert.assertEquals(GameColor.RED, dice.getGameColor());
		Assert.assertEquals(Value.ONE, dice.getValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

	}

	@Test
	public void testExecuteToolCard5ActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard5Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(5));

		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.RED));
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 0);

		gameAction = new ExecuteToolCard5Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 2, 2);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard5Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard5Action(1);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new RoundState());

		gameAction = new ExecuteToolCard5Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	@Test
	public void testExecuteToolCard6Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard6Or10Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(6));

		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.FIVE, GameColor.YELLOW));

		gameAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard6Or10Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Dice dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.ONE, dice.getValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

	}

	@Test
	public void testExecuteToolCard10Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard6Or10Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(10));

		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.FIVE, GameColor.YELLOW));

		gameAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard6Or10Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Dice dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.TWO, dice.getValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

	}

	@Test
	public void testExecuteToolCard7Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard7Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(7));

		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.FIVE, GameColor.YELLOW));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.TWO, GameColor.RED));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.THREE, GameColor.BLUE));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.FOUR, GameColor.GREEN));

		gameAction = new ExecuteToolCard7Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Dice dice = game.getMainBoard().getExtractedDices().getData().getDice(0);
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.ONE, dice.getValue());

		dice = game.getMainBoard().getExtractedDices().getData().getDice(1);
		Assert.assertEquals(GameColor.RED, dice.getGameColor());
		Assert.assertEquals(Value.FIVE, dice.getValue());

		dice = game.getMainBoard().getExtractedDices().getData().getDice(2);
		Assert.assertEquals(GameColor.BLUE, dice.getGameColor());
		Assert.assertEquals(Value.TWO, dice.getValue());

		dice = game.getMainBoard().getExtractedDices().getData().getDice(3);
		Assert.assertEquals(GameColor.GREEN, dice.getGameColor());
		Assert.assertEquals(Value.SIX, dice.getValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

	}

	@Test
	public void testExecuteToolCard8Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard8Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(8));

		game.getMainBoard().addPlayerQueue(1);
		game.getMainBoard().addPlayerQueue(2);
		game.getMainBoard().addPlayerQueue(3);
		game.getMainBoard().addPlayerQueue(4);
		game.getMainBoard().addPlayerQueue(0);

		gameAction = new ExecuteToolCard8Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);


		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());


		Assert.assertEquals(0, (int)game.getMainBoard().getData().getNextPlayer());
		game.getMainBoard().removeNextPlayer();
		Assert.assertEquals(1, (int)game.getMainBoard().getData().getNextPlayer());
		game.getMainBoard().removeNextPlayer();
		Assert.assertEquals(2, (int)game.getMainBoard().getData().getNextPlayer());
		game.getMainBoard().removeNextPlayer();
		Assert.assertEquals(3, (int)game.getMainBoard().getData().getNextPlayer());
		game.getMainBoard().removeNextPlayer();
		Assert.assertEquals(4, (int)game.getMainBoard().getData().getNextPlayer());
		game.getMainBoard().removeNextPlayer();
		Assert.assertEquals(-1, (int)game.getMainBoard().getData().getNextPlayer());

	}

	@Test
	public void testExecuteToolCardAction()
	{
		AbstractGameAction gameAction = new ExecuteToolCardAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.getPlayerBoard(0).setToken(5);
		game.setState(new RoundState());

		List<Class> actionList = new ArrayList<>();
		game.getMainBoard().addToolCard(new ToolCard("", "", GameColor.YELLOW, 12, actionList));

		gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.YELLOW), 0, 1);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 0);

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 1);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCardAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Dice dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.ONE, dice.getValue());

		dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(1).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.TWO, dice.getValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

		Assert.assertEquals(4, game.getPlayerBoard(0).getData().getToken());

	}

	@Test
	public void testUseToolCardAction2TokenAsked()
	{
		AbstractGameAction gameAction = new ExecuteToolCardAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.getPlayerBoard(0).setToken(5);
		game.setState(new RoundState());

		List<Class> actionList = new ArrayList<>();
		game.getMainBoard().addToolCard(new ToolCard("", "", GameColor.YELLOW, 12, actionList));

		game.getMainBoard().incNCallToolCard(0);

		gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

	}

	@Test
	public void testSinglePlayer()
	{
		game.getMainBoard().setPlayerCount(1);

		game.getMainBoard().setCurrentPlayer(0);

		game.getPlayerBoard(0).setToken(0);

		game.setState(new RoundState());

		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.ONE, GameColor.YELLOW));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.TWO, GameColor.RED));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.THREE, GameColor.BLUE));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.FOUR, GameColor.GREEN));

		List<Class> actionList = new ArrayList<>();
		game.getMainBoard().addToolCard(new ToolCard("", "", GameColor.YELLOW, 12, actionList));

		AbstractGameAction gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetSinglePlayerDiceAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.YELLOW), 0, 1);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 0);

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 1);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCardAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Dice dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.ONE, dice.getValue());

		dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(1).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.TWO, dice.getValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertTrue(game.getMainBoard().getData().getParamToolCard().isEmpty());

		Assert.assertEquals(0, game.getPlayerBoard(0).getData().getToken());

		Assert.assertEquals(3, game.getMainBoard().getExtractedDices().getData().getNumberOfDices());

		Assert.assertEquals(Value.TWO, game.getMainBoard().getExtractedDices().getData().getDice(0).getValue());
		Assert.assertEquals(GameColor.RED, game.getMainBoard().getExtractedDices().getData().getDice(0).getGameColor());

	}


	@Test
	public void testSinglePlayerFail()
	{
		game.getMainBoard().setPlayerCount(1);

		game.getMainBoard().setCurrentPlayer(0);

		game.getPlayerBoard(0).setToken(0);

		game.setState(new RoundState());

		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.ONE, GameColor.YELLOW));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.TWO, GameColor.RED));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.THREE, GameColor.BLUE));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.FOUR, GameColor.GREEN));

		List<Class> actionList = new ArrayList<>();
		game.getMainBoard().addToolCard(new ToolCard("", "", GameColor.YELLOW, 12, actionList));

		AbstractGameAction gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetSinglePlayerDiceAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetSinglePlayerDiceAction(0, 5);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetSinglePlayerDiceAction(0, -1);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetSinglePlayerDiceAction(1, 2);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setPlayerCount(2);
		gameAction = new ToolCardSetSinglePlayerDiceAction(0, 0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	@Test
	public void testExecuteToolCard7ActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard7Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getMainBoard().setCurrentPlayer(0);
		game.setState(new ToolCardState(7));

		game.getMainBoard().addPlayerQueue(1);
		game.getMainBoard().addPlayerQueue(2);
		game.getMainBoard().addPlayerQueue(2);
		game.getMainBoard().addPlayerQueue(1);
		game.getMainBoard().addPlayerQueue(0);

		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.FIVE, GameColor.YELLOW));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.TWO, GameColor.RED));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.THREE, GameColor.BLUE));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.FOUR, GameColor.GREEN));

		gameAction = new ExecuteToolCard7Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	@Test
	public void testParamToolCard()
	{
		game.getMainBoard().setParamToolCard("a", 1);

		Assert.assertEquals(1, (int)game.getMainBoard().getData().getParamToolCard().get("a"));

		game.getMainBoard().setParamToolCard("a", 2);

		Assert.assertEquals(2, (int)game.getMainBoard().getData().getParamToolCard().get("a"));
		Assert.assertEquals(1, game.getMainBoard().getData().getParamToolCard().size());

	}


}