package progetto.integration.client.view.cl;

import progetto.model.ToolCard;

public class ShowToolCardState extends AbstractCLViewState {

    public ShowToolCardState(CommandLineView cl) {
        super("Show tool card state", cl);
    }

    @Override
    public void onApply() {

        int i = 0;
        for (ToolCard toolCard: getModel().getMainBoard().getData().getToolCards()) {
            registerCommand(new UseToolCardCommand(getView(), i));
            i++;
        }

    }

    @Override
    public String getMessage() {
        return "\nScegliere quale carta utilizzare tra quelle proposte:\n";
    }
}
