package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.ShowPlayerBoardState;

public class ShowPlayerBoardRequestCommand extends AbstractStateSwitcherCommand {

    public ShowPlayerBoardRequestCommand(CommandLineView commandLineView) {
        super(commandLineView, new ShowPlayerBoardState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getHelp() {
        return "Vedi schede dei giocatori";
    }
}
