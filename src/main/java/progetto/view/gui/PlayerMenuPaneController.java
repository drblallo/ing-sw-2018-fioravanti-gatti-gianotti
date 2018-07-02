package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import progetto.controller.EndTurnAction;
import progetto.model.Container;
import progetto.model.PlayerBoardData;
import progetto.utils.IObserver;

public class PlayerMenuPaneController extends AbstractController{

    @FXML
    private Label numberOfTokens;
    @FXML
    private Label currentPlayer;
    @FXML
    private VBox mainVBox;
    @FXML
    private HBox tokensHBox;
    @FXML
    private Button showOtherPlayersButton;
    private int lastPlayerCount = -1;
    private IObserver<PlayerBoardData> playerObserver = ogg -> Platform.runLater(this::updatePlayerBoard);
    private Container<PlayerBoardData> playerBoard;
    private int lastChair = -2;

    @Override
    public void setUp(GUIView view){
    	super.setUp(view);
        view.getController().getRoomViewCallback().addObserver(ogg -> Platform.runLater(this::onRoomChanged));
        view.getController().getObservable().getRoundInformation()
                .addObserver(ogg -> Platform.runLater(this::updateCurrentPlayer) );
        view.getController().getObservable().getMainBoard().addObserver(ogg -> updateMainBoard());
        AlertTurnBoxPaneController.setup();
    }

    private void onRoomChanged()
    {
        int currentChair = Math.max(getController().getChair(), 0);
        if (currentChair != lastChair)
        {
            lastChair = currentChair;
            if (playerBoard!=null)
                playerBoard.removeObserver(playerObserver);
            playerBoard = getController().getObservable().getPlayerBoard(currentChair);
            playerBoard.addObserver(playerObserver);
            updatePlayerBoard();
        }
    }

    private void updateMainBoard(){
        int currentPlayerCount = getModel().getMainBoard().getData().getPlayerCount();
        if(currentPlayerCount!=lastPlayerCount){
            if(currentPlayerCount == 1){
                mainVBox.getChildren().remove(tokensHBox);
                mainVBox.getChildren().removeAll(showOtherPlayersButton);
            }
            else if(lastPlayerCount == 1){
                mainVBox.getChildren().add(tokensHBox);
                mainVBox.getChildren().add(showOtherPlayersButton);
            }
            lastPlayerCount = currentPlayerCount;
        }
    }

    private void updatePlayerBoard() {
        PlayerBoardData playerBoardData = getModel().getPlayerBoard(lastChair).getData();
        if(playerBoardData.getToken()!=Integer.parseInt(numberOfTokens.getText()))
            numberOfTokens.setText(playerBoardData.getToken() + "");
    }

    private void updateCurrentPlayer(){
        int newCurrentPlayer = getModel().getRoundInformation().getData().getCurrentPlayer();
        if (Integer.parseInt(currentPlayer.getText())!=newCurrentPlayer){
            currentPlayer.setText(newCurrentPlayer + "");
            if (newCurrentPlayer == getController().getChair())
                AlertTurnBoxPaneController.display();
        }
    }

    @FXML
    private void onEndTurnButtonClicked(){
        if (getController().getChair()!=-1)
            getController().sendAction(new EndTurnAction(getController().getChair()));
    }

    @FXML
    private void onShowOtherPlayersButtonClicked(){
        getView().getStateManager().getStateFromName("OtherPlayersPane.fxml").show(true);
    }

}
