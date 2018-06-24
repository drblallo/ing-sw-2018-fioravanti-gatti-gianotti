package progetto.integration.client.view.cl;

import progetto.controller.StartGameAction;

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
