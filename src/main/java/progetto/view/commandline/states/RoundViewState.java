package progetto.view.commandline.states;

import progetto.model.RoundState;
import progetto.model.ToolCardParameters;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.*;

/**
 * Stage shown when the user is the current player
 */
public class RoundViewState extends AbstractCLViewState {

    /**
     * public constructor
     * @param view the command line view that this state will be applied to
     */
    public RoundViewState(CommandLineView view) {
        super("round view state", view);
    }

    /**
     * Check if this state is still valid
     * @return if this state is still valid
     */
    @Override
    public boolean isStillValid() {
        return getModel().getMainBoard().getData().getGameState().getClass() == RoundState.class
                && getController().getChair() == getController().getModel()
                .getRoundInformation().getData().getCurrentPlayer();
    }

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply() {

        ToolCardParameters toolCardParameters = getModel().getRoundInformation().getData().getToolCardParameters();
        if (toolCardParameters.getDice()!=null)
            registerCommand(new ChooseValueDiceCommand(getView()) );
        registerCommand(new ShowDicesCommand(getView()));
        registerCommand(new ShowPlayerBoardRequestCommand(getView()));
        registerCommand(new PlaceDiceCommand(getView()));
        registerCommand(new ShowPickedDicesCommand(getView()));
        registerCommand(new ShowToolCardsCommand(getView()));
        registerCommand(new ShowObjectivesCommand(getView()));
        registerCommand(new ShowRoundTrackCommand(getView()));
        registerCommand(new EndTurnCommand(getView()));
        registerCommand(new SelectChairCommand(getView()));
        registerCommand(new ReturnCommand(getView(), new ConfirmExitState(getView()), "Esci dalla partita"));
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
