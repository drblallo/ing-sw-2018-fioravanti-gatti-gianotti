package progetto.view.commandline.states;

import progetto.model.RoundState;
import progetto.model.ToolCardParameters;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.*;

public class RoundViewState extends AbstractCLViewState {


    public RoundViewState(CommandLineView view) {
        super("round view state", view);
    }

    @Override
    public boolean isStillValid() {
        return getModel().getMainBoard().getData().getGameState().getClass() == RoundState.class
                && getController().getChair() == getController().getModel()
                .getRoundInformation().getData().getCurrentPlayer();
    }

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

    @Override
    public String getMessage() {
        return "";
    }
}
