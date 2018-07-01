package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.PickDiceState;

public class ShowDicesCommand extends AbstractStateSwitcherCommand {

    public ShowDicesCommand(CommandLineView commandLineView) {
        super(commandLineView, new PickDiceState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getHelp() {
        return "Prendi un dado";
    }
}
