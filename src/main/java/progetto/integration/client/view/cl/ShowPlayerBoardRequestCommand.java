package progetto.integration.client.view.cl;

public class ShowPlayerBoardRequestCommand extends AbstractStateSwitcherCommand {

    public ShowPlayerBoardRequestCommand(CommandLineView commandLineView) {
        super(commandLineView, new ShowPlayerBoardState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        //Only state switcher command
    }

    @Override
    public String getHelp() {
        return "Vedi schede dei giocatori";
    }
}
