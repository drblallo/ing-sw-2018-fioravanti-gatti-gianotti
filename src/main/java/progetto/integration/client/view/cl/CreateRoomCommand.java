package progetto.integration.client.view.cl;

public class CreateRoomCommand extends AbstractStateSwitcherCommand {

    public CreateRoomCommand(CommandLineView commandLineView) {
        super(commandLineView, new RoomsState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {

        if(params == null || params.length == 0){

            write("Inserire un nome valido per la stanza!\n");
            return;
        }

        getCommandLineView().getController().createGame(params[0]);
        getCommandLineView().getController().fetchCurrentState();
        write("La stanza " + params[0] + " è stata creata!\n");
    }

    @Override
    public String getHelp() {
        return "Crea una nuova stanza (Formato: 1 <NomeStanza>)";
    }
}
