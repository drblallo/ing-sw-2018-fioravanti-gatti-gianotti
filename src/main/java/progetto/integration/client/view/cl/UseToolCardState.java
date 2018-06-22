package progetto.integration.client.view.cl;

import progetto.controller.*;
import progetto.model.ToolCard;

public class UseToolCardState extends AbstractCLViewState {

    private int numberOfCard;

    public UseToolCardState(CommandLineView cl, int numberOfCard) {
        super("Use tool card state", cl);
        this.numberOfCard = numberOfCard;
    }

    @Override
    public boolean isStillValid() {
        return getModel().getRoundInformation().getData().getCurrentPlayer() == getController().getChair();
    }

    @Override
    public void onApply() {

        ToolCard toolCard = getModel().getMainBoard().getData().getToolCards().get(numberOfCard);
        getController().sendAction(new UseToolCardAction(getController().getChair(), numberOfCard));

        int i = 1;
        registerCommand(new ShowPickedDicesCommand(getView(), i));
        i++;
        registerCommand(new ShowPlayerBoardCommand(getView(), getController().getChair() ,i,
                new Printer(), this));
        i++;
        registerCommand(new ShowRoundTrackCommand(getView(), i));
        i++;
        for (Class c: toolCard.getCardAction()) {

            if(c == ToolCardSetSinglePlayerDiceAction.class &&
                    getModel().getMainBoard().getData().getPlayerCount() == 1){
                registerCommand(new SetSinglePlayerDiceAction(getView(), i));
                i++;
            }
            else if(c == ToolCardSetPickedDiceAction.class){
                registerCommand(new ChoosePickedDiceCommand(getView(), i));
                i++;
            }
            else if(c == ToolCardSetIncreaseDecreaseAction.class){
                registerCommand(new IncreaseDecreseCommand(getView(),0,i));
                i++;
                registerCommand(new IncreaseDecreseCommand(getView(), 1,i));
                i++;
            }
            else if(c == ToolCardSetPlacedDiceAction.class){
                registerCommand(new ChoosePlayerBoardDiceCommand(getView(), i,1));
                i++;
            }
            else if(c == ToolCardSetDiceRoundTrackAction.class){
                registerCommand(new ChooseRoundTrackDiceCommand(getView(), i));
                i++;
            }
            else if(c == ToolCardSetSecondPlacedDiceAction.class){
                registerCommand(new ChoosePlayerBoardDiceCommand(getView(), i, 2));
                i++;
            }
            else if (c == ToolCardSetDiceValueAction.class){
                registerCommand(new ChooseValueDiceCommand(getView(), i));
            }

        }

        registerCommand(new SolveToolCardCommand(getView(), i));
        i++;
        registerCommand(new CancelUseOfToolCardCommand(getView(),i));

    }

    @Override
    public String getMessage() {
        return "\nInserisci tutti i dati necessari per utilizzare la carta da te selezionata: !\n" +
                getModel().getMainBoard().getData().getToolCards().get(numberOfCard).getToolTip() + '\n';
    }
}
