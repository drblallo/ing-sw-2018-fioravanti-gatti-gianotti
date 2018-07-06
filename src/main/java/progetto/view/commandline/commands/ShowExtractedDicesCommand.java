package progetto.view.commandline.commands;

import progetto.model.ExtractedDicesData;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.printer.DrawableUtils;

/**
 * Command to show extracted dices
 * @author Federica
 */
public class ShowExtractedDicesCommand extends AbstractCLViewCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modifiy
     */
    public ShowExtractedDicesCommand(CommandLineView commandLineView){ super(commandLineView); }

    /**
     * Show the picked dices of the user
     * @param args  input not needed
     */
    @Override
    public void exec(String[] args) {
        ExtractedDicesData extractedDicesData = getController().getModel()
                .getMainBoard().getExtractedDices().getData();
        if(extractedDicesData.getNumberOfDices()!=0)
            write(DrawableUtils.getExtracted(extractedDicesData).toString());
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Mostra i dadi nella riserva";
    }
}
