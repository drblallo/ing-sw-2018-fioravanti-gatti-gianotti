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

        int i = 1;
        registerCommand(new ShowDicesCommand(getView(), i));
        i++;
        registerCommand(new ShowPlayerBoardRequestCommand(getView(),i));
        i++;
        registerCommand(new PlaceDiceCommand(getView(),i));
        i++;
        registerCommand(new ShowPickedDicesCommand(getView(), i));
        i++;
        registerCommand(new ShowToolCardsCommand(getView(),i));
        i++;
        registerCommand(new ShowObjectivesCommand(getView(), i));
        i++;
        registerCommand(new ShowRoundTrackCommand(getView(), i));
        i++;
        registerCommand(new EndTurnCommand(getView(),i));
        i++;
        registerCommand(new ReturnCommand(getView(), new ConfirmExitState(getView()), i, "Esci dalla partita"));
        i++;
        registerCommand(new CloseGameCommand(getView(), i));
    }

    @Override
    public String getMessage() {
        return "";
    }
}
