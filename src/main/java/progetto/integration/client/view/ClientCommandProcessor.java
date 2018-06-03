package progetto.integration.client.view;

import progetto.controller.*;
import progetto.integration.client.ActionCommand;
import progetto.integration.client.ClientController;
import progetto.integration.client.StackableCommandProcessorBuilder;
import progetto.model.AddWindowFrameCoupleAction;
import progetto.model.EndTurnAction;
import progetto.model.FrameSetAction;
import progetto.view.commandline.EchoCommand;
import progetto.view.commandline.HelpCommand;
import progetto.view.commandline.StackCommandProcessor;

public class ClientCommandProcessor extends StackCommandProcessor
{
	private final ClientController controller;

	public ClientCommandProcessor(ClientController controller)
	{
		this.controller = controller;
	}

	public void reaload()
	{

		while (peekCommandProcessor() != null)
			popCommandProcessor();


		IGameController clientGame = controller;

		new StackableCommandProcessorBuilder(this, controller)
				.addNested("root", "main")
				.addNested("SuchExample", "Ti sto mostrando tale spledido esempio")
				.addReturn("indietro", "root")
				.getStackCommandProcessor();

		registerCommand(new EchoCommand());
		registerCommand(new HelpCommand(this));

		registerCommand(new ActionCommand(StartGameAction.class,clientGame));
		registerCommand(new ActionCommand(AddWindowFrameCoupleAction.class,clientGame));
		registerCommand(new ActionCommand(SetPlayerCountAction.class, clientGame));

		registerCommand(new ActionCommand(SetSeedAction.class, clientGame));
		registerCommand(new ActionCommand(FrameSetAction.class,clientGame));
		registerCommand(new ActionCommand(PickDiceAction.class, clientGame));
		registerCommand(new ActionCommand(PlaceDiceAction.class, clientGame));
		registerCommand(new ActionCommand(EndTurnAction.class, clientGame));
		registerCommand(new ActionCommand(ToolCardSetPickedDiceAction.class, clientGame));
		registerCommand(new ActionCommand(UseToolCardAction.class, clientGame));


	}
}
