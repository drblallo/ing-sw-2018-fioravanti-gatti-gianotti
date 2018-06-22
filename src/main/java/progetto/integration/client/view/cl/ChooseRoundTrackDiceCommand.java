package progetto.integration.client.view.cl;

import progetto.controller.ToolCardSetDiceRoundTrackAction;

public class ChooseRoundTrackDiceCommand extends AbstractCLViewCommand {

    private int numberOfCommand;

    public ChooseRoundTrackDiceCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView);
        this.numberOfCommand = numberOfCommand;
    }

    @Override
    public void exec(String[] args) {

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

    @Override
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        return "Indica il dado del tracciato dei round che desideri prelevare " +
                "(Formato: " + numberOfCommand + " <Numero del round> <Numero del dado>";
    }
}
