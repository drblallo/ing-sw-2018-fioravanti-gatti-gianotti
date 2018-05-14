package progetto.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import progetto.game.AbstractGameAction;
import progetto.game.AbstractProcessor;
import progetto.game.ActionQueue;
import progetto.utils.IObserver;

public class ActionQueuePaneController {

    @FXML
    private ListView<Label> pastActionsListView;

    @FXML
    private Label nextAction;

    @FXML
    private Label numberOfPendingActions;

    private AbstractProcessor<AbstractGameAction> actionQueue;

    private IObserver<AbstractProcessor<AbstractGameAction>> actionQueueIObserver = ogg -> Platform.runLater(this::update);


    public final void setActionQueue(AbstractProcessor<AbstractGameAction> newActionQueue){


        if(actionQueue!=null){

            actionQueue.removeObserver(actionQueueIObserver);

        }

        actionQueue = newActionQueue;

        actionQueue.addObserver(actionQueueIObserver);

        Platform.runLater(this::update);

    }


    private void update(){

        pastActionsListView.getItems().clear();

        for(int i=0; i<actionQueue.getPastItemCount(); i++){

            pastActionsListView.getItems().add(new Label(actionQueue.getPastItem(i).getToolTip()));

        }

        numberOfPendingActions.setText(Integer.toString(actionQueue.getPendingItemsCount()));

        if(actionQueue.getPendingItemsCount()!=0){

            nextAction.setText(actionQueue.peekPending().getToolTip());

        }


    }
}
