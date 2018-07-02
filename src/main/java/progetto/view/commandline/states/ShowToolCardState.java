package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.ReturnCommand;
import progetto.view.commandline.commands.UseToolCardCommand;

public class ShowToolCardState extends AbstractCLViewState {

    public ShowToolCardState(CommandLineView cl) {
        super("Show tool card state", cl);
    }

    @Override
    public void onApply() {

        for (int i = 0; i < getModel().getMainBoard().getData().getToolCards().size(); i++)
            registerCommand(new UseToolCardCommand(getView(), i));
        registerCommand(new ReturnCommand(getView(), new GameTransitionState(getView()), "Indietro"));
    }

    @Override
    public String getMessage() {

        StringBuilder toReturn = new StringBuilder();
        toReturn.append("\nScegliere quale carta utilizzare tra quelle proposte:\n");
        if (getModel().getMainBoard().getData().getPlayerCount() != 1)
            toReturn.append("Hai " + getModel().getPlayerBoard(getController().getChair()).getData().getToken()
            + "punti favore\n");
        return toReturn.toString();
    }
}
