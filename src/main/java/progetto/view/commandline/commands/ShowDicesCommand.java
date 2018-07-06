package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.PickDiceState;

/**
 * Command to show extracted dices
 * @author Federica
 */
public class ShowDicesCommand extends AbstractStateSwitcherCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public ShowDicesCommand(CommandLineView commandLineView) {
        super(commandLineView, new PickDiceState(commandLineView));
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
        return "Prendi un dado";
    }
}
