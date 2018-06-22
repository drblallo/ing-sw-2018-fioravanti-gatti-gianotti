package progetto.integration.client.view.cl;

import progetto.model.AbstractPrivateObjectiveCard;
import progetto.model.AbstractPublicObjectiveCard;

import java.util.List;

public class ShowObjectivesCommand extends AbstractStateSwitcherCommand {


    public ShowObjectivesCommand(CommandLineView commandLineView) {
        super(commandLineView, new RoundViewState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
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

    @Override
    public String getHelp() {
        return "Mostra obiettivi";
    }
}
