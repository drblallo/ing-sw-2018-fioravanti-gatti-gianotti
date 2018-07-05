package progetto.view.commandline.commands;

import progetto.network.ServerStateView;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

/**
 * Command to join a room
 */
public class JoinRoomCommand extends AbstractStateSwitcherCommand {

    private ServerStateView.SimpleRoomState simpleRoomState;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     * @param simpleRoomState the room that the perform of this command will join
     */
    public JoinRoomCommand(CommandLineView commandLineView, ServerStateView.SimpleRoomState simpleRoomState) {
        super(commandLineView, new GameTransitionState(commandLineView));
        this.simpleRoomState = simpleRoomState;
    }

    /**
     * join the room received in the constructor
     * @param params no input needed
     */
    @Override
    protected void perform(String[] params) {

        getCommandLineView().getController().joinGame(simpleRoomState.roomID, getCommandLineView().getPlayerName());
    }

    /**
     *Return the name of the room received in the constructor and the number of his members
     * @return the name of the room recived in the constructor and the number of his members
     */
    @Override
    public String getHelp() {
        return "" + simpleRoomState.roomName + " - Numero partecipanti auttali: " + simpleRoomState.playerSize;
    }

}
