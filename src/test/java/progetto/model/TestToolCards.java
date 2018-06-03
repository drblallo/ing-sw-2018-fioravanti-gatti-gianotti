package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.controller.ExecuteToolCardAction;
import progetto.controller.GameController;
import progetto.controller.ToolCardSetPickedDiceAction;
import progetto.controller.UseToolCardAction;

public class TestToolCards {

	Model game;

	@Before
	public void setUp()
	{
		game = new Model();
	}

	@Test
	public void testRoughingForcepsState()
	{
		AbstractToolCardState toolCardState = new RoughingForcepsState();
		game.setState(toolCardState);
		game.getMainBoard().setParamToolCard("nDice", 0);
		game.getMainBoard().setParamToolCard("increaseDecrease", 0);
		Assert.assertTrue(toolCardState.isEverythingSet(game));

	}


	@Test
	public void testToolTip()
	{
		AbstractToolCard toolCardState = new RoughingForcepsToolCard();
		Assert.assertEquals("Pinza Sgrossatrice"+" "+"Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1"+" "+Color.PURPLE, toolCardState.getToolTip());

	}

	@Test
	public void testRoughingForcepsStateSolve()
	{
		AbstractToolCardState toolCardState = new RoughingForcepsState();
		game.setState(toolCardState);

		game.getMainBoard().setCurrentPlayer(1);
		game.getPlayerBoard(1).getPickedDicesSlot().add(new Dice(Value.TWO, Color.GREEN), false,false,false);

		game.getMainBoard().setParamToolCard("nDice", 0);
		game.getMainBoard().setParamToolCard("increaseDecrease", 0);
		toolCardState.solve(game);

		Assert.assertEquals(Value.THREE, game.getPlayerBoard(1).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice().getValue());

		game.getMainBoard().setParamToolCard("nDice", 0);
		game.getMainBoard().setParamToolCard("increaseDecrease", 1);
		toolCardState.solve(game);

		Assert.assertEquals(Value.TWO, game.getPlayerBoard(1).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice().getValue());

	}

	@Test
	public void testRoughingForcepsSetOperationAction()
	{
		AbstractGameState gameState = new RoughingForcepsState();
		game.getMainBoard().setGameState(gameState);
		AbstractGameAction gameAction = new RoughingForcepsSetOperationAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction = new RoughingForcepsSetOperationAction(1);
		Assert.assertTrue(gameAction.canBeExecuted(game));

	}

	@Test
	public void testRoughingForcepsSetOperationActionExecute()
	{
		AbstractGameState gameState = new RoughingForcepsState();
		game.getMainBoard().setGameState(gameState);
		AbstractGameAction gameAction = new RoughingForcepsSetOperationAction(0);
		gameAction.execute(game);

		Assert.assertEquals(0, (int)game.getMainBoard().getData().getParamToolCard().get("increaseDecrease"));

	}

	@Test
	public void testRoughingForcepsSetOperationActionFail()
	{
		AbstractGameAction gameAction = new RoughingForcepsSetOperationAction(0);
		Assert.assertFalse(gameAction.canBeExecuted(game));
	}

	@Test
	public void testRoughingForcepsSetOperationActionFail2()
	{
		AbstractGameAction gameAction = new RoughingForcepsSetOperationAction(2);
		Assert.assertFalse(gameAction.canBeExecuted(game));
	}

	@Test
	public void testExecuteToolCardAction()
	{
		GameController game = new GameController();
		AbstractGameState toolCardState = new RoughingForcepsState();
		game.getModel().setState(toolCardState);

		game.getModel().getMainBoard().setCurrentPlayer(1);
		game.getModel().getPlayerBoard(1).getPickedDicesSlot().add(new Dice(Value.TWO, Color.GREEN), false,false,false);

		game.getModel().getMainBoard().setParamToolCard("nDice", 0);
		game.getModel().getMainBoard().setParamToolCard("increaseDecrease", 0);

		AbstractToolCardState cardState = (AbstractToolCardState)toolCardState;
		Assert.assertTrue(cardState.isEverythingSet(game.getModel()));

		game.sendAction(new ExecuteToolCardAction());
		game.processAction();

		Assert.assertEquals(Value.THREE, game.getModel().getPlayerBoard(1).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice().getValue());

	}

	@Test
	public void testExecuteToolCardActionFail()
	{
		GameController game = new GameController();
		game.getModel().setState(new PreGameState());

		game.sendAction(new ExecuteToolCardAction());
		game.processAction();

		ExecuteToolCardAction executeToolCardAction = new ExecuteToolCardAction();
		Assert.assertFalse(executeToolCardAction.canBeExecuted(game.getModel()));

	}

	@Test
	public void testRoughingForcepsStateFail()
	{
		AbstractGameState toolCardState = new RoughingForcepsState();
		game.setState(toolCardState);

		game.getMainBoard().setCurrentPlayer(1);
		game.getPlayerBoard(1).getPickedDicesSlot().add(new Dice(Value.TWO, Color.GREEN), false,false,false);

		game.getMainBoard().setParamToolCard("nDice", 2);
		game.getMainBoard().setParamToolCard("increaseDecrease", 0);

		AbstractToolCardState cardState = ((AbstractToolCardState) toolCardState);
		cardState.solve(game);
		Assert.assertEquals(RoundState.class, game.getMainBoard().getData().getGameState().getClass());

	}

	@Test
	public void testUseToolCardAction()
	{
		game.setState(new RoundState());
		game.getMainBoard().addToolCard(new RoughingForcepsToolCard());
		AbstractGameAction gameAction = new UseToolCardAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game));
		gameAction.execute(game);
		Assert.assertEquals(RoughingForcepsState.class, game.getMainBoard().getData().getGameState().getClass());

	}

	@Test
	public void testUseToolCardActionFail()
	{
		game.setState(new RoundState());
		game.getMainBoard().addToolCard(new RoughingForcepsToolCard());
		AbstractGameAction gameAction = new UseToolCardAction(1);
		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new UseToolCardAction();
		Assert.assertFalse(gameAction.canBeExecuted(game));
	}

	@Test
	public void testToolCardSetPickedDiceAction()
	{
		game.setState(new RoughingForcepsState());

		game.getMainBoard().setCurrentPlayer(1);

		AbstractGameAction gameAction = new ToolCardSetPickedDiceAction(0);

		game.getPlayerBoard(1).getPickedDicesSlot().add(new Dice(Value.THREE, Color.GREEN), false, false, false);

		Assert.assertTrue(gameAction.canBeExecuted(game));

		gameAction.execute(game);

		Assert.assertEquals(0, (int)game.getMainBoard().getData().getParamToolCard().get("nDice"));

	}

	@Test
	public void testToolCardSetPickedDiceActionFail()
	{
		game.setState(new RoughingForcepsState());

		game.getMainBoard().setCurrentPlayer(1);

		AbstractGameAction gameAction = new ToolCardSetPickedDiceAction(0);

		Assert.assertFalse(gameAction.canBeExecuted(game));

		gameAction = new ToolCardSetPickedDiceAction();

		Assert.assertFalse(gameAction.canBeExecuted(game));
	}

}
