package progetto.integration.client.view.cl;

public class CloseGameCommand extends AbstractCLViewCommand {

    private int numberOfCommand;

    public CloseGameCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView);
        this.numberOfCommand = numberOfCommand;
    }

    @Override
    public void exec(String[] args) {
        getController().shutDown();
    }

    @Override
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        return "Spegni il gioco";
    }
}
