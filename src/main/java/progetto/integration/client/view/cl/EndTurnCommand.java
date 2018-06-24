package progetto.integration.client.view.cl;

import progetto.controller.EndTurnAction;

public class EndTurnCommand extends AbstractCLViewCommand {

    public EndTurnCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    @Override
    public void exec(String[] args) {
        getController().sendAction(new EndTurnAction(getController().getChair()));
    }

    @Override
    public String getHelp() {
        return "Finisci il tuo turno";
    }
}
