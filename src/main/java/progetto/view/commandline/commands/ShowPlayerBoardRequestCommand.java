package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.ShowPlayerBoardState;

/**
 * Command to go to the ShowPlayerBoardState
 */
public class ShowPlayerBoardRequestCommand extends AbstractStateSwitcherCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public ShowPlayerBoardRequestCommand(CommandLineView commandLineView) {
        super(commandLineView, new ShowPlayerBoardState(commandLineView));
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
        return "Vedi schede dei giocatori";
    }
}
