package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.AbstractCLViewState;

/**
 * Command to return to a specified state
 */
public class ReturnCommand extends AbstractStateSwitcherCommand {

    private String name;

    /**
     * Public constructor
     * @param commandLineView the command line view that this command will modify
     * @param abstractCLViewState the state to return to
     * @param name the infos given to the user about this command
     */
    public ReturnCommand(CommandLineView commandLineView, AbstractCLViewState abstractCLViewState, String name) {
        super(commandLineView, abstractCLViewState);
        this.name = name;
    }

    /**
     * Does not perform anything
     * @param params not needed
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
        return name;
    }
}
