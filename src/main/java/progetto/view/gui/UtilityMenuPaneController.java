package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import progetto.network.RoomView;

public class UtilityMenuPaneController {

    private GUIView view;
    @FXML
    private ChoiceBox<Integer> chairs;
    @FXML
    private Button chatButton;

    public void setup(GUIView view){
        this.view = view;
        view.getController().getRoomViewCallback().addObserver(ogg -> Platform.runLater(this::updateChairs));
        view.getController().getObservable().getMainBoard().addObserver(ogg -> Platform.runLater(this::isSinglePlayer));
    }

    @FXML
    private void onMenuButtonClicked(){
        view.getViewStateMachine().getStateFromName("StartingPane.fxml").show(false);
    }

    @FXML
    private void onChatButtonClicked(){
        view.getViewStateMachine().getStateFromName("ChatPane.fxml").show(true);
    }

    private void updateChairs(){
        RoomView roomView = view.getController().getCurrentRoom();
        int playerCount = view.getController().getModel().getMainBoard().getData().getPlayerCount();

        chairs.getItems().clear();
        chairs.getItems().add(-1);

        for(int i=0; i<playerCount; i++){
            if(roomView.getPlayerOfChair(i)==null){
                chairs.getItems().add(i);
            }
        }
    }

    private void isSinglePlayer(){
        int numberOfPlayers = view.getController().getModel().getMainBoard().getData().getPlayerCount();
        if (numberOfPlayers == 1){
            if (!chatButton.isDisabled())
                chatButton.setDisable(true);
        }
        else if (chatButton.isDisabled())
            chatButton.setDisable(false);
    }

    /**
     * when the player clicks on the button it tries to take a chair
     */
    @FXML
    private void onSitButtonPressed(){
        if (chairs.getSelectionModel().getSelectedItem()!=null){
            view.getController().pickChair(chairs.getSelectionModel().selectedItemProperty().get());
        }
    }

}
