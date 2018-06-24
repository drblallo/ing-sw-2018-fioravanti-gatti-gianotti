package progetto.integration.client.view.cl;

public class StartNewGameCommand extends AbstractStateSwitcherCommand {

    public StartNewGameCommand(CommandLineView commandLineView) {
        super(commandLineView, new DefaultViewState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getHelp() {
        return "Inizia una nuova partita";
    }
}
