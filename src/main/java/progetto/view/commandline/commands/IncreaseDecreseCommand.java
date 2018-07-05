package progetto.view.commandline.commands;

import progetto.controller.ToolCardSetIncreaseDecreaseAction;
import progetto.controller.ToolCardSetPickedDiceAction;
import progetto.model.ToolCardParameters;
import progetto.view.commandline.CommandLineView;

/**
 * Command to increase or decrease the value of a dice when permitted by a tool card
 */
public class IncreaseDecreseCommand extends AbstractCLViewCommand {

    private int increaseDecrese;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     * @param increseDecrese information about the type of action this command needs to do
     *                       (increaseDecrease == 0, increase,
     *                       increaseDecrease == 1, decrease )
     */
    public IncreaseDecreseCommand(CommandLineView commandLineView, int increseDecrese) {
        super(commandLineView);
        this.increaseDecrese = increseDecrese;
    }

    /**
     * Send to the controller a ToolCardSetIncreaseDecreaseAction according to the increaseDecrease value received
     * @param params no input needed
     */
    @Override
    public void exec(String[] params) {
        getController().sendAction(new ToolCardSetIncreaseDecreaseAction(getController().getChair(), increaseDecrese));
    }

    /**
     * Returns infos about what this command does and about the user's choice
     * @return infos about what this command does and about the user's choiche
     */
    @Override
    public String getHelp() {
        ToolCardParameters toolCardParameters = getModel().getRoundInformation().getData().getToolCardParameters();
        String toReturn;
        if(increaseDecrese == 0){
            if (toolCardParameters.getIncreaseDecrease() == 0)
                toReturn = "Incrementa di 1 - Selezionato";
            else toReturn = "Incrementa di 1";
        }
        else {
            if (toolCardParameters.getIncreaseDecrease() == 1)
                toReturn = "Decrementa di 1 - Selezionato";
            else toReturn = "Decrementa di 1";
        }
        return toReturn;
    }
}
