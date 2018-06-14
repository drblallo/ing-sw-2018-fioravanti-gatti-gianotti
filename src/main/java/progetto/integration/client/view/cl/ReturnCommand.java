package progetto.integration.client.view.cl;

public class ReturnCommand extends AbstractStateSwitcherCommand {

    private int numberOfCommand;
    private String name;

    public ReturnCommand(CommandLineView commandLineView,
                         AbstractCLViewState abstractCLViewState, int numberOfCommand, String name) {
        super(commandLineView, abstractCLViewState);
        this.numberOfCommand = numberOfCommand;
        this.name = name;
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
        return name;
    }
}
