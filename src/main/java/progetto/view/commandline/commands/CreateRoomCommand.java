package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.RoomsState;

/**
 * command to create a new room
 */
public class CreateRoomCommand extends AbstractCLViewCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public CreateRoomCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    /**
     * if params contains a valid string, create a new room with the input string as name
     * @param params input string by the user, it should be a valid name for a romm
     */
    @Override
    public void exec(String[] params) {

        if(params == null || params.length == 0){

            write("Inserire un nome valido per la stanza!\n");
            return;
        }

        getCommandLineView().getController().createGame(params[0]);
        getCommandLineView().getController().fetchCurrentState();
        write("\nLa stanza " + params[0] + " è stata creata!\n");
    }

    /**
     * return infos about what this command does and how the input should be written
     * @return infos about what this command does and how the input should be written
     */
    @Override
    public String getHelp() {
        return "Crea una nuova stanza (Formato: 1 <NomeStanza>)";
    }
}
