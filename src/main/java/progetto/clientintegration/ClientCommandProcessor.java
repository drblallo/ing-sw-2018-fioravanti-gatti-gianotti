package progetto.clientintegration;

import progetto.commandline.CommandProcessor;
import progetto.commandline.EchoCommand;
import progetto.commandline.HelpCommand;
import progetto.game.*;

final class ClientCommandProcessor {

    private ClientCommandProcessor(){

        //constructor hiding

    }



    private static CommandProcessor processor = null;

    public static synchronized CommandProcessor getCommandProcessor() {

        if (processor == null) {

            processor = new CommandProcessor("main");
            processor.registerCommand(new EchoCommand());
            processor.registerCommand(new HelpCommand(processor));

            processor.registerCommand(new ActionCommand(StartGameAction.class, ClientMain.getGame()));
            processor.registerCommand(new ActionCommand(AddWindowFrameCoupleAction.class, ClientMain.getGame()));
            processor.registerCommand(new ActionCommand(SetPlayerCountAction.class, ClientMain.getGame()));

            processor.registerCommand(new ActionCommand(SetSeedAction.class, ClientMain.getGame()));
            processor.registerCommand(new ActionCommand(FrameSetAction.class, ClientMain.getGame()));
	        processor.registerCommand(new ActionCommand(PickDiceAction.class, ClientMain.getGame()));
	        processor.registerCommand(new ActionCommand(PlaceDiceAction.class, ClientMain.getGame()));
	        processor.registerCommand(new ActionCommand(EndTurnAction.class, ClientMain.getGame()));
        }

        return processor;

    }
}
