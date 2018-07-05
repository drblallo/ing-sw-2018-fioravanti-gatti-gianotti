package progetto.view.commandline.commands;

import progetto.controller.SetDifficultyAction;
import progetto.view.commandline.CommandLineView;

public class SetSinglePlayerDifficultyCommand extends AbstractCLViewCommand {

    private static final int MAX_DIFFICULTY = 5;

    public SetSinglePlayerDifficultyCommand(CommandLineView commandLineView){
        super(commandLineView);
    }

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

    @Override
    public String getHelp() {
        return "Scegli il livello di difficoltà (Formato: " + getName() + " <Livello>)" +
                " (Con 5 = molto facile, 1 = molto difficile)";
    }
}
