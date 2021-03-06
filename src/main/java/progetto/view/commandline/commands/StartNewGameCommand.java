package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.DefaultViewState;

/**
 * Command to return to the DefaultViewState
 * @author Federica
 */
public class StartNewGameCommand extends AbstractStateSwitcherCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public StartNewGameCommand(CommandLineView commandLineView) {
        super(commandLineView, new DefaultViewState(commandLineView));
    }

    /**
     * Does not perform anything
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
        return "Inizia una nuova partita";
    }
}
