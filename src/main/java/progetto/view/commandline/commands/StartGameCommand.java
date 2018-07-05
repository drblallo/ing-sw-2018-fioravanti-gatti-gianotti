package progetto.view.commandline.commands;

import progetto.controller.StartGameAction;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

/**
 * Command to start the game
 */
public class StartGameCommand extends AbstractStateSwitcherCommand{

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public StartGameCommand(CommandLineView commandLineView) {
        super(commandLineView, new GameTransitionState(commandLineView));
    }

    /**
     * Send a StartGameAction to the controller
     * @param params input not needed
     */
    @Override
    protected void perform(String[] params) {
        getController().sendAction(new StartGameAction());
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Inizia partita";
    }
}
