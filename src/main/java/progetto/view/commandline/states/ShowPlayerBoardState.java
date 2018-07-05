package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.Printer;
import progetto.view.commandline.commands.ShowPlayerBoardCommand;

/**
 * State where the user can ask too see the playerboard of a player
 */
public class ShowPlayerBoardState extends AbstractCLViewState {

    /**
     * public constructor
     * @param view the command line view that this state will be applied to
     */
    public ShowPlayerBoardState(CommandLineView view) {
        super("show player board state", view);
    }

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply() {

        Printer printer = new Printer();
        int numberOfPlayer = getView().getController().getModel().getMainBoard().getData().getPlayerCount();
        for(int i = 0; i<numberOfPlayer; i++){
            registerCommand(new ShowPlayerBoardCommand(getView(), i, printer, new GameTransitionState(getView())));
        }

    }

    /**
     * Return a message associated to this stage
     * @return a message associated to this stage
     */
    @Override
    public String getMessage() {
        return "\nSeleziona il giocatore del quale vuoi vedere la scheda:\n";
    }
}
