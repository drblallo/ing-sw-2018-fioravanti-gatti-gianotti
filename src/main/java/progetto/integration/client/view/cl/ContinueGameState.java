package progetto.integration.client.view.cl;

public class ContinueGameState extends AbstractCLViewState{

    public ContinueGameState(CommandLineView cl) {
        super("Continue game state", cl);
    }

    @Override
    public void onApply() {

        int i;
        for (i=0; i<getController().getConnectionCount(); i++){
            registerCommand(new ContinueGameCommand(getView(), i+1));
        }
        registerCommand(new ReturnCommand(getView(), new DefaultViewState(getView()), i+1, "Indietro"));
    }

    @Override
    public String getMessage() {
        return "\nSelezionare la partita che si desidera riprendere:\n";
    }
}
