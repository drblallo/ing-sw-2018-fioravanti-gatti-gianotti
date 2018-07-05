package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.ContinueGameState;

/**
 * Command to go in the ContinueGameState
 */
public class ContinueCommand extends AbstractStateSwitcherCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public ContinueCommand(CommandLineView commandLineView) {
        super(commandLineView, new ContinueGameState(commandLineView));
    }

    /**
     * Does not perform any action
     * @param params input not needed
     */
    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Continua partita";
    }
}
