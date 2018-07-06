package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.controller.*;
import progetto.view.ToolCardActionList;

import java.util.List;

/**
 * Test tool cards
 */
public class TestToolCards {

	Model game;

	@Before
	public void setUp()
	{
		game = new Model();
	}

	/**
	 * Test toolCardState - fail - wrong caller
	 */
	@Test
	public void testToolCardStateFailWrongCaller()
	{
		game.getMainBoard().addToolCard(new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,1));

		AbstractGameState state = new FrameSelectionState();
		game.setState(state);
		state = new RoundState();
		game.setState(state);
		game.getRoundInformation().setCurrentPlayer(1);
		game.getPlayerBoard(0).setToken(5);
		AbstractGameAction gameAction = new UseToolCardAction(0, 0);;
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	/**
	 * Test toolCardState
	 */
	@Test
	public void testToolCardState()
	{
		game.getMainBoard().addToolCard(new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,1));

		AbstractGameState state = new FrameSelectionState();
		game.setState(state);
		state = new RoundState();
		game.setState(state);
		game.getRoundInformation().setCurrentPlayer(0);
		game.getPlayerBoard(0).setToken(5);
		AbstractGameAction gameAction = new UseToolCardAction(0, 0);;
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);
		game.getRoundInformation().setNDice(0);
		game.getRoundInformation().setIncreaseDecrease(0);
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.YELLOW));
		gameAction = new ExecuteToolCard1Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));

	}


	/**
	 * Test get toolTip
	 */
	@Test
	public void testToolTip()
	{
		ToolCard toolCard = new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,1);
		Assert.assertEquals("1 Viola Pinza Sgrossatrice Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", toolCard.getToolTip());

	}

	/**
	 * Test execute tool card RoughingForceps Action - increase
	 */
	@Test
	public void testExecuteRoughingForcepsToolCardAction()
	{
		AbstractGameAction gameAction = new ExecuteToolCard1Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		ToolCard toolCard = new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,1);

		game.setState(new ToolCardState(1));

		game.getRoundInformation().setCurrentPlayer(0);
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

	/**
	 * Test execute tool card RoughingForceps Action - decrease
	 */
	@Test
	public void testExecuteRoughingForcepsToolCardActionDec()
	{
		AbstractGameAction gameAction = new ExecuteToolCard1Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		ToolCard toolCard = new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,1);

		game.setState(new ToolCardState(1));

		game.getRoundInformation().setCurrentPlayer(0);
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

	/**
	 * Test execute tool card RoughingForceps Action - fail - canBeExecuted false
	 */
	@Test
	public void testExecuteRoughingForcepsToolCardActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard1Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ExecuteToolCard1Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(0));

		game.getRoundInformation().setCurrentPlayer(0);

		AbstractGameAction supportAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertFalse(supportAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(1);

		supportAction = new ToolCardSetIncreaseDecreaseAction(0, 0);
		Assert.assertFalse(supportAction.canBeExecuted(game));

		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	/**
	 * Test action to select dice from roundTrack
	 */
	@Test
	public void testToolCardSelectDiceRoundTrackAction()
	{
		AbstractGameAction gameAction = new ToolCardSetDiceRoundTrackAction();

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 0, 0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(5));

		game.getRoundInformation().setCurrentPlayer(0);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 0);

		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(0, (int)game.getRoundInformation().getData().getToolCardParameters().getRound());
		Assert.assertEquals(0, (int)game.getRoundInformation().getData().getToolCardParameters().getNDiceRT());

	}

	/**
	 * Test action to select dice from roundTrack - fail - canBeExecuted false
	 */
	@Test
	public void testToolCardSelectDiceRoundTrackActionFail()
	{
		AbstractGameAction gameAction = new ToolCardSetDiceRoundTrackAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 1, 0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(0));

		game.getRoundInformation().setCurrentPlayer(0);
		game.getRoundTrack().add(new Dice(Value.ONE, GameColor.YELLOW), 0);

		Assert.assertFalse(gameAction.canBeExecuted(game));

		Assert.assertFalse(game.getRoundInformation().getData().getToolCardParameters().getRound() != -1);
		Assert.assertFalse(game.getRoundInformation().getData().getToolCardParameters().getNDiceRT() != -1);

	}

	/**
	 * Test action to set dice value
	 */
	@Test
	public void testToolCardSelectDiceValueAction()
	{
		game.getMainBoard().addToolCard(new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,11));
		game.getRoundInformation().setCurrentPlayer(0);
		game.getMainBoard().setPlayerCount(1);
		game.getMainBoard().setGameState(new RoundState());

		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.GREEN));

		AbstractGameAction gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		game.getRoundInformation().setSPDice(0);

		gameAction = new ExecuteToolCardAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetDiceValueAction(0, 1);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(1, game.getRoundInformation().getData().getToolCardParameters().getValue());

	}

	/**
	 * Test action to set dice value
	 */
	@Test
	public void testToolCardSelectDiceValueActionValue()
	{
		game.getMainBoard().addToolCard(new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,11));
		game.getRoundInformation().setCurrentPlayer(0);
		game.getMainBoard().setPlayerCount(1);
		game.getMainBoard().setGameState(new RoundState());

		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.GREEN));

		AbstractGameAction gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		game.getRoundInformation().setSPDice(0);

		gameAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCardAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetDiceValueAction(0, Value.ONE);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(1, game.getRoundInformation().getData().getToolCardParameters().getValue());

	}

	/**
	 * Test action to select dice value - fail - canBeExecuted false
	 */
	@Test
	public void testToolCardSelectDiceValueActionFail()
	{
		AbstractGameAction gameAction = new ToolCardSetDiceValueAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetDiceValueAction(0, 1);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(0));
		game.getRoundInformation().setCurrentPlayer(1);

		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
		game.setState(new RoundState());

		Assert.assertFalse(gameAction.canBeExecuted(game));

		Assert.assertFalse(game.getRoundInformation().getData().getToolCardParameters().getValue()!=-1);

	}

	/**
	 * Test action to select a dice from placed frame
	 */
	@Test
	public void testToolCardSetPlacedDiceAction()
	{
		game.setState(new ToolCardState(2));
		game.getRoundInformation().setCurrentPlayer(0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);

		AbstractGameAction gameAction = new ToolCardSetPlacedDiceAction(0, 0 ,0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(0, game.getRoundInformation().getData().getToolCardParameters().getYPlacedDice());
		Assert.assertEquals(0, game.getRoundInformation().getData().getToolCardParameters().getXPlacedDice());

	}

	/**
	 * Test action to select second dice from placed frame
	 */
	@Test
	public void testToolCardSetSecondPlacedDiceAction()
	{
		game.setState(new ToolCardState(4));
		game.getRoundInformation().setCurrentPlayer(0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);

		AbstractGameAction gameAction = new ToolCardSetSecondPlacedDiceAction(0, 0 ,0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(0, game.getRoundInformation().getData().getToolCardParameters().getYPlacedDice2());
		Assert.assertEquals(0, game.getRoundInformation().getData().getToolCardParameters().getXPlacedDice2());

	}

	/**
	 * Test action to select dice from placed frame - fail - canBeExecuted false
	 */
	@Test
	public void testToolCardSetPlacedDiceActionFail()
	{
		AbstractGameAction gameAction = new ToolCardSetPlacedDiceAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPlacedDiceAction(0, 0 ,0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(0));
		game.getRoundInformation().setCurrentPlayer(0);

		Assert.assertFalse(gameAction.canBeExecuted(game));

		Assert.assertFalse(game.getRoundInformation().getData().getToolCardParameters().getYPlacedDice()!=-1);
		Assert.assertFalse(game.getRoundInformation().getData().getToolCardParameters().getXPlacedDice()!=-1);

	}

	/**
	 * Test action to select second dice from placed frame - fail - canBeExecuted false
	 */
	@Test
	public void testToolCardSetSecondPlacedDiceActionFail()
	{
		AbstractGameAction gameAction = new ToolCardSetSecondPlacedDiceAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetSecondPlacedDiceAction(0, 0 ,0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(0));
		game.getRoundInformation().setCurrentPlayer(0);

		Assert.assertFalse(gameAction.canBeExecuted(game));

		Assert.assertFalse(game.getRoundInformation().getData().getToolCardParameters().getYPlacedDice2()!=-1);
		Assert.assertFalse(game.getRoundInformation().getData().getToolCardParameters().getXPlacedDice2()!=-1);

	}

	/**
	 * Test action to select picked dice
	 */
	@Test
	public void testToolCardSetPickedDiceAction()
	{
		AbstractGameAction gameAction = new ToolCardSetPickedDiceAction(0, 0);

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(1));
		game.getRoundInformation().setCurrentPlayer(0);
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.YELLOW), false, false, false);

		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(0, game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to select picked dice - fail - canBeExecuted false
	 */
	@Test
	public void testToolCardSetPickedDiceActionFail()
	{
		AbstractGameAction gameAction = new ToolCardSetPickedDiceAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.setState(new ToolCardState(0));
		game.getRoundInformation().setCurrentPlayer(1);
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.YELLOW), false, false, false);

		Assert.assertFalse(gameAction.canBeExecuted(game));

		Assert.assertFalse(game.getRoundInformation().getData().getToolCardParameters().getNDice()!=-1);

	}

	/**
	 * Test action to use a tool card
	 */
	@Test
	public void testUseToolCardAction()
	{
		AbstractGameAction gameAction = new UseToolCardAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new FrameSelectionState());
		game.getRoundInformation().setCurrentPlayer(0);
		game.getPlayerBoard(0).setToken(5);
		gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(ToolCardState.class, game.getMainBoard().getData().getGameState().getClass());


	}

	/**
	 * Test action to choose if increase or decrease the value of the dice
	 */
	@Test
	public void testToolCardSetIncreaseDecreaseActionFail()
	{
		AbstractGameAction gameAction = new ToolCardSetIncreaseDecreaseAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	/**
	 * Test get tool card ToolTip
	 */
	@Test
	public void testCardIndexColorToolTip()
	{
		game.setState(new FrameSelectionState());
		game.setState(new RoundState());
		game.getRoundInformation().setCurrentPlayer(0);
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

	/**
	 * Test action to execute tool card 11
	 */
	@Test
	public void testExecuteToolCard11Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard11Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
		game.setState(new ToolCardState(11));
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.TWO, GameColor.RED));

		gameAction = new ToolCardSetPickedDiceAction(0,0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard11Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0);
		Assert.assertNull(dicePlacementCondition);

		Assert.assertEquals(ToolCardState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertNotEquals(-1, game.getRoundInformation().getData().getToolCardParameters().getNDice());

		Assert.assertEquals(GameColor.BLUE, game.getRoundInformation().getData().getToolCardParameters().getDice().getGameColor());

		game.setState(new RoundState());
		gameAction = new ToolCardSetDiceValueAction(0, 3);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());

		Dice dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice();
		Assert.assertEquals(GameColor.BLUE, dice.getGameColor());
		Assert.assertEquals(Value.THREE, dice.getValue());

		dice = game.getRoundInformation().getData().getToolCardParameters().getDice();
		Assert.assertNull(dice);

		gameAction = new EndTurnAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(-1, game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to execute tool card 11 - fail - canBeExecuted false
	 */
	@Test
	public void testExecuteToolCard11ActionEndTurnAction()
	{
		game.getMainBoard().setPlayerCount(1);
		game.getRoundInformation().addPlayerQueue(0);

		AbstractGameAction gameAction = new ExecuteToolCard11Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
		game.setState(new ToolCardState(11));
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.TWO, GameColor.RED));

		gameAction = new ToolCardSetPickedDiceAction(0,0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard11Action(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Dice dice = game.getRoundInformation().getData().getToolCardParameters().getDice();
		Assert.assertEquals(GameColor.BLUE, dice.getGameColor());

		Assert.assertEquals(0, game.getMainBoard().getExtractedDices().getData().getNumberOfDices());
		Assert.assertEquals(0, game.getPlayerBoard(0).getPickedDicesSlot().getData().getNDices());

		gameAction = new EndTurnAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(1, game.getMainBoard().getExtractedDices().getData().getNumberOfDices());
		Assert.assertEquals(0, game.getPlayerBoard(0).getPickedDicesSlot().getData().getNDices());

		Assert.assertNull(game.getRoundInformation().getData().getToolCardParameters().getDice());
		Assert.assertEquals(-1, game.getRoundInformation().getData().getToolCardParameters().getNDice());
		Assert.assertEquals(0, game.getPlayerBoard(0).getNPickedDices());

		Assert.assertEquals(GameColor.BLUE, game.getMainBoard().getExtractedDices().getData().getDice(0).getGameColor());

	}

	/**
	 * Test action to execute tool card 11 - fail  - canBeExecuted false
	 */
	@Test
	public void testExecuteToolCard11ActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard11Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

	/**
	 * Test action to execute tool card 12
	 */
	@Test
	public void testExecuteToolCard12Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard12Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

		gameAction = new ToolCardSetSecondPlacedDiceAction(0, 0, 1);
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

		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to execute tool card 12 - fail - canBeExecuted false
	 */
	@Test
	public void testExecuteToolCard12ActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard12Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

	/**
	 * Test action to execute tool card 12 - fail - canBeExecuted false - dices with no same color
	 */
	@Test
	public void testExecuteToolCard12ActionDifferentColorFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard12Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

	/**
	 * Test action to execute tool card 12 - fail - canBeExecuted false - dices with no same color
	 */
	@Test
	public void testExecuteToolCard12ActionDifferentColor2Fail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard12Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

		gameAction = new ToolCardSetSecondPlacedDiceAction(0, 0, 1);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCard12Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	/**
	 * Test action to execute tool card 2
	 */
	@Test
	public void testExecuteToolCard2Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard2Or3Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

		gameAction = new EndTurnAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to execute tool card 3
	 */
	@Test
	public void testExecuteToolCard3Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard2Or3Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

		gameAction = new EndTurnAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to execute tool card 9
	 */
	@Test
	public void testExecuteToolCard9ActionAction()
	{
		AbstractGameAction gameAction = new ExecuteToolCard9Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

		gameAction = new EndTurnAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to execute tool card 9 - fail - canBeExecuted false
	 */
	@Test
	public void testExecuteToolCard9ActionActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard9Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

	/**
	 * Test action to execute tool card 4
	 */
	@Test
	public void testExecuteToolCard4Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard4Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
		game.setState(new ToolCardState(4));

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.YELLOW), 0, 1);

		gameAction = new ToolCardSetPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetSecondPlacedDiceAction(0, 0, 1);
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

		gameAction = new EndTurnAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to execute tool card 4 - fail - canBeExecuted false
	 */
	@Test
	public void testExecuteToolCard4ActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard4Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

	/**
	 * Test action to execute tool card 5
	 */
	@Test
	public void testExecuteToolCard5Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard5Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

		gameAction = new EndTurnAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());

		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to execute tool card 5 - fail - canBeExecuted false
	 */
	@Test
	public void testExecuteToolCard5ActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard5Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

	/**
	 * Test action to execute tool card 6
	 */
	@Test
	public void testExecuteToolCard6Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard6Or10Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

		gameAction = new EndTurnAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to execute tool card 10
	 */
	@Test
	public void testExecuteToolCard10Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard6Or10Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

		gameAction = new EndTurnAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());

		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to execute tool card 7
	 */
	@Test
	public void testExecuteToolCard7Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard7Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
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

		gameAction = new EndTurnAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertEquals(-1, game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to execute tool card 8
	 */
	@Test
	public void testExecuteToolCard8Action()
	{
		AbstractGameAction gameAction = new ExecuteToolCard8Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
		game.setState(new RoundState());

		game.getRoundInformation().addPlayerQueue(1);
		game.getRoundInformation().addPlayerQueue(2);
		game.getRoundInformation().addPlayerQueue(3);
		game.getRoundInformation().addPlayerQueue(4);
		game.getRoundInformation().addPlayerQueue(0);

		game.getPlayerBoard(0).setToken(10);
		game.getMainBoard().addToolCard(new ToolCard("", "", GameColor.YELLOW, 8));
		gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCardAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);


		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());


		Assert.assertEquals(0, (int)game.getRoundInformation().getData().getNextPlayer());
		game.getRoundInformation().removeNextPlayer();
		Assert.assertEquals(1, (int)game.getRoundInformation().getData().getNextPlayer());
		game.getRoundInformation().removeNextPlayer();
		Assert.assertEquals(2, (int)game.getRoundInformation().getData().getNextPlayer());
		game.getRoundInformation().removeNextPlayer();
		Assert.assertEquals(3, (int)game.getRoundInformation().getData().getNextPlayer());
		game.getRoundInformation().removeNextPlayer();
		Assert.assertEquals(4, (int)game.getRoundInformation().getData().getNextPlayer());
		game.getRoundInformation().removeNextPlayer();
		Assert.assertEquals(-1, (int)game.getRoundInformation().getData().getNextPlayer());

	}

	/**
	 * Test action to execute tool card in use
	 */
	@Test
	public void testExecuteToolCardAction()
	{
		AbstractGameAction gameAction = new ExecuteToolCardAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
		game.getPlayerBoard(0).setToken(5);
		game.setState(new RoundState());

		game.getMainBoard().addToolCard(new ToolCard("", "", GameColor.YELLOW, 12));

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

		gameAction = new ToolCardSetSecondPlacedDiceAction(0, 0, 1);
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
		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());

		Assert.assertEquals(4, game.getPlayerBoard(0).getData().getToken());

	}

	/**
	 * Test use tool card - two token asked
	 */
	@Test
	public void testUseToolCardAction2TokenAsked()
	{
		AbstractGameAction gameAction = new ExecuteToolCardAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
		game.getPlayerBoard(0).setToken(5);
		game.setState(new RoundState());

		game.getMainBoard().addToolCard(new ToolCard("", "", GameColor.YELLOW, 12));

		game.getMainBoard().incNCallToolCard(0);

		gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

	}

	/**
	 * Test use of tool card in single player
	 */
	@Test
	public void testSinglePlayer()
	{
		game.getMainBoard().setPlayerCount(1);

		game.getRoundInformation().setCurrentPlayer(0);

		game.getPlayerBoard(0).setToken(0);

		game.setState(new RoundState());

		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.ONE, GameColor.YELLOW));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.TWO, GameColor.RED));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.THREE, GameColor.BLUE));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.FOUR, GameColor.GREEN));

		game.getMainBoard().addToolCard(new ToolCard("", "", GameColor.YELLOW, 12));

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

		gameAction = new ToolCardSetSecondPlacedDiceAction(0, 0, 1);
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
		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());

		Assert.assertEquals(0, game.getPlayerBoard(0).getData().getToken());

		Assert.assertEquals(3, game.getMainBoard().getExtractedDices().getData().getNumberOfDices());

		Assert.assertEquals(Value.TWO, game.getMainBoard().getExtractedDices().getData().getDice(0).getValue());
		Assert.assertEquals(GameColor.RED, game.getMainBoard().getExtractedDices().getData().getDice(0).getGameColor());

	}

	/**
	 * Test use tool card in single player - fail - canBeExecuted false
	 */
	@Test
	public void testSinglePlayerFail()
	{
		game.getMainBoard().setPlayerCount(1);

		game.getRoundInformation().setCurrentPlayer(0);

		game.getPlayerBoard(0).setToken(0);

		game.setState(new RoundState());

		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.ONE, GameColor.YELLOW));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.TWO, GameColor.RED));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.THREE, GameColor.BLUE));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.FOUR, GameColor.GREEN));

		game.getMainBoard().addToolCard(new ToolCard("", "", GameColor.YELLOW, 12));

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

	/**
	 * Test action to execute tool card 7 - fail - canBeExecuted false
	 */
	@Test
	public void testExecuteToolCard7ActionFail()
	{
		AbstractGameAction gameAction = new ExecuteToolCard7Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);
		game.setState(new ToolCardState(7));

		game.getRoundInformation().addPlayerQueue(1);
		game.getRoundInformation().addPlayerQueue(2);
		game.getRoundInformation().addPlayerQueue(2);
		game.getRoundInformation().addPlayerQueue(1);
		game.getRoundInformation().addPlayerQueue(0);

		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.FIVE, GameColor.YELLOW));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.TWO, GameColor.RED));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.THREE, GameColor.BLUE));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.FOUR, GameColor.GREEN));

		gameAction = new ExecuteToolCard7Action(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	/**
	 * Test tool card parameters
	 */
	@Test
	public void testParamToolCard()
	{
		game.getRoundInformation().setNDice(1);

		Assert.assertEquals(1, game.getRoundInformation().getData().getToolCardParameters().getNDice());

		game.getRoundInformation().setNDice(2);

		Assert.assertEquals(2, game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to execute tool card 12 - two dices to move
	 */
	@Test
	public void testExecuteToolCard12SecondAction()
	{
		AbstractGameAction gameAction = new ExecuteToolCard12Action();
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.getRoundInformation().setCurrentPlayer(0);

		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.ONE, GameColor.YELLOW), 0, 0);
		game.getPlayerBoard(0).addDiceInPlacedFrame(new Dice(Value.TWO, GameColor.BLUE), 0, 1);
		game.getRoundTrack().add(new Dice(Value.THREE, GameColor.YELLOW), 0);

		game.setState(new RoundState());

		game.getPlayerBoard(0).setToken(10);
		game.getMainBoard().addToolCard(new ToolCard("", "", GameColor.YELLOW, 12));
		gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetDiceRoundTrackAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ToolCardSetSecondPlacedDiceAction(0, 0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		gameAction = new ExecuteToolCardAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);

		Dice dice = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice();
		Assert.assertEquals(GameColor.YELLOW, dice.getGameColor());
		Assert.assertEquals(Value.ONE, dice.getValue());

		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(1);
		Assert.assertNull(dicePlacementCondition);

		dice = game.getPlayerBoard(0).getDicePlacedFrame().getData().getDice(0, 1);
		Assert.assertEquals(GameColor.BLUE, dice.getGameColor());
		Assert.assertEquals(Value.TWO, dice.getValue());

		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());
		Assert.assertEquals(-1,game.getRoundInformation().getData().getToolCardParameters().getNDice());

	}

	/**
	 * Test action to set dice value - fail - canBeExecuted false
	 */
	@Test
	public void testToolCardSetDiceValueActionFail()
	{
		game.getRoundInformation().setCurrentPlayer(0);
		AbstractGameAction gameAction = new ToolCardSetDiceValueAction(0, 0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		game.setState(new RoundState());

		game.getRoundInformation().setChangedDiceDB(0);
		game.getRoundInformation().setNDice(0);
		game.getRoundInformation().setDice(new Dice(Value.ONE, GameColor.GREEN));
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.ONE, GameColor.GREEN));

		gameAction = new ToolCardSetDiceValueAction(1, 1);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetDiceValueAction(0, 0);
		Assert.assertFalse(gameAction.canBeExecuted(game));

	}

	/**
	 * Test get list of actions for toolCards
	 */
	@Test
	public void testToolCardActionList()
	{
		List<Class> list = ToolCardActionList.getInstance().getList(5);

		Assert.assertEquals(2, list.size());
		Assert.assertEquals(ToolCardSetPickedDiceAction.class, list.get(0));
		Assert.assertEquals(ToolCardSetDiceRoundTrackAction.class, list.get(1));

		list.remove(0);
		Assert.assertEquals(1, list.size());

		list = ToolCardActionList.getInstance().getList(5);
		Assert.assertEquals(2, list.size());

		list = ToolCardActionList.getInstance().getList(12);
		Assert.assertEquals(3, list.size());

	}

	/**
	 * Test get list of actions for toolCards - fail - wrong index
	 */
	@Test
	public void testToolCardActionListFail()
	{
		List<Class> list = ToolCardActionList.getInstance().getList(13);
		Assert.assertEquals(0, list.size());

		list = ToolCardActionList.getInstance().getList(0);
		Assert.assertEquals(0, list.size());
	}

}