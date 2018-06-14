package progetto.integration.client.view.cl;

import progetto.controller.EndTurnAction;

public class EndTurnCommand extends AbstractCLViewCommand {

    private int numberOfCommand;

    public EndTurnCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView);
        this.numberOfCommand = numberOfCommand;
    }

    @Override
    public void exec(String[] args) {
        getController().sendAction(new EndTurnAction(getController().getChair()));
    }

    @Override
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        return "Finisci il tuo turno";
    }
}
