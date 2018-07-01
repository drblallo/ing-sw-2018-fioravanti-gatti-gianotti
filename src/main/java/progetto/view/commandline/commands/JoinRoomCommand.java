package progetto.view.commandline.commands;

import progetto.network.ServerStateView;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

public class JoinRoomCommand extends AbstractStateSwitcherCommand {

    private ServerStateView.SimpleRoomState simpleRoomState;

    public JoinRoomCommand(CommandLineView commandLineView, ServerStateView.SimpleRoomState simpleRoomState) {
        super(commandLineView, new GameTransitionState(commandLineView));
        this.simpleRoomState = simpleRoomState;
    }

    @Override
    protected void perform(String[] params) {

        getCommandLineView().getController().joinGame(simpleRoomState.roomID, getCommandLineView().getPlayerName());
    }

    @Override
    public String getHelp() {
        return "" + simpleRoomState.roomName + " - Numero partecipanti auttali: " + simpleRoomState.playerSize;
    }

}
