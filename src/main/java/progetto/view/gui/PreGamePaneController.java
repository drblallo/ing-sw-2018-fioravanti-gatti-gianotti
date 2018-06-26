package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import progetto.controller.SetPlayerCountAction;
import progetto.controller.StartGameAction;
import progetto.integration.client.view.GUIView;
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
    private GUIView view;
    private IObserver<RoomView> roomViewIObserver = ogg -> Platform.runLater(()->updateRoomView(ogg));
    private ComposableController<MainBoardData, AbstractMainBoard> mainBoardComposableController =
            new ComposableController<>();

    @FXML
    public void initialize(){
        for (int i = 0; i< MainBoardData.MAX_NUM_PLAYERS; i++){
            numberOfPlayersChoice.getItems().add(i+1);
        }
        mainBoardComposableController.getOnModifiedCallback().addObserver
                (ogg -> Platform.runLater(this::updateMainBoard));
    }

    public void setup(GUIView view){
        this.view = view;

        mainBoardComposableController.setObservable(view.getController().getObservable().getMainBoard());
        view.getController().getRoomViewCallback().addObserver(roomViewIObserver);
        numberOfPlayersChoice.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> view.getController().sendAction(new SetPlayerCountAction(newValue)) );

      }

    private void updateMainBoard() {
        MainBoardData mainBoardData = view.getController().getModel().getMainBoard().getData();
        if (mainBoardData.getPlayerCount() != Integer.parseInt(currentNumberOfPlayers.getText()))
            currentNumberOfPlayers.setText(mainBoardData.getPlayerCount() + "");
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
