package progetto.view.commandline.commands;

import progetto.controller.CancelToolCardUseAction;
import progetto.view.commandline.CommandLineView;

/**
 * Command to cancel the use of a tool card
 * @author Federica
 */
public class CancelUseOfToolCardCommand extends AbstractCLViewCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public CancelUseOfToolCardCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    /**
     * Send the CancelToolCardUseAction to the controller
     * @param params not needed
     */
    @Override
    public void exec(String[] params) {
        getController().sendAction(new CancelToolCardUseAction(getController().getChair()));
    }

    /**
     * Return some infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Annulla uso della carta scelta";
    }
}
