package progetto.view.commandline.states;

import progetto.model.PreGameState;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.DifferenceDescriptor;
import progetto.view.commandline.commands.ReturnCommand;
import progetto.view.commandline.commands.SelectChairCommand;
import progetto.view.commandline.commands.SetNumberOfPlayersCommand;
import progetto.view.commandline.commands.StartGameCommand;

public class PreGameViewState extends AbstractCLViewState {

    public PreGameViewState(CommandLineView view) {
        super("PreGameViewState", view
        );
    }

    @Override
    public boolean isStillValid() {
        return getModel().getMainBoard().getData().getGameState().getClass() == PreGameState.class;
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

        registerCommand(new StartGameCommand(getView()));
        registerCommand(new SetNumberOfPlayersCommand(getView()));
        registerCommand(new SelectChairCommand(getView()));
        registerCommand(new ReturnCommand(getView(),
                new DefaultViewState(getView()), "Indietro"));


    }

    @Override
    public String getMessage() {
        return "\nSelezionare una tra le opzioni proposte:\n";
    }
}
