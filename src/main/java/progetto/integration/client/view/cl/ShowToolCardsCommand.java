package progetto.integration.client.view.cl;

public class ShowToolCardsCommand extends AbstractStateSwitcherCommand {

    public ShowToolCardsCommand(CommandLineView commandLineView) {
        super(commandLineView, new ShowToolCardState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getHelp() {
        return "Mostra le carte utensili disponibili";
    }
}
