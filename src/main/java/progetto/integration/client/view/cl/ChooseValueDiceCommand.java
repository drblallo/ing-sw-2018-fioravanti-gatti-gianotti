package progetto.integration.client.view.cl;

import progetto.controller.ToolCardSetDiceValueAction;

public class ChooseValueDiceCommand extends AbstractCLViewCommand {

    private int numberOfCommand;
    private static final int MAX_VALUE = 6;

    public ChooseValueDiceCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView);
        this.numberOfCommand = numberOfCommand;
    }

    @Override
    public void exec(String[] args) {

        String toReturn = "Inserire un valore valido!";

        if(args == null || args.length == 0){
            write(toReturn);
            return;
        }
        try {
            int value = Integer.parseInt(args[0]);

            if(value>0 && value <= MAX_VALUE){
                ToolCardSetDiceValueAction toolCardSetDiceValueAction =
                        new ToolCardSetDiceValueAction(getController().getChair(), value);
                if(toolCardSetDiceValueAction.canBeExecuted(getModel())){
                    getController().sendAction(toolCardSetDiceValueAction);
                    return;
                }
                else write(toReturn);
            }

        }catch (NumberFormatException e ){
            write(toReturn);
        }
    }

    @Override
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        return "Scegli il valore che vuoi assegnare al dado estratto (Formato: <" + numberOfCommand + " <Valore del dado>";
    }
}
