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

/**
 * this is the class that handles the player menu fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 */
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

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     * @param view the current gui view
     */
    @Override
    public void setUp(GUIView view){
    	super.setUp(view);
        view.getController().getRoomViewCallback().addObserver(ogg -> Platform.runLater(this::onRoomChanged));
        view.getController().getObservable().getRoundInformation()
                .addObserver(ogg -> Platform.runLater(this::updateCurrentPlayer) );
        view.getController().getObservable().getMainBoard().addObserver(ogg -> updateMainBoard());
        AlertTurnBoxPaneController.setup();
    }

    /**
     * called when the current room changes
     * update user infos
     */
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

    /**
     * called when main board changes
     * change the scene if a multi player game turns into a single player one or if a single player
     * game turns into a multi player one
     */
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

    /**
     * called when the player board of the user changes
     * update the user's number of tokens
     */
    private void updatePlayerBoard() {
        PlayerBoardData playerBoardData = getModel().getPlayerBoard(lastChair).getData();
        if(playerBoardData.getToken()!=Integer.parseInt(numberOfTokens.getText()))
            numberOfTokens.setText(playerBoardData.getToken() + "");
    }

    /**
     * called when some round information changes
     * update the current player
     */
    private void updateCurrentPlayer(){
        int newCurrentPlayer = getModel().getRoundInformation().getData().getCurrentPlayer();
        if (Integer.parseInt(currentPlayer.getText())!=newCurrentPlayer){
            currentPlayer.setText(newCurrentPlayer + "");
            if (newCurrentPlayer == getController().getChair())
                AlertTurnBoxPaneController.display();
        }
    }

    /**
     * called when endTurnButton is clicked
     * Sent an EndTurnAction to the controller
     */
    @FXML
    private void onEndTurnButtonClicked(){
        if (getController().getChair()!=-1)
            getController().sendAction(new EndTurnAction(getController().getChair()));
    }

    /**
     * called when showOtherPlayersButton is clicked
     * change the scene to Other Players
     */
    @FXML
    private void onShowOtherPlayersButtonClicked(){
        getView().getStateManager().getStateFromName("OtherPlayersPane.fxml").show(true);
    }

}
