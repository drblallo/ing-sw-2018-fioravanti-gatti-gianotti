package progetto.integration.client.view.cl;

import progetto.model.RoundState;

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

        registerCommand(new ShowDicesCommand(getView()));
        registerCommand(new ShowPlayerBoardRequestCommand(getView()));
        registerCommand(new PlaceDiceCommand(getView()));
        registerCommand(new ShowPickedDicesCommand(getView()));
        registerCommand(new ShowToolCardsCommand(getView()));
        registerCommand(new ShowObjectivesCommand(getView()));
        registerCommand(new ShowRoundTrackCommand(getView()));
        registerCommand(new EndTurnCommand(getView()));
        registerCommand(new ReturnCommand(getView(), new ConfirmExitState(getView()), "Esci dalla partita"));
        registerCommand(new CloseGameCommand(getView()));
    }

    @Override
    public String getMessage() {
        return "";
    }
}
