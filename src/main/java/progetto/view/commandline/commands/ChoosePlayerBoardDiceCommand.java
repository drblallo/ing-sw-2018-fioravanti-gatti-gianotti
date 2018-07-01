package progetto.view.commandline.commands;

import progetto.controller.ToolCardSetPlacedDiceAction;
import progetto.controller.ToolCardSetSecondPlacedDiceAction;
import progetto.model.Dice;
import progetto.model.ToolCardParameters;
import progetto.view.commandline.CommandLineView;

public class ChoosePlayerBoardDiceCommand extends AbstractCLViewCommand {

    private int numberOfDice;

    public ChoosePlayerBoardDiceCommand(CommandLineView commandLineView, int numberOfDice) {
        super(commandLineView);
        this.numberOfDice = numberOfDice;
    }

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

    @Override
    public String getHelp() {
        ToolCardParameters toolCardParameters = getModel().getRoundInformation().getData().getToolCardParameters();
        if (numberOfDice == 1 && toolCardParameters.isFirstDiceSet()){
                Dice dice = getModel().getPlayerBoard(getController().getChair()).getDicePlacedFrame()
                        .getData().getDice(toolCardParameters.getYPlacedDice(), toolCardParameters.getXPlacedDice());
                return  "Hai scelto il " + dice.toString();
        }
        else if (numberOfDice == 2 && toolCardParameters.isSecondDiceSet()){
            Dice dice = getModel().getPlayerBoard(getController().getChair()).getDicePlacedFrame()
                    .getData().getDice(toolCardParameters.getYPlacedDice2(), toolCardParameters.getXPlacedDice2());
            return "Hai scelto il " + dice.toString();
        }
        else return "Digita la posizione del " + numberOfDice + "° dado della plancia da muovere " +
                "(Formato: " + getName() + " <x> <y>)";
    }
}
