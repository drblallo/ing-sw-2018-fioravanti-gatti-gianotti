package progetto.view.commandline.commands;

import progetto.controller.ToolCardSetPickedDiceAction;
import progetto.model.Dice;
import progetto.model.ToolCardParameters;
import progetto.view.commandline.CommandLineView;

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

        ToolCardParameters toolCardParameters = getModel().getRoundInformation().getData().getToolCardParameters();
        if (toolCardParameters.getNDice()>=0){
            Dice dice = getModel().getPlayerBoard(getController().getChair()).getPickedDicesSlot()
                    .getData().getDicePlacementCondition(toolCardParameters.getNDice()).getDice();
            return  "Hai scelto il " + dice.toString();
        }

        return "Scegli il dado al quale vuoi applicare la carta scelta (Formato: " + getName()+
                " <Numero del dado scelto>";
    }
}
