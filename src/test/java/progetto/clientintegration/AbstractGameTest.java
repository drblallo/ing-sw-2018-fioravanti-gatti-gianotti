package progetto.clientintegration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.game.*;
import progetto.utils.Waiter;

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
		Assert.assertEquals(FrameSelectionState.class, game.getMainBoard().getMainBoardData().getGameState().getClass());

		game.getActionQueue();
		game.getDiceBag();
		game.getMainBoard().getMainBoardData().getGameState();
		game.getMainBoard();
		game.getPlayerBoard(0);
		game.getMainBoard().getMainBoardData().getPlayerCount();
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
		Assert.assertEquals(3, game.getMainBoard().getMainBoardData().getPlayerCount());
	}

	public void wait(int milliseconds)
	{
		steven.wait(milliseconds);
	}
}
