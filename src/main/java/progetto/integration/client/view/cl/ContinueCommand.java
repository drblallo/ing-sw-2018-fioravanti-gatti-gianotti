package progetto.integration.client.view.cl;

public class ContinueCommand extends AbstractStateSwitcherCommand {

    public ContinueCommand(CommandLineView commandLineView) {
        super(commandLineView, new ContinueGameState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getName() {
        return "2";
    }

    @Override
    public String getHelp() {
        return "Continua partita";
    }
}
