package progetto.view.commandline.commands;

import progetto.controller.UseToolCardAction;
import progetto.view.commandline.CommandLineView;

/**
 * Command to use a tool card
 */
public class UseToolCardCommand extends AbstractCLViewCommand {

    private int numberOfCard;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     * @param numberOfCard the number of the card to use
     */
    public UseToolCardCommand(CommandLineView commandLineView, int numberOfCard) {
        super(commandLineView);
        this.numberOfCard = numberOfCard;
    }

    /**
     * Check if a UseToolCardAction can be executed and if positive send the action to the controller
     * @param params no input needed
     */
    @Override
    public void exec(String[] params) {

        UseToolCardAction useToolCardAction = new UseToolCardAction(getController().getChair(), numberOfCard);
        if (useToolCardAction.canBeExecuted(getModel()))
            getController().sendAction(new UseToolCardAction(getController().getChair(), numberOfCard));
        else write("Non puoi usare questa carta!");
    }

    /**
     * Return the name of the tool card associated to this command
     * @return the name of the tool card associated to this command
     */
    @Override
    public String getHelp() {
        return getModel().getMainBoard().getData().getToolCards().get(numberOfCard).getToolTip();
    }
}
