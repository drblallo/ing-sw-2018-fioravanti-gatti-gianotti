package progetto.view.commandline.states;

import progetto.network.ServerStateView;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.JoinRoomCommand;
import progetto.view.commandline.commands.ReturnCommand;

import java.util.List;

public class RoomsChoiceState extends AbstractCLViewState {

    public RoomsChoiceState(CommandLineView view) {
        super("rooms choise state", view);
    }

    @Override
    public void onApply() {

        List<ServerStateView.SimpleRoomState> simpleRoomStateList = getController().getCurrentServerState().asList();
        for (int i = 1; i< simpleRoomStateList.size(); i++ )
            registerCommand(new JoinRoomCommand(getView(), simpleRoomStateList.get(i)));

        registerCommand(new ReturnCommand(getView(), new RoomsState(getView()), "Indietro"));

    }

    @Override
    public String getMessage() {

        return "\nScegliere una tra le stanze presenti:\n";

    }
}
