package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.SocketRMIChoiceState;

/**
 * Command to start a new game
 * @author Federica
 */
public class NewGameCommand extends AbstractStateSwitcherCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public NewGameCommand(CommandLineView commandLineView) {
        super(commandLineView, new SocketRMIChoiceState(commandLineView));
    }

    /**
     * Does not perform any action
     * @param params input not needed
     */
    @Override
    public void perform(String[] params) {
        //Only state switcher command
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Nuova partita";
    }
}
