package progetto.view.commandline.states;

import progetto.controller.*;
import progetto.model.*;
import progetto.view.ToolCardActionList;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.Printer;
import progetto.view.commandline.commands.*;

/**
 * State in which the user can select the information needed to use the selected card
 */
public class UseToolCardState extends AbstractCLViewState {

    /**
     * public constructor
     * @param cl the command line view that this state will be applied to
     */
    public UseToolCardState(CommandLineView cl) {
        super("Use tool card state", cl);
    }

    /**
     * Check if this state is still valid
     * @return if this state is still valid
     */
    @Override
    public boolean isStillValid() {
        IModel model = getModel();
        return model.getMainBoard().getData().getGameState().getClass() == ToolCardState.class &&
        model.getRoundInformation().getData().getCurrentPlayer() == getController().getChair() ;
    }

    /**
     * load the commands associated to this stage
     */
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
        registerCommand(new ShowExtractedDicesCommand(getView()));
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

    /**
     * Return a message associated to this stage
     * @return a message associated to this stage
     */
    @Override
    public String getMessage() {
        StringBuilder toReturn = new StringBuilder();
        IModel model = getModel();
        ToolCard toolCard = model.getMainBoard().getData().getToolCards().get(
                model.getRoundInformation().getData().getToolCardParameters().getNCard());
        toReturn.append("\nInserisci tutti i dati necessari per utilizzare la carta da te selezionata: \n")
                .append( toolCard.getToolTip()).append("\n");
        if (getModel().getMainBoard().getData().getPlayerCount()!=1){
            MainBoardData mainBoardData = model.getMainBoard().getData();
            toReturn.append("Costo: ").append(Math.min(1, Math.max(0, mainBoardData.getNCallToolCard(toolCard.getIndex()))) + 1)
                    .append(" punti favore\n");}
        return toReturn.toString();
    }
}
