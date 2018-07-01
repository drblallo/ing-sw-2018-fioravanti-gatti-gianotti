package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.DifferenceDescriptor;
import progetto.view.commandline.commands.ReturnCommand;
import progetto.view.commandline.commands.ShowPlayerBoardRequestCommand;


public class WaitingTurnState extends AbstractCLViewState {

    private boolean notMyTurn = true;

    public WaitingTurnState(CommandLineView view) {
        super("waiting turn state", view);
    }

    @Override
    public boolean isStillValid() {
        return notMyTurn;
    }

    @Override
    public void addObservers() {

        getDifferenceDescriptors().add(
            new DifferenceDescriptor<>
            (
                getView().getController().getObservable().getRoundInformation(),
                (data1, data2) -> data2.getCurrentPlayer() == getView().getController().getChair(),
                (oldData, newData) -> notMyTurn = false
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
