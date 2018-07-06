package progetto.view.commandline.commands;

import progetto.controller.FrameSetAction;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.WaitingState;

/**
 * Command to select a window frame at the beginning of the game
 * @author Federica
 */
public class SelectWindowCommand extends AbstractStateSwitcherCommand {

    private int number;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     * @param number the number of the window frame associated to this command
     */
    public SelectWindowCommand(CommandLineView commandLineView, int number) {
        super(commandLineView, new WaitingState(commandLineView));
        this.number = number;
    }

    /**
     * Send a FrameSetAction to the controller
     * @param params input not needed
     */
    @Override
    protected void perform(String[] params) {
        getController().sendAction(new FrameSetAction(getController().getChair(),
                (number-1)/2, (number-1)%2));
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Scegli vetrata numero " + getName();
    }
}
