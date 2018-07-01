package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.ContinueGameState;

public class ContinueCommand extends AbstractStateSwitcherCommand {

    public ContinueCommand(CommandLineView commandLineView) {
        super(commandLineView, new ContinueGameState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getHelp() {
        return "Continua partita";
    }
}
