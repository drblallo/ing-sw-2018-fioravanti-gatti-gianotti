package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.ChairSelectionState;

public class SelectChairCommand extends AbstractStateSwitcherCommand {

    public SelectChairCommand(CommandLineView commandLineView) {
        super(commandLineView, new ChairSelectionState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getHelp() {
        return "Seleziona una sedia";
    }
}
