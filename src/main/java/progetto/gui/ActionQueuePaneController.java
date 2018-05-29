package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import progetto.game.*;

public class ActionQueuePaneController extends AbstractController<CommandQueueData, DataContainer<CommandQueueData>>{

    @FXML
    private ListView<Label> pastActionsListView;

    @FXML
    private Label nextAction;

    @FXML
    private Label numberOfPendingActions;

    @Override
    protected void update(){

        CommandQueueData commandQueueData = getObservable().getData();

        pastActionsListView.getItems().clear();

        for(int i=0; i<commandQueueData.pastSize(); i++){

            pastActionsListView.getItems().add(new Label(commandQueueData.getPastItem(i).getToolTip()));

        }

        numberOfPendingActions.setText(Integer.toString(commandQueueData.pendingSize()));

        if(commandQueueData.pendingSize()!=0){

            nextAction.setText(commandQueueData.peekPending().getToolTip());

        }


    }
}
