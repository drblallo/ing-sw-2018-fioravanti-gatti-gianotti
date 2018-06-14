package progetto.integration.client.view.cl;

import progetto.model.AbstractGameState;
import progetto.model.FrameSelectionState;
import progetto.model.PreGameState;
import progetto.model.RoundState;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameTransitionState extends AbstractCLViewState {

    private static final Logger LOGGER = Logger.getLogger(GameTransitionState.class.getName());

    public GameTransitionState(CommandLineView view) {
        super("game transition state", view);
    }

    @Override
    public void onApply() {

        AbstractGameState abstractGameState = getModel().getMainBoard().getData().getGameState();

        LOGGER.log(Level.INFO, "GameTransitionState - Actual state:  " + abstractGameState.getName());

        if(abstractGameState.getClass() == PreGameState.class)
            getView().setState(new PreGameViewState(getView()));

        else if(abstractGameState.getClass() == FrameSelectionState.class)
            getView().setState(new FrameSelectionViewState(getView()));

        else if(abstractGameState.getClass() == RoundState.class &&
                getController().getModel().getRoundInformation().getData().getCurrentPlayer() == getController().getChair())
            getView().setState(new RoundViewState(getView()));

        else if(abstractGameState.getClass() == RoundState.class)
            getView().setState(new WaitingTurnState(getView()));
    }

    @Override
    public String getMessage() {
        return "";
    }
}
