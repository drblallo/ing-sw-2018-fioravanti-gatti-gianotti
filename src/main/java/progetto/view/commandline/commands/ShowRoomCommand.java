package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.RoomsChoiceState;

public class ShowRoomCommand extends AbstractStateSwitcherCommand {

    public ShowRoomCommand(CommandLineView commandLineView) {
        super(commandLineView, new RoomsChoiceState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getHelp() {
        return "Entra in una stanza esistente";
    }
}
