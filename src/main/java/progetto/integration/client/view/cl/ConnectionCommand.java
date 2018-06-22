package progetto.integration.client.view.cl;

public class ConnectionCommand extends AbstractCLViewCommand {

    private boolean isRMI;

    public ConnectionCommand(CommandLineView commandLineView, boolean isRMI) {
        super(commandLineView);
        this.isRMI = isRMI;
    }

    @Override
    public String getHelp() {
        if(isRMI)
            return "RMI (Formato: 1 <Indirizzo IP>)";
        return "Socket (Formato: 2 <Indirizzo IP>)";
    }

    @Override
    public void exec(String[] params) {

        if(params == null || params.length == 0){

            setState(new SocketRMIChoiceState(getCommandLineView()));
            write("Inserire un indirizzo IP valido!\n");
            return;

        }


        if(getCommandLineView().getController().createConnection(params[0], isRMI)){
        	setState(new RoomsState(getCommandLineView()));
            return;
        }

        setState(new SocketRMIChoiceState(getCommandLineView()));
        write("Creazione connessione fallita");


    }
}
