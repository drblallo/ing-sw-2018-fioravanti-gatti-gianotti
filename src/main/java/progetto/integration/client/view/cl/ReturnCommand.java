package progetto.integration.client.view.cl;

public class ReturnCommand extends AbstractStateSwitcherCommand {

    private String name;

    public ReturnCommand(CommandLineView commandLineView, AbstractCLViewState abstractCLViewState, String name) {
        super(commandLineView, abstractCLViewState);
        this.name = name;
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getHelp() {
        return name;
    }
}
