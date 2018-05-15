package progetto.game;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAction {

	private Game game;

	@Before
	public void setUp()
	{
		game = new Game();
	}

	@Test
	public void testSetSeed()
	{
		game.sendAction(new SetSeedAction(2));
		new SetSeedAction();
		game.processAllPendingAction();
		Assert.assertEquals(2, game.getSeed());

	}

	@Test
	public void testSetPlayerCount()
	{
		game.sendAction(new SetPlayerCountAction(3));
		game.processAction();
		game.processAction();
		Assert.assertEquals(3, game.getMainBoard().getMainBoardData().getPlayerCount());
		game.sendAction(new StartGameAction());
		game.processAllPendingAction();
		game.sendAction(new SetPlayerCountAction(2));
		game.processAction();
		Assert.assertEquals(3, game.getMainBoard().getMainBoardData().getPlayerCount());
	}

	@Test
	public void testGetPlayerID()
	{
		AbstractGameAction action = new AbstractGameAction(-2) {

			@Override
			public boolean canBeExecuted(Game game) {
				return false;
			}

			@Override
			protected void execute(Game game) {

			}
		};

		Assert.assertEquals(-2, action.getCallerID());

	}

	@Test
	public void testGetActionName()
	{
		SetPlayerCountAction s = new SetPlayerCountAction(2);
		Assert.assertEquals(s.getName(), SetPlayerCountAction.class.getSimpleName());
	}

	@Test
	public void testStartGame()
	{
		game.sendAction(new StartGameAction());
		game.processAction();
		game.sendAction(new StartGameAction());
		game.processAction();
		Assert.assertEquals(FrameSelectionState.class, game.getMainBoard().getMainBoardData().getGameState().getClass());
		Assert.assertEquals("Frame selection", game.getMainBoard().getMainBoardData().getGameState().getName());
	}

	@Test
	public void testNonExecutibleAction()
	{
		game.sendAction(new StartGameAction());
		game.sendAction(new SetSeedAction(2));
		game.processAllPendingAction();
		Assert.assertEquals(0, game.getSeed());
	}

	@Test
	public void testActionFormat()
	{
		String s = (AbstractGameAction.getStructure(SetPlayerCountAction.class));
		Assert.assertEquals("SetPlayerCountAction <playerID> <playerCount> ", s);
	}

	@Test
	public void testActionCreation()
	{
		int[] args = new int[2];
		args[0] = -1;
		args[1] = 2;

		AbstractGameAction a = AbstractGameAction.createAction(SetPlayerCountAction.class, args);
		Assert.assertEquals("SetPlayerCountAction playerID: -1 playerCount: 2 ", a.getToolTip());
	}


	@Test
	public void testNonCreatableAction()
	{
		AbstractGameAction a = AbstractGameAction.createAction(
				AddWindowFrameCoupleAction.class,
				null);
		Assert.assertNull(a);
	}

	@Test
	public void testCanBeExecutedAddWindowFrameCoupleAction()
	{
		JSONArray ja = new JSONArray();
		ja.put("Virtus"); ja.put(5); ja.put(9); ja.put(0); ja.put(0); ja.put(Value.FOUR); ja.put(2); ja.put(0);
		ja.put(Value.TWO); ja.put(3); ja.put(0); ja.put(Value.FIVE); ja.put(2); ja.put(1); ja.put(Value.SIX);
		ja.put(4); ja.put(1); ja.put(Value.TWO); ja.put(1); ja.put(2); ja.put(Value.THREE); ja.put(3);
		ja.put(2); ja.put(Value.FOUR); ja.put(0); ja.put(3); ja.put(Value.FIVE); ja.put(2); ja.put(3);
		ja.put(Value.ONE); ja.put(4); ja.put(4); ja.put(0); ja.put(Color.GREEN); ja.put(3); ja.put(1);
		ja.put(Color.GREEN); ja.put(2); ja.put(2); ja.put(Color.GREEN); ja.put(1); ja.put(3); ja.put(Color.GREEN);
		WindowFrameCouple windowFrameCouple = new WindowFrameCouple(ja, ja);
		AddWindowFrameCoupleAction a = new AddWindowFrameCoupleAction(windowFrameCouple);

		Assert.assertTrue(a.canBeExecuted(game));

		game.setState(new FrameSelectionState());

		Assert.assertFalse(a.canBeExecuted(game));

	}

	@Test
	public void testFrameSetAction()
	{
		FrameSetAction a = new FrameSetAction(1);
		Assert.assertFalse(a.canBeExecuted(game));
		game.setState(new FrameSelectionState());
		Assert.assertTrue(a.canBeExecuted(game));
	}

	@Test
	public void testPickDiceAction()
	{
		PickDiceAction p = new PickDiceAction(0, 0);
		Assert.assertFalse(p.canBeExecuted(game));
		game.setState(new RoundState());
		Assert.assertFalse(p.canBeExecuted(game));
		game.getMainBoard().getExtractedDices().addDice(new Dice(Value.ONE, Color.YELLOW));
		Assert.assertTrue(p.canBeExecuted(game));
		p = new PickDiceAction(1, 0);
		Assert.assertFalse(p.canBeExecuted(game));
	}

	@Test
	public void testPlaceDiceAction()
	{
		JSONArray ja;
		ja = new JSONArray();
		ja.put("Virtus"); ja.put(5); ja.put(9); ja.put(0); ja.put(0); ja.put(Value.FOUR); ja.put(2); ja.put(0);
		ja.put(Value.TWO); ja.put(3); ja.put(0); ja.put(Value.FIVE); ja.put(2); ja.put(1); ja.put(Value.SIX);
		ja.put(4); ja.put(1); ja.put(Value.TWO); ja.put(1); ja.put(2); ja.put(Value.THREE); ja.put(3);
		ja.put(2); ja.put(Value.FOUR); ja.put(0); ja.put(3); ja.put(Value.FIVE); ja.put(2); ja.put(3);
		ja.put(Value.ONE); ja.put(4); ja.put(4); ja.put(0); ja.put(Color.GREEN); ja.put(3); ja.put(1);
		ja.put(Color.GREEN); ja.put(2); ja.put(2); ja.put(Color.GREEN); ja.put(1); ja.put(3); ja.put(Color.GREEN);

		game.getPlayerBoard(0).setWindowFrame(new WindowFrame(ja));
		PlaceDiceAction p = new PlaceDiceAction(0, 0, 0, 0);
		Assert.assertFalse(p.canBeExecuted(game));
		game.setState(new RoundState());
		Assert.assertFalse(p.canBeExecuted(game));
		game.getPlayerBoard(0).getPickedDicesSlot().add(new Dice(Value.FOUR, Color.YELLOW), true, true, true);
		Assert.assertTrue(p.canBeExecuted(game));
		p = new PlaceDiceAction(1, 0, 0, 0);
		Assert.assertFalse(p.canBeExecuted(game));
	}

	@Test
	public void testActionAndStateSimulation()
	{
		JSONArray ja;
		ja = new JSONArray();
		ja.put("Virtus"); ja.put(5); ja.put(9); ja.put(0); ja.put(0); ja.put(Value.FOUR); ja.put(2); ja.put(0);
		ja.put(Value.TWO); ja.put(3); ja.put(0); ja.put(Value.FIVE); ja.put(2); ja.put(1); ja.put(Value.SIX);
		ja.put(4); ja.put(1); ja.put(Value.TWO); ja.put(1); ja.put(2); ja.put(Value.THREE); ja.put(3);
		ja.put(2); ja.put(Value.FOUR); ja.put(0); ja.put(3); ja.put(Value.FIVE); ja.put(2); ja.put(3);
		ja.put(Value.ONE); ja.put(4); ja.put(4); ja.put(0); ja.put(Color.GREEN); ja.put(3); ja.put(1);
		ja.put(Color.GREEN); ja.put(2); ja.put(2); ja.put(Color.GREEN); ja.put(1); ja.put(3); ja.put(Color.GREEN);

		WindowFrame windowFrame = new WindowFrame(ja);

		WindowFrameCouple windowFrameCouple = new WindowFrameCouple(ja, ja);

		game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouple));
		game.processAction();

		MainBoard mainBoard = game.getMainBoard();
		mainBoard.setPlayerCount(4);
		game.getPlayerBoard(0).setWindowFrame(windowFrame);
		game.getPlayerBoard(1).setWindowFrame(windowFrame);
		game.getPlayerBoard(2).setWindowFrame(windowFrame);
		game.getPlayerBoard(3).setWindowFrame(windowFrame);
		game.setState(new PreGameState());

		game.sendAction(new SetPlayerCountAction(4));
		game.processAction();

		Assert.assertEquals(4, mainBoard.getMainBoardData().getPlayerCount());

		game.sendAction(new StartGameAction());
		game.processAction();

		game.sendAction(new FrameSetAction(-1));
		game.processAction();

		Assert.assertEquals(2, game.getMainBoard().getMainBoardData().getCurrentFirstPlayer());
		Assert.assertEquals(2, game.getMainBoard().getMainBoardData().getCurrentPlayer());

		Assert.assertEquals(9, game.getMainBoard().getExtractedDices().getExtractedDicesData().getNumberOfDices());

		game.sendAction(new PickDiceAction(2, 0));
		game.processAction();

		Assert.assertEquals(Color.BLUE, game.getPlayerBoard(2).getPickedDicesSlot().getPickedDicesSlotData().getDicePlacementCondition(0).getDice().getColor());
		Assert.assertEquals(Value.FIVE, game.getPlayerBoard(2).getPickedDicesSlot().getPickedDicesSlotData().getDicePlacementCondition(0).getDice().getValue());

		game.sendAction(new PlaceDiceAction(2, 0, 1, 0));
		game.processAction();

		Assert.assertEquals(1, game.getPlayerBoard(2).getDicePlacedFrame().getDicePlacedFrameData().getNDices());
		Assert.assertEquals(Color.BLUE, game.getPlayerBoard(2).getDicePlacedFrame().getDicePlacedFrameData().getDice(1, 0).getColor());
		Assert.assertEquals(Value.FIVE, game.getPlayerBoard(2).getDicePlacedFrame().getDicePlacedFrameData().getDice(1, 0).getValue());

		game.sendAction(new EndTurnAction(2));
		game.processAction();
		Assert.assertEquals(3, game.getMainBoard().getMainBoardData().getCurrentPlayer());
		game.sendAction(new EndTurnAction(3));
		game.processAction();
		Assert.assertEquals(0, game.getMainBoard().getMainBoardData().getCurrentPlayer());
		game.sendAction(new EndTurnAction(0));
		game.processAction();
		Assert.assertEquals(1, game.getMainBoard().getMainBoardData().getCurrentPlayer());
		game.sendAction(new EndTurnAction(1));
		game.processAction();
		Assert.assertEquals(1, game.getMainBoard().getMainBoardData().getCurrentPlayer());
		game.sendAction(new PickDiceAction(1, 1));
		game.processAction();
		game.sendAction(new EndTurnAction(1));
		game.processAction();
		Assert.assertEquals(0, game.getMainBoard().getMainBoardData().getCurrentPlayer());
		game.sendAction(new EndTurnAction(0));
		game.processAction();
		Assert.assertEquals(3, game.getMainBoard().getMainBoardData().getCurrentPlayer());
		game.sendAction(new EndTurnAction(3));
		game.processAction();
		Assert.assertEquals(2, game.getMainBoard().getMainBoardData().getCurrentPlayer());
		game.sendAction(new EndTurnAction(2));
		game.processAction();
		Assert.assertEquals(3, game.getMainBoard().getMainBoardData().getCurrentPlayer());

		Assert.assertFalse(game.getRoundTrack().getRoundTrackData().isFree(0));

		Assert.assertEquals(1, game.getMainBoard().getMainBoardData().getCurrentRound());

		for(int i=0; i<90; i++)
		{
			game.sendAction(new EndTurnAction(game.getMainBoard().getNextPlayer()));
			game.processAction();
		}

		Assert.assertEquals("End game", game.getMainBoard().getMainBoardData().getGameState().getName());

	}

}
