package progetto.view.commandline.commands;

import progetto.controller.SetPlayerCountAction;
import progetto.view.commandline.CommandLineView;

/**
 * Command to set the number of players of a game
 */
public class SetNumberOfPlayersCommand extends AbstractCLViewCommand {

    private static final int MAX_NUMBER_OF_PLAYERS = 4;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public SetNumberOfPlayersCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    /**
     * if params contains a valid number of players, send to the controller a SetPlayerCountAction
     * @param params input by the user, it should contains a valid number of players
     */
    @Override
    public void exec(String[] params) {

        int numberOfPlayers;

        try {
            numberOfPlayers = Integer.parseInt(params[0]);
        }catch (Exception e){
            numberOfPlayers = -1;
        }

        if(numberOfPlayers < 1 || numberOfPlayers > MAX_NUMBER_OF_PLAYERS){
            write("Inserire un numero di giocatori valido!\n");
            return;
        }

        getController().sendAction(new SetPlayerCountAction((numberOfPlayers)));

    }

    /**
     * Return infos about what this command does and how the input should be written
     * @return infos about what this command does and how the input should be written
     */
    @Override
    public String getHelp() {
        return "Camabia il numero di giocatori (Formato: 2 <Numero di giocatori desiderato>)";
    }
}
