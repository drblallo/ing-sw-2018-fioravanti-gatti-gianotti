package progetto.integration.client.view.cl;

import progetto.view.commandline.DifferenceDescriptor;


public class WaitingTurnState extends AbstractCLViewState {

    private boolean myTurn = true;

    public WaitingTurnState(CommandLineView view) {
        super("waiting turn state", view);
    }

    @Override
    public boolean isStillValid() {
        return myTurn;
    }

    @Override
    public void addObservers() {

        getDifferenceDescriptors().add(
            new DifferenceDescriptor<>
            (
                getView().getController().getObservable().getRoundInformation(),
                (data1, data2) -> data2.getCurrentPlayer() == getView().getController().getChair(),
                (oldData, newData) -> myTurn = false
            )
        );

    }

    @Override
    public void onApply() {

        registerCommand(new ShowPlayerBoardRequestCommand(getView()));
        registerCommand(new ReturnCommand(getView(), new ConfirmExitState(getView()), "Esci dal gioco"));

    }

    @Override
    public String getMessage() {
        return "\nMentre attendi il tuo turno puoi fare una delle seguenti cose:\n";
    }
}
