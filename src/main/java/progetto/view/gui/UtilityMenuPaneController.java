package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import progetto.network.RoomView;

/**
 * this is the class that handles the utility menu fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 */
public class UtilityMenuPaneController extends AbstractController{

    @FXML
    private ChoiceBox<Integer> chairs;
    @FXML
    private Button chatButton;

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     * @param view the current gui view
     */
    @Override
    public void setUp(GUIView view){
    	super.setUp(view);
        view.getController().getRoomViewCallback().addObserver(ogg -> Platform.runLater(this::updateChairs));
        view.getController().getObservable().getMainBoard().addObserver(ogg -> Platform.runLater(this::isSinglePlayer));
    }

    /**
     * called when menuButton is clicked
     * changes the scene to Starting Pane
     */
    @FXML
    private void onMenuButtonClicked(){
        getView().getStateManager().getStateFromName("StartingPane.fxml").show(false);
    }

    /**
     * called when chatButton is clicked
     * changes the scene to Chat Pane
     */
    @FXML
    private void onChatButtonClicked(){
        getView().getStateManager().getStateFromName("ChatPane.fxml").show(true);
    }

    /**
     * called when current rooms changes
     * update available chairs
     */
    private void updateChairs(){
        RoomView roomView = getController().getCurrentRoom();
        int playerCount = getController().getModel().getMainBoard().getData().getPlayerCount();

        chairs.getItems().clear();
        chairs.getItems().add(-1);

        for(int i=0; i<playerCount; i++){
            if(roomView.getPlayerOfChair(i)==null){
                chairs.getItems().add(i);
            }
        }
    }

    /**
     * called when main board changes
     * if the game is in single player mode remove chatButton
     */
    private void isSinglePlayer(){
        int numberOfPlayers = getController().getModel().getMainBoard().getData().getPlayerCount();
        if (numberOfPlayers == 1){
            if (!chatButton.isDisabled())
                chatButton.setDisable(true);
        }
        else if (chatButton.isDisabled())
            chatButton.setDisable(false);
    }

    /**
     * called when sitButton is clicked
     * the user pick the selected chair
     */
    @FXML
    private void onSitButtonPressed(){
        if (chairs.getSelectionModel().getSelectedItem()!=null){
            getController().pickChair(chairs.getSelectionModel().selectedItemProperty().get());
        }
    }

}
