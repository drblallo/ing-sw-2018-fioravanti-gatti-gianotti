package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import progetto.controller.ExecuteToolCardAction;
import progetto.controller.PlaceDiceAction;
import progetto.model.AbstractGameAction;
import progetto.model.CommandQueueData;
import progetto.network.RoomView;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Federica
 */
public class ActionQueuePaneController extends AbstractController{

    private static final Logger LOGGER = Logger.getLogger(ActionQueuePaneController.class.getName());
    @FXML
    private ListView<Label> pastActionsListView;
    @FXML
    private Label nextAction;
    @FXML
    private Label numberOfPendingActions;
    @FXML
    private TextArea otherPlayersActions;

    @Override
    public void setUp(GUIView view){
        super.setUp(view);
        view.getController().getObservable().getCommandQueue().addObserver(ogg -> update());
    }

    protected void update(){

        LOGGER.log(Level.FINE, "Reloading");
        CommandQueueData commandQueueData = getModel().getCommandQueue().getData();
        RoomView roomView = getController().getCurrentRoom();
        AbstractGameAction abstractGameAction = commandQueueData.getPastItem(0);
        String nameOfCaller;
        if (roomView.getPlayerOfChair(abstractGameAction.getCallerID()).getName()!=null){
            nameOfCaller = roomView.getPlayerOfChair(abstractGameAction.getCallerID()).getName();
        }
        else nameOfCaller = "Giocatore n° " + abstractGameAction.getCallerID();

        if (abstractGameAction.getCallerID() != getController().getChair()){
            if (abstractGameAction.getClass() == ExecuteToolCardAction.class){
                otherPlayersActions.appendText(nameOfCaller + "ha usato la carta: "
                        + abstractGameAction.getClass().getName());
            }
            else if (abstractGameAction.getClass() == PlaceDiceAction.class){
                otherPlayersActions.appendText(nameOfCaller + "ha piazzato un dado");
            }
        }




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
