package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

/**
 * Command to sit on a chair
 */
public class SitOnChairCommand extends AbstractStateSwitcherCommand {

    private int chair;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     * @param chair the number of the chair associated to this command
     */
    public SitOnChairCommand(CommandLineView commandLineView, int chair) {
        super(commandLineView, new GameTransitionState(commandLineView));
        this.chair = chair;
    }

    /**
     * Pick the selected chair
     * @param params input not needed
     */
    @Override
    protected void perform(String[] params) {
        getCommandLineView().getController().pickChair(chair);
    }

    /**
     * Return the number of the chair associated to this command
     * @return the number of the chair associated to this command
     */
    @Override
    public String getHelp() {
        return "Sedia numero " + chair;
    }
}
