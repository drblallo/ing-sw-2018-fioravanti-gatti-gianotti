package progetto.integration.client;

import org.junit.Assert;
import org.junit.Test;
import progetto.controller.GameController;
import progetto.controller.StartGameAction;
import progetto.model.AddWindowFrameCoupleAction;
import progetto.model.FrameSelectionState;
import progetto.model.WindowFrameCoupleArray;

public class TestActionCommand {
	@Test
	public void testActionCommand()
	{
		GameController game = new GameController();
		WindowFrameCoupleArray windowFrameCoupleArray = new WindowFrameCoupleArray();
		for(int i=0; i<windowFrameCoupleArray.getWindowFrameCouples().size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCoupleArray.getWindowFrameCouples().get(i)));
		}
		game.processAllPendingAction();
		ActionCommand command = new ActionCommand(StartGameAction.class, game);
		Assert.assertEquals(command.getName(), StartGameAction.class.getSimpleName());
		Assert.assertEquals("StartGameAction <playerID> ", command.getHelp());
		String[] s = {"-1"};
		Assert.assertEquals("Sent command", command.execute(s));
		game.processAllPendingAction();
		Assert.assertEquals(FrameSelectionState.class, game.getModel().getMainBoard().getData().getGameState().getClass());
		s = new String[0];
		command.execute(s);
		String[] s2 = {"1-"};

		Assert.assertEquals("Parameter 0 invalid: 1-", command.execute(s2));
	}
}
