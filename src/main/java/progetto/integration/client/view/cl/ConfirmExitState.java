package progetto.integration.client.view.cl;

public class ConfirmExitState extends AbstractCLViewState {

    public ConfirmExitState(CommandLineView cl) {
        super("ConfirmExistState", cl);
    }

    @Override
    public void onApply() {

        int i = 1;
        registerCommand(new ReturnCommand(getView(), new DefaultViewState(getView()), i, "Si"));
        i++;
        registerCommand(new ReturnCommand(getView(), new RoundViewState(getView()), i, "No"));

    }

    @Override
    public String getMessage() {
        return "\nSei sicuro di voler abbandoare il gioco?\n";
    }
}
