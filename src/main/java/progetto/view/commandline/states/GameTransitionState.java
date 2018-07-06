package progetto.view.commandline.states;

import progetto.model.*;
import progetto.view.commandline.CommandLineView;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Particular stage where during the game the right state is selected
 * This is the state where the command line view goes when a state become "not still valid"
 */
public class GameTransitionState extends AbstractCLViewState {

    private static final Logger LOGGER = Logger.getLogger(GameTransitionState.class.getName());

    /**
     * public constructor
     * @param view the command line view that this state will be applied to
     */
    public GameTransitionState(CommandLineView view) {
        super("game transition state", view);
    }

    /**
     * Check the game state from the model and set as a consequence the right state of the command line view
     */
    @Override
    public void onApply() {

        AbstractGameState abstractGameState = getModel().getMainBoard().getData().getGameState();

        LOGGER.log(Level.INFO, "GameTransitionState - Actual state:  " + abstractGameState.getName());

        if(abstractGameState.getClass() == PreGameState.class)
            getView().setState(new PreGameViewState(getView()));
        else if(abstractGameState.getClass() == FrameSelectionState.class)
            getView().setState(new FrameSelectionViewState(getView()));
        else if(abstractGameState.getClass() == RoundState.class && getController().getModel()
                .getRoundInformation().getData().getCurrentPlayer() == getController().getChair())
            getView().setState(new RoundViewState(getView()));
        else if(abstractGameState.getClass() == RoundState.class)
            getView().setState(new WaitingTurnState(getView()));
        else if (abstractGameState.getClass() == ToolCardState.class)
            getView().setState(new UseToolCardState(getView()));
        else if(abstractGameState.getClass() == EndGameState.class)
            getView().setState(new EndGameViewState(getView()));
    }

    /**
     * Does not perform anything
     * @return nothing
     */
    @Override
    public String getMessage() {
        return "";
    }
}
