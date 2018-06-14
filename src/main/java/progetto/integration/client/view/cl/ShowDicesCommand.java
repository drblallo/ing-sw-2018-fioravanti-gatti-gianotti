package progetto.integration.client.view.cl;

public class ShowDicesCommand extends AbstractStateSwitcherCommand {

    private int numberOfCommand;

    public ShowDicesCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView, new PickDiceState(commandLineView));
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
        return "Prendi un dado";
    }
}
