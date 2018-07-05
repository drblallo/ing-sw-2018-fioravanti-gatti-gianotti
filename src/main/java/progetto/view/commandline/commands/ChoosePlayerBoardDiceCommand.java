package progetto.view.commandline.commands;

import progetto.controller.ToolCardSetPlacedDiceAction;
import progetto.controller.ToolCardSetSecondPlacedDiceAction;
import progetto.model.Dice;
import progetto.model.ToolCardParameters;
import progetto.view.commandline.CommandLineView;

/**
 * Command to select the dice from the playerBoard to use with a tool card
 */
public class ChoosePlayerBoardDiceCommand extends AbstractCLViewCommand {

    private int numberOfDice;

    /**
     * pubblic constructor
     * @param commandLineView the command line view that this command will modify
     * @param numberOfDice the number of the dice to select (1 = first dice, 2 = second dice)
     */
    public ChoosePlayerBoardDiceCommand(CommandLineView commandLineView, int numberOfDice) {
        super(commandLineView);
        this.numberOfDice = numberOfDice;
    }

    /**
     * if args contains a valid position of a dice placed on the playerboard,
     * if numberOfDice == 1 check if a ToolCardSetPlacedDiceAction can be executed and
     * if positive send the action to the controller,
     * if numberOfDice == 2 check if a ToolCardSetSecondPlacedDiceAction ca be executed and
     * if positive send the action to the controller
     * @param args input by the user, it should be a valid position of a dice placed on the playerboard
     */
    @Override
    public void exec(String[] args) {

        String toReturn = "Digitare una posizione valida!";

        if(args == null || args.length!=2){
            write(toReturn);
            return;
        }
        try {
            int posX = Integer.parseInt(args[1]);
            int posY = Integer.parseInt(args[0]);

            if(numberOfDice == 1){
                ToolCardSetPlacedDiceAction toolCardSetPlacedDiceAction =
                        new ToolCardSetPlacedDiceAction(getController().getChair(), posY, posX);
                if(toolCardSetPlacedDiceAction.canBeExecuted(getModel()))
                    getController().sendAction(toolCardSetPlacedDiceAction);
                else write(toReturn);
            }
            else if (numberOfDice == 2){
                ToolCardSetSecondPlacedDiceAction toolCardSetSecondPlacedDiceAction =
                        new ToolCardSetSecondPlacedDiceAction(getController().getChair(), posY, posX);
                if (toolCardSetSecondPlacedDiceAction.canBeExecuted(getModel()))
                    getController().sendAction(toolCardSetSecondPlacedDiceAction);
                else write(toReturn);
            }
            else write(toReturn);
        }catch (NumberFormatException e ){
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
        if (numberOfDice == 1 && toolCardParameters.isFirstDiceSet()){
                Dice dice = getModel().getPlayerBoard(getController().getChair()).getDicePlacedFrame()
                        .getData().getDice(toolCardParameters.getYPlacedDice(), toolCardParameters.getXPlacedDice());
                return  "Hai scelto dai dadi piazzati il " + dice.toString();
        }
        else if (numberOfDice == 2 && toolCardParameters.isSecondDiceSet()){
            Dice dice = getModel().getPlayerBoard(getController().getChair()).getDicePlacedFrame()
                    .getData().getDice(toolCardParameters.getYPlacedDice2(), toolCardParameters.getXPlacedDice2());
            return "Hai scelto dai dadi piazzati il " + dice.toString();
        }
        else return "Digita la posizione del " + numberOfDice + "° dado della plancia da muovere " +
                "(Formato: " + getName() + " <x> <y>)";
    }
}
