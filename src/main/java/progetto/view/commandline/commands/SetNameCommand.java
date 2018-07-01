package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.DefaultViewState;

public class SetNameCommand extends AbstractCLViewCommand {

    public SetNameCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    @Override
    public void exec(String[] params) {

        if(params == null || params.length == 0){
            write("Inserire un nome valido!\n");
            return;
        }

        getCommandLineView().setPlayerName(params[0]);
        write("\nNome corrente: " + getCommandLineView().getPlayerName() + "\n");
        setState(new DefaultViewState(getCommandLineView()));
    }

    @Override
    public String getHelp() {
        return "Inserisci nome (Formato: 3 <Nome>)";
    }
}
