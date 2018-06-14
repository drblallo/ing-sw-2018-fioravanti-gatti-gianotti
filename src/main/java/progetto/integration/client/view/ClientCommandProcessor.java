package progetto.integration.client.view;

import progetto.controller.*;
import progetto.integration.client.ActionCommand;
import progetto.integration.client.ClientController;
import progetto.model.AddWindowFrameCoupleAction;
import progetto.view.commandline.CommandProcessor;
import progetto.view.commandline.EchoCommand;
import progetto.view.commandline.HelpCommand;

public class ClientCommandProcessor extends CommandProcessor
{

	public ClientCommandProcessor(ClientController controller)
	{
		super("Root");
		IGameController clientGame = controller;


		registerCommand(new EchoCommand());
		registerCommand(new HelpCommand(this));

		registerCommand(new ActionCommand(StartGameAction.class, clientGame));
		registerCommand(new ActionCommand(AddWindowFrameCoupleAction.class, clientGame));
		registerCommand(new ActionCommand(SetPlayerCountAction.class, clientGame));

		registerCommand(new ActionCommand(SetSeedAction.class, clientGame));
		registerCommand(new ActionCommand(FrameSetAction.class, clientGame));
		registerCommand(new ActionCommand(PickDiceAction.class, clientGame));
		registerCommand(new ActionCommand(PlaceDiceAction.class, clientGame));
		registerCommand(new ActionCommand(EndTurnAction.class, clientGame));
		registerCommand(new ActionCommand(ToolCardSetPickedDiceAction.class, clientGame));
		registerCommand(new ActionCommand(UseToolCardAction.class, clientGame));

	}
}
