package progetto.view.commandline.commands;

import progetto.controller.ToolCardSetSinglePlayerDiceAction;
import progetto.view.commandline.CommandLineView;

public class SetSinglePlayerDiceAction extends AbstractCLViewCommand {

    public SetSinglePlayerDiceAction(CommandLineView commandLineView) {
        super(commandLineView);
    }

    @Override
    public void exec(String[] args) {
        String toReturn = "Inserire un numero valido!";
        if(args == null || args.length == 0){
         write(toReturn);
         return;
        }
        try{
            int numberOfDice = Integer.parseInt(args[0]);
            ToolCardSetSinglePlayerDiceAction toolCardSetSinglePlayerDiceAction =
                    new ToolCardSetSinglePlayerDiceAction(getController().getChair(), numberOfDice);
            if(toolCardSetSinglePlayerDiceAction.canBeExecuted(getModel())){
                getController().sendAction(toolCardSetSinglePlayerDiceAction);
            }
            else write(toReturn);
        }catch (NumberFormatException e){
            write(toReturn);
        }
    }

    @Override
    public String getHelp() {
        return "Scegli il dado da sacrificare per usare la carta scelta " +
                "(Formato: " + getName()+ " <Numero del dado>) " +
                "(NB: Il colore del dado deve corrispondere a quello indicato sulla carta)\n";
    }
}
