package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.controller.*;

import java.util.List;

/**
 * Test action and states
 */
public class TestAction {

	private GameController game;

	@Before
	public void setUp()
	{
		game = new GameController();
	}

	/**
	 * Test set seed
	 */
	@Test
	public void testSetSeed()
	{
		game.sendAction(new SetSeedAction(2));
		new SetSeedAction();
		game.processAllPendingAction();
		Assert.assertEquals(2, game.getModel().getSeed());

	}

	/**
	 * Test set number of player
	 */
	@Test
	public void testSetPlayerCount()
	{
		game.sendAction(new SetPlayerCountAction(3));
		game.processAction();
		game.processAction();
		Assert.assertEquals(3, game.getModel().getMainBoard().getData().getPlayerCount());
		game.sendAction(new StartGameAction());
		game.processAllPendingAction();
		game.sendAction(new SetPlayerCountAction(2));
		game.processAction();
		Assert.assertEquals(3, game.getModel().getMainBoard().getData().getPlayerCount());
	}

	/**
	 * Test get player ID
	 */
	@Test
	public void testGetPlayerID()
	{
		AbstractGameAction action = new AbstractGameAction(-2) {

			@Override
			public boolean canBeExecuted(IModel game) {
				return false;
			}

			@Override
			public void execute(Model game) {

			}
		};

		Assert.assertEquals(-2, action.getCallerID());

	}

	/**
	 * Test get the name of the action
	 */
	@Test
	public void testGetActionName()
	{
		SetPlayerCountAction s = new SetPlayerCountAction(2);
		Assert.assertEquals(s.getName(), SetPlayerCountAction.class.getSimpleName());
	}

	/**
	 * Test start game - Added windowFrameCouples and Frame selection state
	 */
	@Test
	public void testStartGame()
	{
		List<WindowFrameCouple> windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		game.sendAction(new StartGameAction());
		game.processAction();
		game.sendAction(new StartGameAction());
		game.processAction();
		Assert.assertEquals(FrameSelectionState.class, game.getModel().getMainBoard().getData().getGameState().getClass());
		Assert.assertEquals("Frame selection", game.getModel().getMainBoard().getData().getGameState().getName());
	}

	/**
	 * Test action is not executable
	 */
	@Test
	public void testNonExecutableAction()
	{
		game.sendAction(new StartGameAction());
		game.sendAction(new SetSeedAction(2));
		game.processAllPendingAction();
		Assert.assertEquals(0, game.getModel().getSeed());
	}

	/**
	 * Test format of the action
	 */
	@Test
	public void testActionFormat()
	{
		String s = (AbstractGameAction.getStructure(SetPlayerCountAction.class));
		Assert.assertEquals("SetPlayerCountAction <playerID> <playerCount> ", s);
	}

	/**
	 * Test action creation
	 */
	@Test
	public void testActionCreation()
	{
		int[] args = new int[2];
		args[0] = -1;
		args[1] = 2;

		AbstractGameAction a = AbstractGameAction.createAction(SetPlayerCountAction.class, args);
		Assert.assertEquals("SetPlayerCountAction playerID: -1 playerCount: 2 ", a.getToolTip());
	}

	/**
	 * Test action not creatable
	 */
	@Test
	public void testNonCreatableAction()
	{
		AbstractGameAction a = AbstractGameAction.createAction(
				AddWindowFrameCoupleAction.class,
				null);
		Assert.assertNull(a);
	}

	/**
	 * Test AddWindowFrameCoupleAction - canBeExecuted
	 */
	@Test
	public void testCanBeExecutedAddWindowFrameCoupleAction()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();

		WindowFrameCouple windowFrameCouple = windowFrameCouples.get(1);

		AddWindowFrameCoupleAction a = new AddWindowFrameCoupleAction(windowFrameCouple);

		Assert.assertTrue(a.canBeExecuted(game.getModel()));

		game.getModel().setState(new FrameSelectionState());

		Assert.assertFalse(a.canBeExecuted(game.getModel()));

	}

	/**
	 * Test action set frame
	 */
	@Test
	public void testFrameSetAction()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		FrameSetAction a = new FrameSetAction(0, 1, 0);
		Assert.assertFalse(a.canBeExecuted(game.getModel()));
		game.getModel().setState(new FrameSelectionState());
		Assert.assertTrue(a.canBeExecuted(game.getModel()));

	}

	/**
	 * Test action pick dice
	 */
	@Test
	public void testPickDiceAction()
	{
		PickDiceAction p = new PickDiceAction(0, 0);
		Assert.assertFalse(p.canBeExecuted(game.getModel()));
		game.getModel().setState(new RoundState());
		Assert.assertFalse(p.canBeExecuted(game.getModel()));
		game.getModel().getMainBoard().getExtractedDices().addDice(new Dice(Value.ONE, GameColor.YELLOW));
		Assert.assertTrue(p.canBeExecuted(game.getModel()));
		p = new PickDiceAction(1, 0);
		Assert.assertFalse(p.canBeExecuted(game.getModel()));

	}

	/**
	 * Test action place dice
	 */
	@Test
	public void testPlaceDiceAction()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		WindowFrame windowFrame = windowFrameCouples.get(1).getWindowFrame(0);
		game.getModel().getPlayerBoard(0).setWindowFrame(windowFrame);
		PlaceDiceAction p = new PlaceDiceAction(0, 0, 0, 0);
		Assert.assertFalse(p.canBeExecuted(game.getModel()));
		game.getModel().setState(new RoundState());
		Assert.assertFalse(p.canBeExecuted(game.getModel()));
		game.getModel().getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.FOUR, GameColor.YELLOW), true, true, true);
		Assert.assertTrue(p.canBeExecuted(game.getModel()));
		game.sendAction(p);
		game.processAction();
		Assert.assertNotNull(game.getModel().getPlayerBoard(0).getDicePlacedFrame().getData().getDice(0 , 0));
		p = new PlaceDiceAction(1, 0, 0, 0);
		Assert.assertFalse(p.canBeExecuted(game.getModel()));

	}

	/**
	 * Test action addWindowFrameCouple
	 */
	@Test
	public void testAddWindowFrameCoupleAction() {
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		WindowFrameCouple windowFrameCouple = windowFrameCouples.get(1);

		game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouple));
		game.processAction();

		Assert.assertEquals(windowFrameCouple, game.getModel().getMainBoard().getData().getWindowFrame(0));

	}

	/**
	 * Test action StartGame
	 */
	@Test
	public void testStartGameAction()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		game.sendAction(new StartGameAction());
		game.processAction();
		Assert.assertEquals("Frame selection", game.getModel().getMainBoard().getData().getGameState().getName());
	}

	/**
	 * Test to verify correct current player
	 */
	@Test
	public void testCurrentPlayer() {
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		MainBoard mainBoard = game.getModel().getMainBoard();
		mainBoard.setPlayerCount(4);
		game.sendAction(new StartGameAction());
		game.processAction();
		game.sendAction(new FrameSetAction(0, -1, 0));
		game.sendAction(new FrameSetAction(1, -1, 0));
		game.sendAction(new FrameSetAction(2, -1, 0));
		game.sendAction(new FrameSetAction(3, -1, 0));
		game.processAllPendingAction();

		Assert.assertEquals(3, game.getModel().getRoundInformation().getData().getCurrentFirstPlayer());
		Assert.assertEquals(3, game.getModel().getRoundInformation().getData().getCurrentPlayer());
	}

	/**
	 * Test get number of extracted dices
	 */
	@Test
	public void testGetNDices()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		WindowFrameCouple windowFrameCouple = windowFrameCouples.get(1);
		MainBoard mainBoard = game.getModel().getMainBoard();
		mainBoard.setPlayerCount(4);
		game.sendAction(new StartGameAction());
		game.processAction();
		game.sendAction(new FrameSetAction(0, -1, 0));
		game.sendAction(new FrameSetAction(1, -1, 0));
		game.sendAction(new FrameSetAction(2, -1, 0));
		game.sendAction(new FrameSetAction(3, -1, 0));
		game.processAllPendingAction();

		Assert.assertEquals(9, game.getModel().getMainBoard().getExtractedDices().getData().getNumberOfDices());

	}

	/**
	 * Test pick a dice and place it
	 */
	@Test
	public void testPickDiceAndPlaceDiceAction()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		WindowFrameCouple windowFrameCouple = windowFrameCouples.get(1);
		WindowFrame windowFrame = windowFrameCouple.getWindowFrame(0);
		MainBoard mainBoard = game.getModel().getMainBoard();
		mainBoard.setPlayerCount(4);
		game.getModel().getPlayerBoard(0).setWindowFrame(windowFrame);
		game.getModel().getPlayerBoard(1).setWindowFrame(windowFrame);
		game.getModel().getPlayerBoard(2).setWindowFrame(windowFrame);
		game.getModel().getPlayerBoard(3).setWindowFrame(windowFrame);
		game.sendAction(new StartGameAction());
		game.processAllPendingAction();
		game.sendAction(new FrameSetAction(0, -1, 0));
		game.sendAction(new FrameSetAction(1, -1, 0));
		game.sendAction(new FrameSetAction(2, -1, 0));
		game.sendAction(new FrameSetAction(3, -1, 0));
		game.processAllPendingAction();

		Assert.assertEquals(3, game.getModel().getRoundInformation().getData().getCurrentPlayer());

		game.sendAction(new PickDiceAction(3, 0));
		game.processAction();
		Assert.assertEquals(GameColor.YELLOW, game.getModel().getPlayerBoard(3).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice().getGameColor());
		Assert.assertEquals(Value.THREE, game.getModel().getPlayerBoard(3).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice().getValue());

		game.sendAction(new PlaceDiceAction(3, 0, 1, 0));
		game.processAction();
		Assert.assertEquals(1, game.getModel().getPlayerBoard(3).getDicePlacedFrame().getData().getNDices());
		Assert.assertEquals(GameColor.YELLOW, game.getModel().getPlayerBoard(3).getDicePlacedFrame().getData().getDice(1, 0).getGameColor());
		Assert.assertEquals(Value.THREE, game.getModel().getPlayerBoard(3).getDicePlacedFrame().getData().getDice(1, 0).getValue());

	}

	/**
	 * Test action end turn
	 */
	@Test
	public void testEndTurnAction()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		WindowFrameCouple windowFrameCouple = windowFrameCouples.get(1);
		MainBoard mainBoard = game.getModel().getMainBoard();
		mainBoard.setPlayerCount(4);
		game.sendAction(new StartGameAction());
		game.processAction();
		game.sendAction(new FrameSetAction(0, -1, 0));
		game.sendAction(new FrameSetAction(1, -1, 0));
		game.sendAction(new FrameSetAction(2, -1, 0));
		game.sendAction(new FrameSetAction(3, -1, 0));
		game.processAllPendingAction();

		game.sendAction(new EndTurnAction(2));
		game.processAction();
		Assert.assertEquals(3, game.getModel().getRoundInformation().getData().getCurrentPlayer());
	}

	/**
	 * Test action end turn with dice picked but not placed
	 */
	@Test
	public void testEndTurnDicePickedNotPlaced()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		WindowFrameCouple windowFrameCouple = windowFrameCouples.get(1);
		WindowFrame windowFrame = windowFrameCouple.getWindowFrame(0);
		MainBoard mainBoard = game.getModel().getMainBoard();
		mainBoard.setPlayerCount(4);
		game.getModel().getPlayerBoard(0).setWindowFrame(windowFrame);
		game.getModel().getPlayerBoard(1).setWindowFrame(windowFrame);
		game.getModel().getPlayerBoard(2).setWindowFrame(windowFrame);
		game.getModel().getPlayerBoard(3).setWindowFrame(windowFrame);
		game.sendAction(new StartGameAction());
		game.processAction();
		game.sendAction(new FrameSetAction(0, -1, 0));
		game.sendAction(new FrameSetAction(1, -1, 0));
		game.sendAction(new FrameSetAction(2, -1, 0));
		game.sendAction(new FrameSetAction(3, -1, 0));
		game.processAllPendingAction();
		game.sendAction(new PickDiceAction(3, 0));
		game.processAction();
		game.sendAction(new PlaceDiceAction(3, 0, 1, 0));
		game.processAction();
		game.sendAction(new EndTurnAction(3));
		game.processAction();

		Dice dice = game.getModel().getMainBoard().getExtractedDices().getData().getDice(0);
		Assert.assertEquals(0, game.getModel().getRoundInformation().getData().getCurrentPlayer());
		//pick dice
		game.sendAction(new PickDiceAction(0, 0));
		game.processAction();
		Assert.assertEquals(7, game.getModel().getMainBoard().getExtractedDices().getData().getNumberOfDices());
		//end turn, picked dice not placed
		game.sendAction(new EndTurnAction(0));
		game.processAction();
		//not placed dice back to extractedDices
		Assert.assertEquals(dice, game.getModel().getMainBoard().getExtractedDices().getData().getDice(7));
		Assert.assertEquals(8, game.getModel().getMainBoard().getExtractedDices().getData().getNumberOfDices());
	}

	/**
	 * Test state start round
	 */
	@Test
	public void testStartRoundState()
	{
		game.getModel().getMainBoard().setPlayerCount(1);
		game.getModel().setState(new StartRoundState());
		Assert.assertEquals(4, game.getModel().getMainBoard().getExtractedDices().getData().getNumberOfDices());
	}

	/**
	 * Test end round and and game
	 */
	@Test
	public void testEndRoundEndGame()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		WindowFrameCouple windowFrameCouple = windowFrameCouples.get(1);
		WindowFrame windowFrame = windowFrameCouple.getWindowFrame(0);
		MainBoard mainBoard = game.getModel().getMainBoard();
		RoundInformation roundInformation = game.getModel().getRoundInformation();
		mainBoard.setPlayerCount(4);
		game.getModel().getPlayerBoard(0).setWindowFrame(windowFrame);
		game.getModel().getPlayerBoard(1).setWindowFrame(windowFrame);
		game.getModel().getPlayerBoard(2).setWindowFrame(windowFrame);
		game.getModel().getPlayerBoard(3).setWindowFrame(windowFrame);
		game.sendAction(new StartGameAction());
		game.processAction();
		game.sendAction(new FrameSetAction(0, -1, 0));
		game.sendAction(new FrameSetAction(1, -1, 0));
		game.sendAction(new FrameSetAction(2, -1, 0));
		game.sendAction(new FrameSetAction(3, -1, 0));
		game.processAllPendingAction();
		Assert.assertEquals(3, game.getModel().getRoundInformation().getData().getCurrentPlayer());
		game.sendAction(new PickDiceAction(3, 0));
		game.processAction();
		game.sendAction(new PlaceDiceAction(3, 0, 1, 0));
		game.processAction();
		game.sendAction(new EndTurnAction(3));
		game.processAction();
		Assert.assertEquals(0, game.getModel().getRoundInformation().getData().getCurrentPlayer());
		game.sendAction(new EndTurnAction(0));
		game.processAction();
		game.sendAction(new EndTurnAction(1));
		game.processAction();
		Assert.assertEquals(2, game.getModel().getRoundInformation().getData().getCurrentPlayer());
		game.sendAction(new EndTurnAction(2));
		game.processAction();
		Assert.assertEquals(2, game.getModel().getRoundInformation().getData().getCurrentPlayer());
		game.sendAction(new PickDiceAction(2, 1));
		game.processAction();
		game.sendAction(new EndTurnAction(2));
		game.processAction();
		Assert.assertEquals(1, game.getModel().getRoundInformation().getData().getCurrentPlayer());
		game.sendAction(new EndTurnAction(1));
		game.processAction();
		Assert.assertEquals(0, game.getModel().getRoundInformation().getData().getCurrentPlayer());
		game.sendAction(new EndTurnAction(0));
		game.processAction();
		Assert.assertEquals(3, game.getModel().getRoundInformation().getData().getCurrentPlayer());
		game.sendAction(new EndTurnAction(3));
		game.processAction();
		Assert.assertEquals(0, game.getModel().getRoundInformation().getData().getCurrentPlayer());
		Assert.assertFalse(game.getModel().getRoundTrack().getData().isFree(0));
		Assert.assertTrue(game.getModel().getRoundTrack().getData().isFree(1));
		Assert.assertEquals(1, game.getModel().getRoundInformation().getData().getCurrentRound());
		for(int i=0; i<90; i++)
		{
			Integer nextPlayer = game.getModel().getRoundInformation().getData().getNextPlayer();
			roundInformation.removeNextPlayer();
			game.sendAction(new EndTurnAction(nextPlayer));
			game.processAction();
		}

		Assert.assertEquals("End model", game.getModel().getMainBoard().getData().getGameState().getName());

	}

	/**
	 * Test action set difficulty (single player)
	 */
	@Test
	public void testSetDifficultyAction()
	{
		game.sendAction(new SetPlayerCountAction(1));
		game.processAction();

		game.sendAction(new SetDifficultyAction(2));
		game.processAction();
		Assert.assertEquals(2, game.getModel().getMainBoard().getData().getDifficulty());

		game.getModel().setState(new FrameSelectionState());

		Assert.assertEquals(2, game.getModel().getMainBoard().getData().getToolCards().size());

	}

	/**
	 * Test action set difficulty - Fail - wrong value
	 */
	@Test
	public void testSetDifficultyActionWrongDifficultyLevel()
	{
		game.sendAction(new SetPlayerCountAction(1));
		game.processAction();

		game.sendAction(new SetDifficultyAction(0));
		game.processAction();
		Assert.assertEquals(3, game.getModel().getMainBoard().getData().getDifficulty());

		game.sendAction(new SetDifficultyAction(6));
		game.processAction();
		Assert.assertEquals(3, game.getModel().getMainBoard().getData().getDifficulty());

	}

	/**
	 * Test action set difficulty - fail - wrong number of player
	 */
	@Test
	public void testSetDifficultyActionFail()
	{
		game.getModel().setState(new RoundState());

		AbstractGameAction gameAction = new SetDifficultyAction(2);

		Assert.assertFalse(gameAction.canBeExecuted(game.getModel()));

		gameAction = new SetDifficultyAction();

		Assert.assertFalse(gameAction.canBeExecuted(game.getModel()));
	}

	/**
	 * Test action set player count - fail - wrong value
	 */
	@Test
	public void testSetPlayerCountFail()
	{
		game.getModel().setState(new PreGameState());

		AbstractGameAction gameAction = new SetPlayerCountAction(5);
		Assert.assertFalse(gameAction.canBeExecuted(game.getModel()));

	}

	/**
	 * Test max one dice picked per turn
	 */
	@Test
	public void testMaxOnePickedDice()
	{
		game.getModel().getMainBoard().setPlayerCount(1);
		game.getModel().getRoundInformation().setCurrentPlayer(0);
		game.getModel().setState(new RoundState());

		game.getModel().getMainBoard().getExtractedDices().addDice(new Dice(Value.ONE, GameColor.GREEN));
		game.getModel().getMainBoard().getExtractedDices().addDice(new Dice(Value.TWO, GameColor.RED));

		AbstractGameAction gameAction = new PickDiceAction(0, 0);

		Assert.assertTrue(gameAction.canBeExecuted(game.getModel()));
		gameAction.execute(game.getModel());

		Assert.assertFalse(gameAction.canBeExecuted(game.getModel()));

		gameAction = new EndTurnAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game.getModel()));
		gameAction.execute(game.getModel());

		gameAction = new PickDiceAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game.getModel()));

	}

	/**
	 * Test max one tool card used per turn
	 */
	@Test
	public void testMaxOneToolCard()
	{
		game.getModel().getMainBoard().setPlayerCount(2);
		game.getModel().getRoundInformation().setCurrentPlayer(0);
		game.getModel().setState(new RoundState());
		game.getModel().getPlayerBoard(0).setToken(10);

		game.getModel().getMainBoard().addToolCard(new ToolCard("", "", GameColor.GREEN, 1));

		game.getModel().getMainBoard().getExtractedDices().addDice(new Dice(Value.ONE, GameColor.GREEN));
		game.getModel().getMainBoard().getExtractedDices().addDice(new Dice(Value.THREE, GameColor.RED));

		AbstractGameAction gameAction = new PickDiceAction(0, 0);

		Assert.assertTrue(gameAction.canBeExecuted(game.getModel()));
		gameAction.execute(game.getModel());

		gameAction = new UseToolCardAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game.getModel()));
		gameAction.execute(game.getModel());

		gameAction = new UseToolCardAction(0, 0);
		Assert.assertFalse(gameAction.canBeExecuted(game.getModel()));

		gameAction = new ToolCardSetPickedDiceAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game.getModel()));
		gameAction.execute(game.getModel());

		gameAction = new ToolCardSetIncreaseDecreaseAction(0, 0);
		Assert.assertTrue(gameAction.canBeExecuted(game.getModel()));
		gameAction.execute(game.getModel());

		gameAction = new ExecuteToolCardAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game.getModel()));
		gameAction.execute(game.getModel());

		Assert.assertEquals(9, game.getModel().getPlayerBoard(0).getData().getToken());

		Assert.assertEquals(1, game.getModel().getPlayerBoard(0).getPickedDicesSlot().getNDices());
		Assert.assertEquals(GameColor.GREEN, game.getModel().getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice().getGameColor());
		Assert.assertEquals(Value.TWO, game.getModel().getPlayerBoard(0).getPickedDicesSlot().getData().getDicePlacementCondition(0).getDice().getValue());

		gameAction = new UseToolCardAction(0, 0);
		Assert.assertFalse(gameAction.canBeExecuted(game.getModel()));

	}

	/**
	 * Test cancel tool card use
	 */
	@Test
	public void testCancelToolCardUseAction()
	{
		game.getModel().getRoundInformation().setCurrentPlayer(0);

		game.getModel().setState(new ToolCardState(0));
		AbstractGameAction gameAction = new CancelToolCardUseAction();
		Assert.assertFalse(gameAction.canBeExecuted(game.getModel()));

		gameAction = new CancelToolCardUseAction(0);
		Assert.assertTrue(gameAction.canBeExecuted(game.getModel()));

	}

}