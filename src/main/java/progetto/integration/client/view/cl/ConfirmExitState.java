package progetto.integration.client.view.cl;

public class ConfirmExitState extends AbstractCLViewState {

    public ConfirmExitState(CommandLineView cl) {
        super("ConfirmExistState", cl);
    }

    @Override
    public void onApply() {

        registerCommand(new ReturnCommand(getView(), new DefaultViewState(getView()), "Si"));
        registerCommand(new ReturnCommand(getView(), new RoundViewState(getView()), "No"));
    }

    @Override
    public String getMessage() {
        return "\nSei sicuro di voler abbandoare la partita?\n";
    }
}
