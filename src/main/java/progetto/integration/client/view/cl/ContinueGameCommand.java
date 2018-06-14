package progetto.integration.client.view.cl;

public class ContinueGameCommand extends AbstractStateSwitcherCommand {

    private int numberOfConnection;

    public ContinueGameCommand(CommandLineView commandLineView, int numberOfConnection) {
        super(commandLineView, new GameTransitionState(commandLineView));
        this.numberOfConnection = numberOfConnection;
    }

    @Override
    protected void perform(String[] params) {
        getController().setCurrentClientGame(numberOfConnection-1);
    }

    @Override
    public String getName() {
        return numberOfConnection + "";
    }

    @Override
    public String getHelp() {
        return getController().getNameOfConnection(numberOfConnection-1);
    }
}
