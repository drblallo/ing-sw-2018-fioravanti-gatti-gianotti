package progetto.integration.client.view.cl;

import progetto.controller.*;
import progetto.model.IModel;
import progetto.model.ToolCard;
import progetto.model.ToolCardActionList;
import progetto.model.ToolCardState;

public class UseToolCardState extends AbstractCLViewState {

    private int numberOfCard;

    public UseToolCardState(CommandLineView cl, int numberOfCard) {
        super("Use tool card state", cl);
        this.numberOfCard = numberOfCard;
    }

    @Override
    public boolean isStillValid() {
        IModel model = getModel();
        return model.getMainBoard().getData().getGameState().getClass() == ToolCardState.class &&
        model.getRoundInformation().getData().getCurrentPlayer() == getController().getChair() ;
    }

    @Override
    public void onApply() {

        ToolCard toolCard = getModel().getMainBoard().getData().getToolCards().get(numberOfCard);
        getController().sendAction(new UseToolCardAction(getController().getChair(), numberOfCard));

        registerCommand(new ShowPickedDicesCommand(getView()));
        registerCommand(new ShowPlayerBoardCommand(getView(), getController().getChair() , new Printer(), this));
        registerCommand(new ShowRoundTrackCommand(getView()));
        for (Class c: ToolCardActionList.getInstance().getList(toolCard.getIndex())) {

            if(c == ToolCardSetSinglePlayerDiceAction.class &&
                    getModel().getMainBoard().getData().getPlayerCount() == 1){
                registerCommand(new SetSinglePlayerDiceAction(getView()));
            }
            else if(c == ToolCardSetPickedDiceAction.class){
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
        return "\nInserisci tutti i dati necessari per utilizzare la carta da te selezionata: !\n" +
                getModel().getMainBoard().getData().getToolCards().get(numberOfCard).getToolTip() + '\n';
    }
}
