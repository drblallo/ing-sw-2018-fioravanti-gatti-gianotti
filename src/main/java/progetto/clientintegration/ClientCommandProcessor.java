package progetto.clientintegration;

import progetto.commandline.CommandProcessor;
import progetto.commandline.EchoCommand;
import progetto.commandline.HelpCommand;

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

        }

        return processor;

    }
}
