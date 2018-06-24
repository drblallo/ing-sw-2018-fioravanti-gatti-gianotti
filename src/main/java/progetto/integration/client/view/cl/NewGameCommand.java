package progetto.integration.client.view.cl;

public class NewGameCommand extends AbstractStateSwitcherCommand {

    public NewGameCommand(CommandLineView commandLineView) {
        super(commandLineView, new SocketRMIChoiceState(commandLineView));
    }

    @Override
    public String getHelp() {
        return "Nuova partita";
    }

    @Override
    public void perform(String[] params) {
        //Only state switcher command
    }
}
