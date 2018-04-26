package progetto.game;

import junit.framework.TestCase;

public class TestGame extends TestCase {

	public void test1()
	{

		Game game = new Game();

		CommandQueue commandQueue = game.getCommandQueue();

		RoundTrack roundTrack = game.getRoundTrack();

		PlayerBoard playerBoard0 = game.getPlayerBoard(0);
		PlayerBoard playerBoard1 = game.getPlayerBoard(1);

		MainBoard mainBoard = game.getMainBoard();

		DiceBag diceBag = game.getDiceBag();

		game.setSeed(0);

		assertEquals(0, game.getSeed());

	}

}
