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

/**
 * this is the class that handles the pre game fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 */
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

    /**
     * called every time this pane is shown
     * clear selected items
     */
    public void onPreShow(){
        numberOfPlayersChoice.getSelectionModel().clearSelection();
        difficultyChoice.getSelectionModel().clearSelection();
        updateMainBoard();
        updateRoomView();
    }

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     * @param view the current gui view
     */
    @Override
    public void setUp(GUIView view){
        super.setUp(view);

        for (int i = 0; i< MainBoardData.MAX_NUM_PLAYERS; i++){
            numberOfPlayersChoice.getItems().add(i+1);
        }
        for (int i = 1; i<MAX_DIFFICULTY + 1; i++){
            difficultyChoice.getItems().add(i);
        }
        playerAndDifficultyHBox.getChildren().remove(difficultyHBox);
        view.getController().getObservable().getMainBoard().addObserver(ogg -> Platform.runLater(this::updateMainBoard));
        view.getController().getRoomViewCallback().addObserver(ogg -> Platform.runLater(()->updateRoomView()));
        numberOfPlayersChoice.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue!=null)
                        view.getController().sendAction(new SetPlayerCountAction(newValue));
                } );
        difficultyChoice.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue!=null)
                        view.getController().sendAction(new SetDifficultyAction(newValue));
                }
        );
      }

    /**
     * called when main board changes
     * update the possible options, which are different if there will be a single or a multi player game
      */
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
            Platform.runLater(()->updateRoomView());
        }
    }

    /**
     * update when the current room changes
     */
    private void updateRoomView(){
            RoomView roomView = getController().getCurrentRoom();
            numberOfChairChoice.getItems().clear();
            numberOfChairChoice.getItems().add(-1);
            MainBoardData mainBoardData = getModel().getMainBoard().getData();
            for (int i = 0; i < mainBoardData.getPlayerCount(); i++) {
                if (roomView.getPlayerOfChair(i) == null)
                    numberOfChairChoice.getItems().add(i);
            currentNumberOfChair.setText(getController().getChair()+ "");
        }
            numberOfPlayersChoice.setValue(-1);
    }

    /**
     * called when startGameButton is clicked
     * send a StartGameAction to the controller
     */
    @FXML
    private void onStartGameButtonClicked(){
        getController().sendAction(new StartGameAction());
    }

    /**
     * called when sitButton is clicked
     * pick the selected chair
     */
    @FXML
    private void onSitButtonPressed(){
        if(numberOfChairChoice.getSelectionModel().getSelectedItem()!=null)
            getController().pickChair(numberOfChairChoice.getSelectionModel().getSelectedItem());
    }

}
