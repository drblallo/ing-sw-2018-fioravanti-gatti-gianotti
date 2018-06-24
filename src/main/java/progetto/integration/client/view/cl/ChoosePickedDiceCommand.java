package progetto.integration.client.view.cl;

import progetto.controller.ToolCardSetPickedDiceAction;

public class ChoosePickedDiceCommand extends AbstractCLViewCommand {

    public ChoosePickedDiceCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    @Override
    public void exec(String[] args) {
        String toReturn = "Inserire una numero valido!";

        if(args == null || args.length == 0){
            write(toReturn);
            return;
        }
        try{
            int number = Integer.parseInt(args[0]);
            ToolCardSetPickedDiceAction toolCardSetPickedDiceAction =
                    new ToolCardSetPickedDiceAction(getController().getChair(), number);
            if(toolCardSetPickedDiceAction.canBeExecuted(getModel())){
                getController().sendAction(toolCardSetPickedDiceAction);
            }
            else {
                write(toReturn);
            }
        }catch (NumberFormatException e){
            write(toReturn);
        }
    }

    @Override
    public String getHelp() {
        return "Scegli il dado al quale vuoi applicare la carta " + "scelta (Formato: " + getName()+
                " <Numero del dado scelto>";
    }
}
