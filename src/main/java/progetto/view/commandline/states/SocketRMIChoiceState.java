package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.ConnectionCommand;
import progetto.view.commandline.commands.ReturnCommand;

public class SocketRMIChoiceState extends AbstractCLViewState {


    public SocketRMIChoiceState(CommandLineView view) {
        super("socket rmi choise state", view);
    }

    @Override
    public void onApply() {

        registerCommand(new ConnectionCommand(getView(), true));
        registerCommand(new ConnectionCommand(getView(), false));
        registerCommand(new ReturnCommand(getView(), new DefaultViewState(getView()),"Indietro"));

    }

    @Override
    public String getMessage(){

        return "\nSelezionare il tipo di connessione ed inserire l'indirizzo IP:\n";

    }
}
