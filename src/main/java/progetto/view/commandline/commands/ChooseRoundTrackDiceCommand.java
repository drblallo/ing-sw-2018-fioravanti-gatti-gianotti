package progetto.view.commandline.commands;

import progetto.controller.ToolCardSetDiceRoundTrackAction;
import progetto.model.Dice;
import progetto.model.ToolCardParameters;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

/**
 * Command to select the dice from the ones in the round track to use with a tool card
 */
public class ChooseRoundTrackDiceCommand extends AbstractStateSwitcherCommand {

    /**
     * public constructu
     * @param commandLineView the command line view that this command will modify
     */
    public ChooseRoundTrackDiceCommand(CommandLineView commandLineView) {
        super(commandLineView, new GameTransitionState(commandLineView));
    }

    /**
     * if args contains a valid position of a dice in the round track, check if a ToolCardSetDiceRoundTrackAction
     * can be executed and if positive send the action to the controller
     * @param args input by the user, it should be a valid position of a dice in the round track
     */
    @Override
    protected void perform(String[] args) {

        String toReturn = "Indicare un dado esistente!";

        if(args == null || args.length!=2){
            write(toReturn);
            return;
        }
        try {
            int numberOfRound = Integer.parseInt(args[0]);
            int numberOfDice = Integer.parseInt(args[1]);

            ToolCardSetDiceRoundTrackAction toolCardSetDiceRoundTrackAction =
                    new ToolCardSetDiceRoundTrackAction(getController().getChair(), numberOfRound, numberOfDice);
            if (toolCardSetDiceRoundTrackAction.canBeExecuted(getModel()))
                getController().sendAction(toolCardSetDiceRoundTrackAction);
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
        if (toolCardParameters.getNDiceRT()>=0) {
            Dice dice = getModel().getRoundTrack().getData().getDice
                    (toolCardParameters.getRound(), toolCardParameters.getNDiceRT());
            return "Hai scelto dal tracciato dei round il " + dice.toString();
        }
        else return "Indica il dado del tracciato dei round che desideri prelevare " +
                "(Formato: " + getName()+ " <Numero del round> <Numero del dado>";
    }
}
