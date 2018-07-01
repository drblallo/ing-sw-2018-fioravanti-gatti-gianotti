package progetto.view.commandline.commands;

import progetto.controller.StartGameAction;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

public class StartGameCommand extends AbstractStateSwitcherCommand{

    public StartGameCommand(CommandLineView commandLineView) {
        super(commandLineView, new GameTransitionState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {

        getController().sendAction(new StartGameAction());

    }

    @Override
    public String getHelp() {
        return "Inizia partita";
    }
}
