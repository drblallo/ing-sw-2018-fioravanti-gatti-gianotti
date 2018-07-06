package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.CreateRoomCommand;
import progetto.view.commandline.commands.ReturnCommand;
import progetto.view.commandline.commands.ShowRoomCommand;
import progetto.view.commandline.commands.UpdateCommand;

/**
 * State where the user can create rooms or ask to see the existing ones
 */
public class RoomsState extends AbstractCLViewState {

    /**
     * public constructor
     * @param view the command line view that this state will be applied to
     */
    public RoomsState(CommandLineView view) {
        super("RoomState", view);
    }

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply() {

        registerCommand(new CreateRoomCommand(getView()));
        registerCommand(new ShowRoomCommand(getView()));
        registerCommand(new UpdateCommand(getView()));
        registerCommand(new ReturnCommand(getView(), new DefaultViewState(getView()), "Indietro"));

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