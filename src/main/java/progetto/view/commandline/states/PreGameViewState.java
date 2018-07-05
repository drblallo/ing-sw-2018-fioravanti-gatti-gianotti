package progetto.view.commandline.states;

import progetto.model.PreGameState;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.DifferenceDescriptor;
import progetto.view.commandline.commands.*;

/**
 * State shown at the beginning of a game
 */
public class PreGameViewState extends AbstractCLViewState {

    private int currentPlayerCount;

    /**
     * public constructor
     * @param view the command line view that this state will be applied to
     */
    public PreGameViewState(CommandLineView view) {
        super("PreGameViewState", view
        );
    }

    /**
     * Check if this state is still valid
     * @return if this state is still valid
     */
    @Override
    public boolean isStillValid() {
        return (getModel().getMainBoard().getData().getGameState().getClass() == PreGameState.class) &&
                (currentPlayerCount == getModel().getMainBoard().getData().getPlayerCount());
    }

    /**
     * add difference descriptors to this state
     */
    @Override
    public void addObservers() {

        getDifferenceDescriptors().add(
            new DifferenceDescriptor<>
            (
                getView().getController().getObservable().getMainBoard(),
                (data1, data2) -> data1.getPlayerCount()!=data2.getPlayerCount(),
                (oldData, newData) -> write("\nIl numero di giocatori è cambiato da " + oldData.getPlayerCount()
                        + " a " + newData.getPlayerCount() + "!\n")
            )
        );
    }

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply() {

        currentPlayerCount = getModel().getMainBoard().getData().getPlayerCount();
        registerCommand(new StartGameCommand(getView()));
        registerCommand(new SetNumberOfPlayersCommand(getView()));
        if (getModel().getMainBoard().getData().getPlayerCount() == 1)
            registerCommand(new SetSinglePlayerDifficultyCommand(getView()));
        registerCommand(new SelectChairCommand(getView()));
        registerCommand(new ReturnCommand(getView(),
                new DefaultViewState(getView()), "Indietro"));


    }

    /**
     * Return a message associated to this stage
     * @return a message associated to this stage
     */
    @Override
    public String getMessage() {
        return "\nSelezionare una tra le opzioni proposte:\n";
    }
}
