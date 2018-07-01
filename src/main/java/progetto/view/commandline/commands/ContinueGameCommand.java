package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

public class ContinueGameCommand extends AbstractStateSwitcherCommand {

    private int numberOfConnection;

    public ContinueGameCommand(CommandLineView commandLineView, int numberOfConnection) {
        super(commandLineView, new GameTransitionState(commandLineView));
        this.numberOfConnection = numberOfConnection;
    }

    @Override
    protected void perform(String[] params) {
        getController().setCurrentClientGame(numberOfConnection);
    }

    @Override
    public String getHelp() {
        return getController().getNameOfConnection(numberOfConnection);
    }
}
