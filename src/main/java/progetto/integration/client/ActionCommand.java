package progetto.integration.client;

import progetto.controller.IGameController;
import progetto.model.AbstractGameAction;
import progetto.view.commandline.ICommand;

public final class ActionCommand implements ICommand {

	private final Class<? extends AbstractGameAction> actionClass;
	private final IGameController game;

	public ActionCommand(Class<? extends AbstractGameAction> actionName, IGameController game)
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

		int[] t = new int[params.length];
		for (int a = 0; a < params.length; a++)
		{

			try
			{
				t[a] = Integer.parseInt(params[a]);
			}
			catch (Exception e)
			{
				return "Parameter "+ a + " invalid: "+ params[a];
			}
		}
		AbstractGameAction actionCommand = AbstractGameAction.createAction(actionClass, t);
		if (actionCommand != null)
		{
			game.sendAction(actionCommand);
			game.processAllPendingAction();
			return "Sent command";
		}

		return "Failed to send command";
	}
}
