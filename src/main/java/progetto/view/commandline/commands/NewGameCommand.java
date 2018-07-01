package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.SocketRMIChoiceState;

public class NewGameCommand extends AbstractStateSwitcherCommand {

    public NewGameCommand(CommandLineView commandLineView) {
        super(commandLineView, new SocketRMIChoiceState(commandLineView));
    }

    @Override
    public String getHelp() {
        return "Nuova partita";
    }

    @Override
    public void perform(String[] params) {
        //Only state switcher command
    }
}
