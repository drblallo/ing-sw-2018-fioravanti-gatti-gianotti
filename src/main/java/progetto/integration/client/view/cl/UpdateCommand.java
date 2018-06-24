package progetto.integration.client.view.cl;

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
