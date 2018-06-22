package progetto.integration.client.view.cl;

import progetto.controller.ToolCardSetIncreaseDecreaseAction;
import progetto.controller.ToolCardSetPickedDiceAction;

public class IncreaseDecreseCommand extends AbstractCLViewCommand {

    private int increaseDecrese;
    private int numberOfCommand;

    public IncreaseDecreseCommand(CommandLineView commandLineView, int increseDecrese, int numberOfCommand) {
        super(commandLineView);
        this.increaseDecrese = increseDecrese;
        this.numberOfCommand = numberOfCommand;
    }

    @Override
    public void exec(String[] params) {

        getController().sendAction(new ToolCardSetPickedDiceAction(getController().getChair(), 0));
        getController().sendAction(new ToolCardSetIncreaseDecreaseAction(getController().getChair(), increaseDecrese));

    }

    @Override
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        if(increaseDecrese == 0)
            return "Incrementa di 1";
        return "Decrementa di 1";
    }
}
