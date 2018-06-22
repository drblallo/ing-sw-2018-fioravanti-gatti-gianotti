package progetto.integration.client.view.cl;

public class UseToolCardCommand extends AbstractStateSwitcherCommand {

    private int numberOfCard;

    public UseToolCardCommand(CommandLineView commandLineView, int numberOfCard) {
        super(commandLineView, new UseToolCardState(commandLineView, numberOfCard));
        this.numberOfCard = numberOfCard;
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getName() {
        return numberOfCard + "";
    }

    @Override
    public String getHelp() {
        return getModel().getMainBoard().getData().getToolCards().get(numberOfCard).getToolTip();
    }
}
