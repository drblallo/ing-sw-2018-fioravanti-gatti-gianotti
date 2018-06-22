package progetto.integration.client.view.cl;

public class ShowToolCardsCommand extends AbstractStateSwitcherCommand {

    private int numberOfCommand;

    public ShowToolCardsCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView, new ShowToolCardState(commandLineView));
        this.numberOfCommand = numberOfCommand;
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        return "Mostra le carte utensili disponibili";
    }
}
