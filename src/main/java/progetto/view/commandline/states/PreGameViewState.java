package progetto.view.commandline.states;

import progetto.model.PreGameState;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.DifferenceDescriptor;
import progetto.view.commandline.commands.*;

public class PreGameViewState extends AbstractCLViewState {

    private int currentPlayerCount;

    public PreGameViewState(CommandLineView view) {
        super("PreGameViewState", view
        );
    }

    @Override
    public boolean isStillValid() {
        return (getModel().getMainBoard().getData().getGameState().getClass() == PreGameState.class) &&
                (currentPlayerCount == getModel().getMainBoard().getData().getPlayerCount());
    }

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

    @Override
    public String getMessage() {
        return "\nSelezionare una tra le opzioni proposte:\n";
    }
}
