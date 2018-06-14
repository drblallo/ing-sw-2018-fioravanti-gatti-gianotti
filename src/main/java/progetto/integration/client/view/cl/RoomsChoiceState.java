package progetto.integration.client.view.cl;

import progetto.network.ServerStateView;

public class RoomsChoiceState extends AbstractCLViewState {

    public RoomsChoiceState(CommandLineView view) {
        super("rooms choise state", view);
    }

    @Override
    public void onApply() {

        int i = 0;

        for (ServerStateView.SimpleRoomState s : getView().getController().getCurrentServerState().asList()
             ) {

            registerCommand(new JoinRoomCommand(getView(), i, s));
            i++;

        }

        registerCommand(new ReturnCommand(getView(), new RoomsState(getView()), i, "Indietro"));

    }

    @Override
    public String getMessage() {

        return "\nScegliere una tra le stanze presenti:\n";

    }
}
