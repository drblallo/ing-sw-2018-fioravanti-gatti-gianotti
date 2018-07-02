package progetto.view.commandline.states;

import progetto.controller.*;
import progetto.model.IModel;
import progetto.model.ToolCard;
import progetto.model.ToolCardActionList;
import progetto.model.ToolCardState;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.Printer;
import progetto.view.commandline.commands.*;

public class UseToolCardState extends AbstractCLViewState {

    public UseToolCardState(CommandLineView cl) {
        super("Use tool card state", cl);
    }

    @Override
    public boolean isStillValid() {
        IModel model = getModel();
        return model.getMainBoard().getData().getGameState().getClass() == ToolCardState.class &&
        model.getRoundInformation().getData().getCurrentPlayer() == getController().getChair() ;
    }

    @Override
    public void onApply() {

        IModel model = getModel();

        int numberOfCard = model.getRoundInformation().getData().getToolCardParameters().getNCard();
        ToolCard toolCard = model.getMainBoard().getData().getToolCards().get(numberOfCard);
        getController().sendAction(new UseToolCardAction(getController().getChair(), numberOfCard));

        if (model.getMainBoard().getData().getPlayerCount() == 1)
            registerCommand(new SetSinglePlayerDiceAction(getView()));

        registerCommand(new ShowPickedDicesCommand(getView()));
        registerCommand(new ShowPlayerBoardCommand(getView(), getController().getChair() , new Printer(), this));
        registerCommand(new ShowRoundTrackCommand(getView()));

        for (Class c: ToolCardActionList.getInstance().getList(toolCard.getIndex())) {

            if(c == ToolCardSetPickedDiceAction.class){
                registerCommand(new ChoosePickedDiceCommand(getView()));
            }
            else if(c == ToolCardSetIncreaseDecreaseAction.class){
                registerCommand(new IncreaseDecreseCommand(getView(),0));
                registerCommand(new IncreaseDecreseCommand(getView(), 1));
            }
            else if(c == ToolCardSetPlacedDiceAction.class){
                registerCommand(new ChoosePlayerBoardDiceCommand(getView(), 1));
            }
            else if(c == ToolCardSetDiceRoundTrackAction.class){
                registerCommand(new ChooseRoundTrackDiceCommand(getView()));
            }
            else if(c == ToolCardSetSecondPlacedDiceAction.class){
                registerCommand(new ChoosePlayerBoardDiceCommand(getView(), 2));
            }
            else if (c == ToolCardSetDiceValueAction.class){
                registerCommand(new ChooseValueDiceCommand(getView()));
            }

        }

        registerCommand(new SolveToolCardCommand(getView()));
        registerCommand(new CancelUseOfToolCardCommand(getView()));

    }

    @Override
    public String getMessage() {
        StringBuilder toReturn = new StringBuilder();
        IModel model = getModel();
        ToolCard toolCard = model.getMainBoard().getData().getToolCards().get(
                model.getRoundInformation().getData().getToolCardParameters().getNCard());
        toReturn.append("\nInserisci tutti i dati necessari per utilizzare la carta da te selezionata: !\n")
                .append( toolCard.getToolTip()).append("\n");
        if (getModel().getMainBoard().getData().getPlayerCount()!=1)
            toReturn.append("Costo: ").append(model.getMainBoard().getData().getNCallToolCard(toolCard.getIndex()))
                    .append(" punti favore\n");
        return toReturn.toString();
    }
}
