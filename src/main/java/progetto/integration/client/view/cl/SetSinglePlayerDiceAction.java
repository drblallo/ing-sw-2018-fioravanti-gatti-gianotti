package progetto.integration.client.view.cl;

import progetto.controller.ToolCardSetSinglePlayerDiceAction;

public class SetSinglePlayerDiceAction extends AbstractCLViewCommand {

    private int numberOfCommand;

    public SetSinglePlayerDiceAction(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView);
        this.numberOfCommand = numberOfCommand;
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
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        return "Scegli il dado da sacrificare per usare la carta scelta " +
                "(Formato: " + numberOfCommand + " <Numero del dado>) " +
                "(NB: Il colore del dado deve corrispondere a quello indicato sulla carta)";
    }
}
