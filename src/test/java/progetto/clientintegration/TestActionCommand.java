package progetto.clientintegration;

import org.junit.Assert;
import org.junit.Test;
import progetto.clientintegration.ActionCommand;
import progetto.game.FrameSelectionState;
import progetto.game.Game;
import progetto.game.StartGameAction;

public class TestActionCommand {
	@Test
	public void testActionCommand()
	{
		Game game = new Game();
		ActionCommand command = new ActionCommand(StartGameAction.class, game);
		Assert.assertEquals(command.getName(), StartGameAction.class.getSimpleName());
		Assert.assertEquals("StartGameAction <playerID> ", command.getHelp());
		String[] s = {"-1"};
		Assert.assertEquals("sent command", command.execute(s));
		game.processAllPendingAction();
		Assert.assertEquals(FrameSelectionState.class, game.getMainBoard().getData().getGameState().getClass());
		s = new String[0];
		command.execute(s);
	}
}
