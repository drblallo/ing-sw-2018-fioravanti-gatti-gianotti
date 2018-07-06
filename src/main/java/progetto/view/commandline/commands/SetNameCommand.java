package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.DefaultViewState;

/**
 * Command to set the name of the user
 * @author Federica
 */
public class SetNameCommand extends AbstractCLViewCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public SetNameCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    /**
     * if params contains a valid name for a user, sets this name as the user's one
     * @param params input by the user, it should contains a valid name for a user
     */
    @Override
    public void exec(String[] params) {

        if(params == null || params.length == 0){
            write("Inserire un nome valido!\n");
            return;
        }

        getCommandLineView().setPlayerName(params[0]);
        write("\nNome corrente: " + getCommandLineView().getPlayerName() + "\n");
        setState(new DefaultViewState(getCommandLineView()));
    }

    /**
     * Return infos about what this command does and how the input should be written
     * @return infos about what this command does and how the input should be writte
     */
    @Override
    public String getHelp() {
        return "Inserisci nome (Formato: 3 <Nome>)";
    }
}
