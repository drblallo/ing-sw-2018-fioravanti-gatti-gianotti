package progetto.integration.client.view.cl;

public class ContinueGameState extends AbstractCLViewState{

    public ContinueGameState(CommandLineView cl) {
        super("Continue game state", cl);
    }

    @Override
    public void onApply() {

        for (int i = 0; i < getController().getConnectionCount(); i++)
            registerCommand(new ContinueGameCommand(getView(), i));

        registerCommand(new ReturnCommand(getView(), new DefaultViewState(getView()),"Indietro"));
    }

    @Override
    public String getMessage() {
        return "\nSelezionare la partita che si desidera riprendere:\n";
    }
}
