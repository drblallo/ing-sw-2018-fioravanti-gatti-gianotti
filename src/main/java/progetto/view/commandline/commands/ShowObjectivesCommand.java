package progetto.view.commandline.commands;

import progetto.model.AbstractPrivateObjectiveCard;
import progetto.model.AbstractPublicObjectiveCard;
import progetto.view.commandline.CommandLineView;

import java.util.List;

/**
 * Command to show public and private objective cards
 */
public class ShowObjectivesCommand extends AbstractCLViewCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public ShowObjectivesCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    /**
     * Show public and private objective cards
     * @param params input not needed
     */
    @Override
    public void exec(String[] params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nI tuoi obiettivi privati sono:\n");
        List<AbstractPrivateObjectiveCard> privateObjectives =
                getModel().getPlayerBoard(getController().getChair()).getData().getPrivateObjectiveCard();
        int i = 1;
        for (AbstractPrivateObjectiveCard card: privateObjectives) {
            stringBuilder.append(i + " - " + card.getToolTip()+'\n');
            i++;
        }
        stringBuilder.append("Gli obiettivi pubblici sono: \n");
        List<AbstractPublicObjectiveCard> publicObjectives =
                getModel().getMainBoard().getData().getPublicObjectiveCards();
        i=1;
        for (AbstractPublicObjectiveCard card: publicObjectives) {
            stringBuilder.append(i + " - " + card.getToolTip() + "\n");
            i++;
        }

        write(stringBuilder.toString());
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Mostra obiettivi";
    }
}
