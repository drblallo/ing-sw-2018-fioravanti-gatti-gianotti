package progetto.serverintegration;


import org.junit.Assert;
import org.junit.Test;
import progetto.clientintegration.AbstractGameTest;
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
		Assert.assertFalse(game.isStringGood(null, -1));

		Assert.assertFalse(game.isStringGood(new StartGameAction(), 2));
	}
}
