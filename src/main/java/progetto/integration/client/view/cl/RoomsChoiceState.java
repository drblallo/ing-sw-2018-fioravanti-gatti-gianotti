package progetto.integration.client.view.cl;

import progetto.network.ServerStateView;

public class RoomsChoiceState extends AbstractCLViewState {

    public RoomsChoiceState(CommandLineView view) {
        super("rooms choise state", view);
    }

    @Override
    public void onApply() {

        for (ServerStateView.SimpleRoomState s : getView().getController().getCurrentServerState().asList())
            registerCommand(new JoinRoomCommand(getView(), s));

        registerCommand(new ReturnCommand(getView(), new RoomsState(getView()), "Indietro"));

    }

    @Override
    public String getMessage() {

        return "\nScegliere una tra le stanze presenti:\n";

    }
}
