package progetto.view.commandline.commands;

import progetto.controller.EndTurnAction;
import progetto.view.commandline.CommandLineView;

/**
 * Command to end the turn
 */
public class EndTurnCommand extends AbstractCLViewCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public EndTurnCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    /**
     * Send to the controller an EndTurnAction
     * @param args input not needed
     */
    @Override
    public void exec(String[] args) {
        getController().sendAction(new EndTurnAction(getController().getChair()));
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Finisci il tuo turno";
    }
}
