package progetto.view.commandline.states;

import progetto.model.FrameSelectionState;
import progetto.view.commandline.CommandLineView;

/**
 * State where the user goes if he has selected his window frame and some other player hasn't yet
 */
public class WaitingState extends AbstractCLViewState {

    /**
     * public constructor
     * @param view the command line view that this state will be applied to
     */
    public WaitingState(CommandLineView view) {
        super("waiting state", view);
    }

    /**
     * Check if this state is still valid
     * @return if this state is still valid
     */
    @Override
    public boolean isStillValid() {
        return getModel().getMainBoard().getData().getGameState().getClass() == FrameSelectionState.class;
    }

    /**
     * Does not perform anything
     */
    @Override
    public void onApply() {
        //Only transition state
    }

    /**
     * Does not perform anything
     * @return nothing
     */
    @Override
    public String getMessage() {
        return "\nAspetta che tutti i giocatori scelgano la loro vetrata!";
    }
}
