package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.Printer;
import progetto.view.commandline.commands.ShowPlayerBoardCommand;

public class ShowPlayerBoardState extends AbstractCLViewState {

    public ShowPlayerBoardState(CommandLineView view) {
        super("show player board state", view);
    }

    @Override
    public void onApply() {

        Printer printer = new Printer();
        int numberOfPlayer = getView().getController().getModel().getMainBoard().getData().getPlayerCount();
        for(int i = 0; i<numberOfPlayer; i++){
            registerCommand(new ShowPlayerBoardCommand(getView(), i, printer, new GameTransitionState(getView())));
        }

    }

    @Override
    public String getMessage() {
        return "\nSeleziona il giocatore del quale vuoi vedere la scheda:\n";
    }
}
