package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.CreateRoomCommand;
import progetto.view.commandline.commands.ReturnCommand;
import progetto.view.commandline.commands.ShowRoomCommand;
import progetto.view.commandline.commands.UpdateCommand;

public class RoomsState extends AbstractCLViewState {

    public RoomsState(CommandLineView view) {
        super("RoomState", view);
    }

    @Override
    public void onApply() {

        registerCommand(new CreateRoomCommand(getView()));
        registerCommand(new ShowRoomCommand(getView()));
        registerCommand(new UpdateCommand(getView()));
        registerCommand(new ReturnCommand(getView(), new DefaultViewState(getView()), "Indietro"));

    }

    @Override
    public String getMessage() {
        return "\nSelezionare una tra le opzioni proposte:\n";
    }
}
