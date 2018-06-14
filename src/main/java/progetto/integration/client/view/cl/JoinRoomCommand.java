package progetto.integration.client.view.cl;

import progetto.network.ServerStateView;

public class JoinRoomCommand extends AbstractStateSwitcherCommand {

    private int number;
    private ServerStateView.SimpleRoomState simpleRoomState;

    public JoinRoomCommand(CommandLineView commandLineView,
                           int number, ServerStateView.SimpleRoomState simpleRoomState) {
        super(commandLineView, new GameTransitionState(commandLineView));
        this.number = number;
        this.simpleRoomState = simpleRoomState;
    }

    @Override
    protected void perform(String[] params) {

        getCommandLineView().getController().joinGame(simpleRoomState.roomID, getCommandLineView().getPlayerName());
    }

    @Override
    public String getName() {
        return "" + number;
    }

    @Override
    public String getHelp() {
        return "" + simpleRoomState.roomName + " - Numero partecipanti auttali: " + simpleRoomState.playerSize;
    }

}
