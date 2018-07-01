package progetto.view.commandline.commands;

import progetto.controller.UseToolCardAction;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

public class UseToolCardCommand extends AbstractStateSwitcherCommand {

    private int numberOfCard;

    public UseToolCardCommand(CommandLineView commandLineView, int numberOfCard) {
        super(commandLineView, new GameTransitionState(commandLineView));
        this.numberOfCard = numberOfCard;
    }

    @Override
    protected void perform(String[] params) {
        getController().sendAction(new UseToolCardAction(getController().getChair(), numberOfCard));
    }

    @Override
    public String getHelp() {
        return getModel().getMainBoard().getData().getToolCards().get(numberOfCard).getToolTip();
    }
}
