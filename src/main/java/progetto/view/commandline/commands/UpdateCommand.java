package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.RoomsState;

public class UpdateCommand extends AbstractStateSwitcherCommand {

    public UpdateCommand(CommandLineView commandLineView) {
        super(commandLineView, new RoomsState(commandLineView) {
        });
    }

    @Override
    protected void perform(String[] params) {
        getCommandLineView().getController().fetchCurrentState();
    }

    @Override
    public String getHelp() {
        return "Aggiorna elenco stanze";
    }
}
