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

        int i = 1;

        registerCommand(new ShowPlayerBoardRequestCommand(getView(), i));
        i++;
        registerCommand(new ReturnCommand(getView(), new ConfirmExitState(getView()), i, "Esci dal gioco"));

    }

    @Override
    public String getMessage() {
        return "\nMentre attendi il tuo turno puoi fare una delle seguenti cose:\n";
    }
}
