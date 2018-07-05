package progetto.view.commandline.commands;

import progetto.controller.ToolCardSetPickedDiceAction;
import progetto.model.Dice;
import progetto.model.ToolCardParameters;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

/**
 * Command to select the dice from the picked ones to use with a tool card
 */
public class ChoosePickedDiceCommand extends AbstractStateSwitcherCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modifiy
     */
    public ChoosePickedDiceCommand(CommandLineView commandLineView) {
        super(commandLineView, new GameTransitionState(commandLineView));
    }

    /**
     * if args contains a valid number of dice, check if a ToolCardSetPickedDiceAction can be executed and
     * if positive send the action to the controller
     * @param args input by the user, it should be the number of the picked dice
     */
    @Override
    protected void perform(String[] args) {
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

    /**
     * if the dice has not been selected yet returns infos about what this command does and how the input
     * should be written, else return a memo of which dice has been selected
     * @return infos about the command according to the state of the tool card
     */
    @Override
    public String getHelp() {

        ToolCardParameters toolCardParameters = getModel().getRoundInformation().getData().getToolCardParameters();
        if (toolCardParameters.getNDice()>=0){
            Dice dice = getModel().getPlayerBoard(getController().getChair()).getPickedDicesSlot()
                    .getData().getDicePlacementCondition(toolCardParameters.getNDice()).getDice();
            return  "Hai scelto dai tuoi dadi presi il " + dice.toString();
        }

        return "Scegli il dado, tra quelli presi, al quale vuoi applicare la carta scelta (Formato: " + getName()+
                " <Numero del dado scelto>";
    }
}
