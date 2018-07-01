package progetto.view.commandline.commands;

import progetto.controller.PickDiceAction;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.RoundViewState;

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
    public String getHelp() {
        return "Prendi il dado numero " + numberOfDice;
    }
}
