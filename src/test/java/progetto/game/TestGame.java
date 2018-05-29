package progetto.game;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGame extends TestCase {

	Game game;

	@Before
	public void setUp()
	{
		game = new Game();
	}

	@Test
	public void testGetter()
	{

		AbstractProcessor commandQueue = game.getActionQueue();

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
		Game g = new Game();
		Game g2 = new Game();

		Assert.assertFalse(g.equals(null));
		Assert.assertFalse(g.equals(this));
		Assert.assertTrue(g.equals(g2));
	}
}
