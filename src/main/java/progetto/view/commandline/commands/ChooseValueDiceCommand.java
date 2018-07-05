package progetto.view.commandline.commands;

import progetto.controller.ToolCardSetDiceValueAction;
import progetto.model.ToolCardParameters;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

/**
 * Command to select the value of a dice when permitted by a tool card
 */
public class ChooseValueDiceCommand extends AbstractStateSwitcherCommand {

    private static final int MAX_VALUE = 6;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public ChooseValueDiceCommand(CommandLineView commandLineView) {
        super(commandLineView, new GameTransitionState(commandLineView));
    }

    /**
     * if args contains a valid value for a dice, check if a ToolCardSetDiceValueAction can be executed and
     * if positive send the action to the controller
     * @param args input by the user, it should be a valid value for a dice
     */
    @Override
    protected void perform(String[] args) {

        String toReturn = "Inserire un valore valido!";

        if(args == null || args.length == 0){
            write(toReturn);
            return;
        }
        try {
            int value = Integer.parseInt(args[0]);
                ToolCardSetDiceValueAction toolCardSetDiceValueAction =
                        new ToolCardSetDiceValueAction(getController().getChair(), value);
                if(toolCardSetDiceValueAction.canBeExecuted(getModel())){
                    getController().sendAction(toolCardSetDiceValueAction);
                }
                else write(toReturn);

        }catch (NumberFormatException e ){
            write(toReturn);
        }
    }

    /**
     * if the value of the dice has not been selected yet returns infos about what this command does and how the input
     * should be written, else return a memo of which value has been selected
     * @return infos about the command according to the state of the tool card
     */
    @Override
    public String getHelp(){
        ToolCardParameters toolCardParameters = getModel().getRoundInformation().getData().getToolCardParameters();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("E' stato estratto un dado di colore ")
                .append(toolCardParameters.getDice().getGameColor().toString()).append(",")
                .append(" scegli il valore da assegnargli (Formato: ").append(getName())
                .append(" <Valore del dado>)");
        return stringBuilder.toString();
    }
}
