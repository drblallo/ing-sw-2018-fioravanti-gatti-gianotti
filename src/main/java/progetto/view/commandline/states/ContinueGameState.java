package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.ContinueGameCommand;
import progetto.view.commandline.commands.ReturnCommand;

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
