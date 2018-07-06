package progetto.view.commandline.commands;

import progetto.controller.SetDifficultyAction;
import progetto.view.commandline.CommandLineView;

/**
 * commmand to set the difficulty of a single player game
 * @author Federica
 */
public class SetSinglePlayerDifficultyCommand extends AbstractCLViewCommand {

    private static final int MAX_DIFFICULTY = 5;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public SetSinglePlayerDifficultyCommand(CommandLineView commandLineView){
        super(commandLineView);
    }

    /**
     * if possibile send a SetDifficultyAction to the controller
     * @param args input by the user, it should be a number between 1 and 5
     */
    @Override
    public void exec(String[] args) {

        int difficulty;
        if (args == null || args.length == 0){
            write("\nInserire un livello di difficoltà valido!");
            return;
        }


        try {
            difficulty = Integer.parseInt(args[0]);
        }catch (NumberFormatException e){
            difficulty = -1;
        }

        if (difficulty<1 || difficulty>MAX_DIFFICULTY ){
            write("\nInserire un livello di difficoltà valido!");
            return;
        }

        getController().sendAction(new SetDifficultyAction(difficulty));
    }

    /**
     * Return infos about what this command does and how the input should be written
     * @return infos about what this command does and how the input should be writtens
     */
    @Override
    public String getHelp() {
        return "Scegli il livello di difficoltà (Formato: " + getName() + " <Livello>)" +
                " (Con 5 = molto facile, 1 = molto difficile)";
    }
}
