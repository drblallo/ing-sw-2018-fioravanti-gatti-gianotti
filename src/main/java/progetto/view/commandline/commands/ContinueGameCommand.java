package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

/**
 * Command to continue a past game
 * @author Federica
 */
public class ContinueGameCommand extends AbstractStateSwitcherCommand {

    private int numberOfConnection;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     * @param numberOfConnection the number of connection associated to this command
     */
    public ContinueGameCommand(CommandLineView commandLineView, int numberOfConnection) {
        super(commandLineView, new GameTransitionState(commandLineView));
        this.numberOfConnection = numberOfConnection;
    }

    /**
     * set the numberOfConnection received in the constructor as the current client connection
     * @param params input by the user ( it could be null for some commands )
     */
    @Override
    protected void perform(String[] params) {
        getController().setCurrentClientGame(numberOfConnection);
    }

    /**
     * Return the name of the connection received in the constructor
     * @return the name of the connection received in the constructor
     */
    @Override
    public String getHelp() {
        return getController().getNameOfConnection(numberOfConnection);
    }
}
