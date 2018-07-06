package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;

/**
 * Command to update the list of the existing rooms
 * @author Federica
 */
public class UpdateCommand extends AbstractCLViewCommand {

    /**
     * public constructo
     * @param commandLineView the command line view that this command will modify
     */
    public UpdateCommand(CommandLineView commandLineView) { super(commandLineView); }

    /**
     * update the list of the existing rooms
     * @param params input not needed
     */
    @Override
    public void exec(String[] params) {
        getCommandLineView().getController().fetchCurrentState();
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Aggiorna elenco stanze";
    }
}
