package progetto.view.commandline.commands;

import progetto.controller.ToolCardSetSinglePlayerDiceAction;
import progetto.model.Dice;
import progetto.model.IModel;
import progetto.view.commandline.CommandLineView;

/**
 * Command to select the dice to sacrifice to use a tool card in single player mode
 */
public class SetSinglePlayerDiceAction extends AbstractCLViewCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public SetSinglePlayerDiceAction(CommandLineView commandLineView) {
        super(commandLineView);
    }

    /**
     * if args contains a valid position of a dice to sacrifice among the extracted ones, check if a
     * ToolCardSetSinglePlayerDiceAction can be executed and if positive send the action to the controller
     * @param args input by the user, it should be a valid position of a dice to sacrifice among the extracted ones
     */
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

    /**
     * if the dice has not been selected yet returns infos about what this command does and how the input
     * should be written, else return a memo of which dice has been selected
     * @return infos about the command according to the state of the tool card
     */
    @Override
    public String getHelp() {
        IModel model = getModel();
        int SPDice = model.getRoundInformation().getData().getToolCardParameters().getSPDice();
        if (SPDice<0)
            return "Scegli dalla riserva il dado da sacrificare per usare la carta scelta " +
                "(Formato: " + getName()+ " <Numero del dado>) " +
                "(NB: Il colore del dado deve corrispondere a quello indicato sulla carta)\n";
        else return "Hai selto di sacrificare il: "
                + model.getMainBoard().getExtractedDices().getData().getDice(SPDice).toString();
    }
}
