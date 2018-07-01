package progetto.view.commandline.states;

import progetto.model.FrameSelectionState;
import progetto.view.commandline.CommandLineView;

public class WaitingState extends AbstractCLViewState {

    public WaitingState(CommandLineView view) {
        super("waiting state", view);
    }

    @Override
    public boolean isStillValid() {
        return getModel().getMainBoard().getData().getGameState().getClass() == FrameSelectionState.class;
    }

    @Override
    public void onApply() {
        //Only transition state
    }

    @Override
    public String getMessage() {
        return "\nAspetta che tutti i giocatori scelgano la loro vetrata!";
    }
}
