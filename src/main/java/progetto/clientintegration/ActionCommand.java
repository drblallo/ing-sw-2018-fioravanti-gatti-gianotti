package progetto.clientintegration;

import progetto.commandline.ICommand;
import progetto.game.AbstractGameAction;
import progetto.game.IExecuibleGame;

final class ActionCommand implements ICommand {

	private final Class<? extends AbstractGameAction> actionClass;
	private final IExecuibleGame game;

	public ActionCommand(Class<? extends AbstractGameAction> actionName, IExecuibleGame game)
	{
		this.actionClass = actionName;
		this.game = game;
	}

	@Override
	public String getName() {
		return actionClass.getSimpleName();
	}

	@Override
	public String getHelp() {
		return AbstractGameAction.getStructure(actionClass);
	}

	@Override
	public String execute(String[] params)
	{

		if(params == null){

			return "Missing arguments";

		}

		int[] t = new int[params.length];
		for (int a = 0; a < params.length; a++)
			t[a] = Integer.parseInt(params[a]);

		AbstractGameAction actionCommand = AbstractGameAction.createAction(actionClass, t);
		if (actionCommand != null)
		{
			game.sendAction(actionCommand);
			game.processAllPendingAction();
			return "Sent command";
		}
		else
		{
			return "Failed to send command";
		}
	}
}
