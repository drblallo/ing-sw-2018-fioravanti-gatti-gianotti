package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import progetto.controller.EndTurnAction;
import progetto.model.Container;
import progetto.model.PlayerBoardData;
import progetto.utils.IObserver;

public class PlayerMenuPaneController {

    @FXML
    private Label numberOfTokens;
    @FXML
    private Label currentPlayer;

    private GUIView view;
    private IObserver<PlayerBoardData> playerObserver = ogg -> Platform.runLater(this::updatePlayerBoard);
    private Container<PlayerBoardData> playerBoard;
    private int lastChair = -2;

    public void setup(GUIView view){
        this.view = view;
        view.getController().getRoomViewCallback().addObserver(ogg -> Platform.runLater(this::onRoomChanged));
        view.getController().getObservable().getRoundInformation()
                .addObserver(ogg -> Platform.runLater(this::updateCurrentPlayer) );
        AlertTurnBoxPaneController.setup();
    }

    private void onRoomChanged()
    {
        int currentChair = Math.max(view.getController().getChair(), 0);
        if (currentChair != lastChair)
        {
            lastChair = currentChair;
            if (playerBoard!=null)
                playerBoard.removeObserver(playerObserver);
            playerBoard = view.getController().getObservable().getPlayerBoard(currentChair);
            playerBoard.addObserver(playerObserver);
            updatePlayerBoard();
        }
    }

    private void updatePlayerBoard() {
        PlayerBoardData playerBoardData = view.getController().getModel().getPlayerBoard(lastChair).getData();
        if(playerBoardData.getToken()!=Integer.parseInt(numberOfTokens.getText()))
            numberOfTokens.setText(playerBoardData.getToken() + "");
    }

    private void updateCurrentPlayer(){
        int newCurrentPlayer = view.getController().getModel().getRoundInformation().getData().getCurrentPlayer();
        if (Integer.parseInt(currentPlayer.getText())!=newCurrentPlayer){
            currentPlayer.setText(newCurrentPlayer + "");
            if (newCurrentPlayer == view.getController().getChair())
                AlertTurnBoxPaneController.display();
        }
    }

    @FXML
    private void onEndTurnButtonClicked(){
        if (view.getController().getChair()!=-1)
            view.getController().sendAction(new EndTurnAction(view.getController().getChair()));
    }

    @FXML
    private void onShowOtherPlayersButtonClicked(){
        view.getViewStateMachine().getStateFromName("OtherPlayersPane.fxml").show(true);
    }

}
