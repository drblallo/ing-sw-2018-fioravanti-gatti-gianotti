package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

public class SitOnChairCommand extends AbstractStateSwitcherCommand {

    private int chair;

    public SitOnChairCommand(CommandLineView commandLineView, int chair) {
        super(commandLineView, new GameTransitionState(commandLineView));
        this.chair = chair;
    }

    @Override
    protected void perform(String[] params) {

        getCommandLineView().getController().pickChair(chair);
    }

    @Override
    public String getHelp() {
        return "Sedia numero " + chair;
    }
}
