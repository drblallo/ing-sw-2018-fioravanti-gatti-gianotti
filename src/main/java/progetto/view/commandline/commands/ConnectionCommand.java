package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.RoomsState;

/**
 * Command to create a socket or a RMI connection to the server
 * @author Federica
 */
public class ConnectionCommand extends AbstractCLViewCommand {

    private boolean isRMI;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     * @param isRMI information about what type of connection has to be create
     */
    public ConnectionCommand(CommandLineView commandLineView, boolean isRMI) {
        super(commandLineView);
        this.isRMI = isRMI;
    }

    /**
     * if args contains a valid string, tries to create a connection using the input string as IP address
     * @param params input string by the user, it should be a valid IP address
     */
    @Override
    public void exec(String[] params) {

        if(params == null || params.length == 0){
            write("\nInserire un indirizzo IP valido!\n");
            return;
        }

        if(getCommandLineView().getController().createConnection(params[0], isRMI)){
        	setState(new RoomsState(getCommandLineView()));
            return;
        }

        write("Creazione connessione fallita");


    }

    /**
     * Return infos about how the input should be written
     * @return infos about how the input should be written
     */
    @Override
    public String getHelp() {
        if(isRMI)
            return "RMI (Formato: 1 <Indirizzo IP>)";
        return "Socket (Formato: 2 <Indirizzo IP>)";
    }
}
