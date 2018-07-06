package progetto.view.commandline.states;

import progetto.network.ServerStateView;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.JoinRoomCommand;
import progetto.view.commandline.commands.ReturnCommand;

import java.util.List;

/**
 * State where the user can see the existing rooms and join one of them
 */
public class RoomsChoiceState extends AbstractCLViewState {

    /**
     * public constructor
     * @param view the command line view that this state will be applied to
     */
    public RoomsChoiceState(CommandLineView view) {
        super("rooms choice state", view);
    }

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply() {

        List<ServerStateView.SimpleRoomState> simpleRoomStateList = getController().getCurrentServerState().asList();
        for (int i = 1; i< simpleRoomStateList.size(); i++ )
            registerCommand(new JoinRoomCommand(getView(), simpleRoomStateList.get(i)));

        registerCommand(new ReturnCommand(getView(), new RoomsState(getView()), "Indietro"));

    }

    /**
     * Return a message associated to this stage
     * @return a message associated to this stage
     */
    @Override
    public String getMessage() {

        return "\nScegliere una tra le stanze presenti:\n";

    }
}
