package progetto.integration.client.view.cl;

public class ShowPlayerBoardRequestCommand extends AbstractStateSwitcherCommand {

    private int numberOfCommand;

    public ShowPlayerBoardRequestCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView, new ShowPlayerBoardState(commandLineView));
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
        return "Vedi schede dei giocatori";
    }
}
