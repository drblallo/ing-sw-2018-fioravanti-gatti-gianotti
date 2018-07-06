package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;

/**
 * Command to close the game
 * @author Federica
 */
public class CloseGameCommand extends AbstractCLViewCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public CloseGameCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    /**
     * shut down the controller
     * @param args no input required
     */
    @Override
    public void exec(String[] args) {
        getController().shutDown();
    }

    /**
     * Return info about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Spegni il gioco";
    }
}
