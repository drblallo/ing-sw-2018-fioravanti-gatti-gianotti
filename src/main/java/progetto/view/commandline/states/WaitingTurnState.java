package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.DifferenceDescriptor;
import progetto.view.commandline.commands.ReturnCommand;
import progetto.view.commandline.commands.ShowPlayerBoardRequestCommand;

/**
 * State where the user goes when he isn't the current player
 */
public class WaitingTurnState extends AbstractCLViewState {

    private boolean notMyTurn = true;

    /**
     * public constructor
     * @param view the command line view that this state will be applied to
     */
    public WaitingTurnState(CommandLineView view) {
        super("waiting turn state", view);
    }

    /**
     * Check if this state is still valid
     * @return if this state is still valid
     */
    @Override
    public boolean isStillValid() {
        return notMyTurn;
    }

    /**
     * add difference descriptors to this state
     */
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

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply() {

        registerCommand(new ShowPlayerBoardRequestCommand(getView()));
        registerCommand(new ReturnCommand(getView(), new ConfirmExitState(getView()), "Esci dal gioco"));

    }

    /**
     * Return a message associated to this stage
     * @return a message associated to this stage
     */
    @Override
    public String getMessage() {
        return "\nMentre attendi il tuo turno puoi fare una delle seguenti cose:\n";
    }
}
