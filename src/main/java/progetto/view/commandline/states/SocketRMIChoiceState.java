package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.ConnectionCommand;
import progetto.view.commandline.commands.ReturnCommand;

/**
 * State where the user can create a connection to the server
 */
public class SocketRMIChoiceState extends AbstractCLViewState {

    /**
     * public constructor
     * @param view the command line view that this state will be applied to
     */
    public SocketRMIChoiceState(CommandLineView view) {
        super("socket rmi choise state", view);
    }

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply() {
        registerCommand(new ConnectionCommand(getView(), true));
        registerCommand(new ConnectionCommand(getView(), false));
        registerCommand(new ReturnCommand(getView(), new DefaultViewState(getView()),"Indietro"));
    }

    /**
     * Return a message associated to this stage
     * @return a message associated to this stage
     */
    @Override
    public String getMessage(){
        return "\nSelezionare il tipo di connessione ed inserire l'indirizzo IP:\n";
    }
}
