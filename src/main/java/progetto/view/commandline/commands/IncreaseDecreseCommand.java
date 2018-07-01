package progetto.view.commandline.commands;

import progetto.controller.ToolCardSetIncreaseDecreaseAction;
import progetto.controller.ToolCardSetPickedDiceAction;
import progetto.model.ToolCardParameters;
import progetto.view.commandline.CommandLineView;

public class IncreaseDecreseCommand extends AbstractCLViewCommand {

    private int increaseDecrese;

    public IncreaseDecreseCommand(CommandLineView commandLineView, int increseDecrese) {
        super(commandLineView);
        this.increaseDecrese = increseDecrese;
    }

    @Override
    public void exec(String[] params) {

        getController().sendAction(new ToolCardSetPickedDiceAction(getController().getChair(), 0));
        getController().sendAction(new ToolCardSetIncreaseDecreaseAction(getController().getChair(), increaseDecrese));

    }

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
