package progetto.game;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class TestGame extends TestCase {

	public void test1()
	{

		Game game = new Game();

		AbstractProcessor commandQueue = game.getCommandQueue();

		RoundTrack roundTrack = game.getRoundTrack();

		PlayerBoard playerBoard0 = game.getPlayerBoard(0);
		PlayerBoard playerBoard1 = game.getPlayerBoard(1);

		MainBoard mainBoard = game.getMainBoard();

		DiceBag diceBag = game.getDiceBag();

		game.setSeed(0);

		assertEquals(0, game.getSeed());

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