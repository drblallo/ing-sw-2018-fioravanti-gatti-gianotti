package progetto.view.commandline.commands;

import progetto.controller.EndTurnAction;
import progetto.view.commandline.CommandLineView;

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
