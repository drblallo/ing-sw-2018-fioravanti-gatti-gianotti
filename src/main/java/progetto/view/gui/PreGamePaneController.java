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

public class PreGamePaneController {

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
    private GUIView view;
    private IObserver<RoomView> roomViewIObserver = ogg -> Platform.runLater(()->updateRoomView(ogg));
    private ComposableController<MainBoardData, AbstractMainBoard> mainBoardComposableController =
            new ComposableController<>();

    @FXML
    public void initialize(){
        for (int i = 0; i< MainBoardData.MAX_NUM_PLAYERS; i++){
            numberOfPlayersChoice.getItems().add(i+1);
        }
        for (int i = 1; i<MAX_DIFFICULTY + 1; i++){
            difficultyChoice.getItems().add(i);
        }
        mainBoardComposableController.getOnModifiedCallback().addObserver
                (ogg -> Platform.runLater(this::updateMainBoard));
        playerAndDifficultyHBox.getChildren().remove(difficultyHBox);
    }

    public void setup(GUIView view){
        this.view = view;

        mainBoardComposableController.setObservable(view.getController().getObservable().getMainBoard());
        view.getController().getRoomViewCallback().addObserver(roomViewIObserver);
        numberOfPlayersChoice.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> view.getController().sendAction(new SetPlayerCountAction(newValue)) );
        difficultyChoice.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> view.getController().sendAction(new SetDifficultyAction(newValue))
        );
      }

    private void updateMainBoard() {
        MainBoardData mainBoardData = view.getController().getModel().getMainBoard().getData();
        int playerCount = mainBoardData.getPlayerCount();
        if (playerCount == 1 ){
            if(!playerAndDifficultyHBox.getChildren().contains(difficultyHBox))
                playerAndDifficultyHBox.getChildren().add(difficultyHBox);
        }
        else playerAndDifficultyHBox.getChildren().remove(difficultyHBox);
        if (playerCount != Integer.parseInt(currentNumberOfPlayers.getText())){
            currentNumberOfPlayers.setText(mainBoardData.getPlayerCount() + "");
            Platform.runLater(()->updateRoomView(view.getController().getCurrentRoom()));
        }
    }

    private void updateRoomView(RoomView roomView){
            numberOfChairChoice.getItems().clear();
            numberOfChairChoice.getItems().add(-1);
            MainBoardData mainBoardData = view.getController().getModel().getMainBoard().getData();
            for (int i = 0; i < mainBoardData.getPlayerCount(); i++) {
                if (roomView.getPlayerOfChair(i) == null)
                    numberOfChairChoice.getItems().add(i);
            currentNumberOfChair.setText(view.getController().getChair()+ "");
        }
    }

    @FXML
    private void onStartGameButtonClicked(){
        view.getController().sendAction(new StartGameAction());
    }

    @FXML
    private void onSitButtonPressed(){
        if(numberOfChairChoice.getSelectionModel().getSelectedItem()!=null)
            view.getController().pickChair(numberOfChairChoice.getSelectionModel().getSelectedItem());
    }

}
