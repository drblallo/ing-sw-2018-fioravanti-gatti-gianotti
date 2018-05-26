package progetto.clientintegration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import progetto.game.*;
import progetto.utils.Waiter;

import java.util.ArrayList;

public abstract class AbstractGameTest
{
	private IExecutibleGameFactory gameFactory;
	protected IExecuibleGame game;
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
		Assert.assertEquals(FrameSelectionState.class, game.getMainBoard().getData().getGameState().getClass());

		game.getCommandQueue();
		game.getMainBoard().getData().getGameState();
		game.getMainBoard();
		game.getPlayerBoard(0);
		game.getMainBoard().getData().getPlayerCount();
		game.getRoundTrack();
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
		Assert.assertEquals(3, game.getMainBoard().getData().getPlayerCount());
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
				g.getMainBoard().getData().getGameState().getClass().getName());
		Assert.assertEquals(
				RoundState.class.getName(),
				g.getClientConnection().getProxy().getMainBoard().getData().getGameState().getClass().getName());

		g.getClientConnection().pickChair(
				g.getClientConnection().getProxy().getMainBoard().getData().getCurrentPlayer()
		);

		wait(400);



		g.sendAction(new PickDiceAction(g.getMainBoard().getData().getCurrentPlayer(), 1));
		g.sendAction(new PlaceDiceAction(g.getMainBoard().getData().getCurrentPlayer(), 0, 0, 0));
		g.sendAction(new EndTurnAction(g.getMainBoard().getData().getCurrentPlayer()));
		g.sendAction(new EndTurnAction(g.getMainBoard().getData().getCurrentPlayer()));
		g.sendAction(new EndTurnAction(g.getMainBoard().getData().getCurrentPlayer()));
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
