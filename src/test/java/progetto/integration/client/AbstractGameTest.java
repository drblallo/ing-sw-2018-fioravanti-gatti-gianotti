package progetto.integration.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.controller.*;
import progetto.model.EndTurnAction;
import progetto.model.FrameSelectionState;
import progetto.model.FrameSetAction;
import progetto.model.RoundState;
import progetto.utils.Waiter;

public abstract class AbstractGameTest
{
	private IExecutibleGameFactory gameFactory;
	protected IGameController game;
	private Waiter steven = new Waiter();

	public AbstractGameTest(IExecutibleGameFactory gameFactory)
	{
		this.gameFactory = gameFactory;
	}


	@Before
	public void setUp()
	{
		game = gameFactory.getGame();
	}


	@Test
	public void testGameStart()
	{
		game.sendAction(new StartGameAction());
		wait(500);
		Assert.assertEquals(FrameSelectionState.class, game.getModel().getMainBoard().getData().getGameState().getClass());

		game.getModel().getCommandQueue();
		game.getModel().getMainBoard().getData().getGameState();
		game.getModel().getMainBoard();
		game.getModel().getPlayerBoard(0);
		game.getModel().getMainBoard().getData().getPlayerCount();
		game.getModel().getRoundTrack();
		game.processAction();
		game.processAllPendingAction();
	}

	@Test
	public void testMultipleSetSeed()
	{
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(2));
		game.sendAction(new SetSeedAction(3));
		game.sendAction(new SetPlayerCountAction(3));

		wait(500);
		Assert.assertEquals(3, game.getModel().getMainBoard().getData().getPlayerCount());
	}

	public void wait(int milliseconds)
	{
		steven.wait(milliseconds);
	}


	@Test
	public void testProxy()
	{
		ClientGame g = (ClientGame) game;
		g.sendAction(new SetPlayerCountAction(1));
		g.sendAction(new StartGameAction());
		g.sendAction(new FrameSetAction(0, 0, 1));
		wait(500);
		Assert.assertEquals(
				RoundState.class.getName(),
				g.getModel().getMainBoard().getData().getGameState().getClass().getName());
		Assert.assertEquals(
				RoundState.class.getName(),
				g.getClientConnection().getProxy().getMainBoard().getData().getGameState().getClass().getName());

		g.getClientConnection().pickChair(
				g.getClientConnection().getProxy().getMainBoard().getData().getCurrentPlayer()
		);

		wait(400);



		g.sendAction(new PickDiceAction(g.getModel().getMainBoard().getData().getCurrentPlayer(), 1));
		g.sendAction(new PlaceDiceAction(g.getModel().getMainBoard().getData().getCurrentPlayer(), 0, 0, 0));
		g.sendAction(new EndTurnAction(g.getModel().getMainBoard().getData().getCurrentPlayer()));
		g.sendAction(new EndTurnAction(g.getModel().getMainBoard().getData().getCurrentPlayer()));
		g.sendAction(new EndTurnAction(g.getModel().getMainBoard().getData().getCurrentPlayer()));
		wait(500);


		try
		{
			g.getNewPlayerEnforces();
		}
		catch (UnsupportedOperationException e)
		{

		}

		try
		{
			g.getEnforceCallback();
		}
		catch (UnsupportedOperationException e)
		{
			return;
		}
		Assert.fail();
	}
}
