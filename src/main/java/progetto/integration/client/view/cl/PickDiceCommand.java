package progetto.integration.client.view.cl;

import progetto.controller.PickDiceAction;

public class PickDiceCommand extends AbstractStateSwitcherCommand {

    private int numberOfDice;

    public PickDiceCommand(CommandLineView commandLineView, int numberOfDice) {
        super(commandLineView, new RoundViewState(commandLineView));
        this.numberOfDice = numberOfDice;
    }

    @Override
    protected void perform(String[] params) {
        getController().sendAction(new PickDiceAction(getController().getChair(), numberOfDice));
    }

    @Override
    public String getName() {
        return numberOfDice + "";
    }

    @Override
    public String getHelp() {
        return "Prendi il dado numero " + numberOfDice;
    }
}
