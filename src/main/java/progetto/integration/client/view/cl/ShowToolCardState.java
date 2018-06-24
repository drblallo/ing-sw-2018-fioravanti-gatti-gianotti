package progetto.integration.client.view.cl;

public class ShowToolCardState extends AbstractCLViewState {

    public ShowToolCardState(CommandLineView cl) {
        super("Show tool card state", cl);
    }

    @Override
    public void onApply() {

        for (int i = 0; i < getModel().getMainBoard().getData().getToolCards().size(); i++)
            registerCommand(new UseToolCardCommand(getView(), i));
    }

    @Override
    public String getMessage() {
        return "\nScegliere quale carta utilizzare tra quelle proposte:\n";
    }
}
