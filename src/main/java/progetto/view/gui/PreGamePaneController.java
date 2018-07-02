package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import progetto.controller.SetDifficultyAction;
import progetto.controller.SetPlayerCountAction;
import progetto.controller.StartGameAction;
import progetto.model.AbstractMainBoard;
import progetto.model.MainBoardData;
import progetto.network.RoomView;
import progetto.utils.IObserver;

public class PreGamePaneController extends AbstractController{

    @FXML
    private Label currentNumberOfPlayers;
    @FXML
    private Label currentNumberOfChair;
    @FXML
    private ChoiceBox<Integer> numberOfPlayersChoice;
    @FXML
    private ChoiceBox<Integer> numberOfChairChoice;
    @FXML
    private ChoiceBox<Integer> difficultyChoice;
    @FXML
    private HBox playerAndDifficultyHBox;
    @FXML
    private HBox difficultyHBox;
    private static final int MAX_DIFFICULTY = 5;
    private IObserver<RoomView> roomViewIObserver = ogg -> Platform.runLater(()->updateRoomView(ogg));

    @FXML
    public void initialize(){
        for (int i = 0; i< MainBoardData.MAX_NUM_PLAYERS; i++){
            numberOfPlayersChoice.getItems().add(i+1);
        }
        for (int i = 1; i<MAX_DIFFICULTY + 1; i++){
            difficultyChoice.getItems().add(i);
        }
        playerAndDifficultyHBox.getChildren().remove(difficultyHBox);
    }

    @Override
    public void setUp(GUIView view){
        super.setUp(view);

        view.getController().getObservable().getMainBoard().addObserver((ogg) -> Platform.runLater(this::updateMainBoard));
        view.getController().getRoomViewCallback().addObserver(roomViewIObserver);
        numberOfPlayersChoice.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> view.getController().sendAction(new SetPlayerCountAction(newValue)) );
        difficultyChoice.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> view.getController().sendAction(new SetDifficultyAction(newValue))
        );
      }

    private void updateMainBoard() {
        MainBoardData mainBoardData = getModel().getMainBoard().getData();
        int playerCount = mainBoardData.getPlayerCount();
        if (playerCount == 1 ){
            if(!playerAndDifficultyHBox.getChildren().contains(difficultyHBox))
                playerAndDifficultyHBox.getChildren().add(difficultyHBox);
        }
        else playerAndDifficultyHBox.getChildren().remove(difficultyHBox);
        if (playerCount != Integer.parseInt(currentNumberOfPlayers.getText())){
            currentNumberOfPlayers.setText(mainBoardData.getPlayerCount() + "");
            Platform.runLater(()->updateRoomView(getController().getCurrentRoom()));
        }
    }

    private void updateRoomView(RoomView roomView){
            numberOfChairChoice.getItems().clear();
            numberOfChairChoice.getItems().add(-1);
            MainBoardData mainBoardData = getModel().getMainBoard().getData();
            for (int i = 0; i < mainBoardData.getPlayerCount(); i++) {
                if (roomView.getPlayerOfChair(i) == null)
                    numberOfChairChoice.getItems().add(i);
            currentNumberOfChair.setText(getController().getChair()+ "");
        }
    }

    @FXML
    private void onStartGameButtonClicked(){
        getController().sendAction(new StartGameAction());
    }

    @FXML
    private void onSitButtonPressed(){
        if(numberOfChairChoice.getSelectionModel().getSelectedItem()!=null)
            getController().pickChair(numberOfChairChoice.getSelectionModel().getSelectedItem());
    }

}
