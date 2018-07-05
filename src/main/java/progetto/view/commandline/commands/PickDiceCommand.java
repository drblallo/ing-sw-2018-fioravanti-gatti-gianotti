package progetto.view.commandline.commands;

import progetto.controller.PickDiceAction;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.RoundViewState;

/**
 * Command to pick a dice from the extracted ones
 */
public class PickDiceCommand extends AbstractStateSwitcherCommand {

    private int numberOfDice;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     * @param numberOfDice the dice among the extracted ones associated to this command
     */
    public PickDiceCommand(CommandLineView commandLineView, int numberOfDice) {
        super(commandLineView, new RoundViewState(commandLineView));
        this.numberOfDice = numberOfDice;
    }

    /**
     * Send a PickDiceAction to the controller
     * @param params not needed
     */
    @Override
    protected void perform(String[] params) {
        getController().sendAction(new PickDiceAction(getController().getChair(), numberOfDice));
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Prendi il dado numero " + numberOfDice;
    }
}
