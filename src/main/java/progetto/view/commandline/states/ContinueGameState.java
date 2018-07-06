package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.ContinueGameCommand;
import progetto.view.commandline.commands.ReturnCommand;

/**
 * State where the user can see the existing games and select one of them
 */
public class ContinueGameState extends AbstractCLViewState{

    /**
     * public constructor
     * @param cl the command line view that this state will be applied to
     */
    public ContinueGameState(CommandLineView cl) {
        super("Continue game state", cl);
    }

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply() {

        for (int i = 0; i < getController().getConnectionCount(); i++)
            registerCommand(new ContinueGameCommand(getView(), i));

        registerCommand(new ReturnCommand(getView(), new DefaultViewState(getView()),"Indietro"));
    }

    /**
     * returns a message associated to this stage
     * @return a message associated to this stage
     */
    @Override
    public String getMessage() {
        return "\nSelezionare la partita che si desidera riprendere:\n";
    }
}
