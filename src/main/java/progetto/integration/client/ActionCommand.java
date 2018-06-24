package progetto.integration.client;

import progetto.controller.IGameController;
import progetto.model.AbstractGameAction;
import progetto.view.commandline.ICommand;

/**
 * A action command if a reflection based connection between action and commands.
 * You cannot decide anything about presentation, so it must be only used for debugging porpuses.
 *
 */
public final class ActionCommand implements ICommand {

	private final Class<? extends AbstractGameAction> actionClass;
	private final IGameController game;

	/**
	 * creates a new actionCommand that will spawn actions of the class provided
	 * @param actionName the class of which new actions will be spawned. Such class must provide a default constructor.
	 * @param game the game that will receive the messages.
	 */
	public ActionCommand(Class<? extends AbstractGameAction> actionName, IGameController game)
	{
		this.actionClass = actionName;
		this.game = game;
	}

	/**
	 * @return the name of the class received while being constructed
	 */
	@Override
	public String getName() {
		return actionClass.getSimpleName();
	}

	/**
	 *
	 * @return name plus the name of each of the fields of the class.
	 */
	@Override
	public String getHelp() {
		return AbstractGameAction.getStructure(actionClass);
	}

	/**
	 * tries to create the action and to send if to the game
	 * @param params each string must be the field in order of apparition in the target class
	 * @return the a confirmation or negation string
	 */
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
