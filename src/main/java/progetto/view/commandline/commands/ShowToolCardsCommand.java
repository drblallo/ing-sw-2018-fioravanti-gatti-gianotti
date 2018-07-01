package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.ShowToolCardState;

public class ShowToolCardsCommand extends AbstractStateSwitcherCommand {

    public ShowToolCardsCommand(CommandLineView commandLineView) {
        super(commandLineView, new ShowToolCardState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getHelp() {
        return "Mostra le carte utensili disponibili";
    }
}
