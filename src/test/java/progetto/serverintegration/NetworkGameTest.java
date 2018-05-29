package progetto.serverintegration;


import org.junit.Assert;
import org.junit.Test;
import progetto.clientintegration.AbstractGameTest;
import progetto.clientintegration.ClientGame;
import progetto.game.FrameSetAction;
import progetto.game.RoundState;
import progetto.game.StartGameAction;

public class NetworkGameTest extends AbstractGameTest {

	public NetworkGameTest()
	{
		super(new NetworkExecuibleGameFactory());
	}

	@Test
	public void testAbstractGameSync()
	{
		ServerGame game = new ServerGame();
		Assert.assertFalse(game.isItemGood(null, -1));

		Assert.assertFalse(game.isItemGood(new StartGameAction(), 2));
	}

}
