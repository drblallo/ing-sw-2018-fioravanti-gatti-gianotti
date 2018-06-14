package progetto.integration.client.view.cl;

public class SelectChairCommand extends AbstractStateSwitcherCommand {

    public SelectChairCommand(CommandLineView commandLineView) {
        super(commandLineView, new ChairSelectionState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getName() {
        return "3";
    }

    @Override
    public String getHelp() {
        return "Seleziona una sedia";
    }
}
