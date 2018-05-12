package progetto.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import progetto.game.Game;
import progetto.game.MainBoard;
import progetto.game.MainBoardData;
import progetto.utils.IObserver;

import java.io.IOException;

public class GamePaneController {

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private Game oldGame = null;

    private int currentPlayer = 0;

    @FXML
    private VBox vBox;

    @FXML
    private Parent mainBoardPane;

    @FXML
    private Parent actionQueuePane;

    @FXML
    private Parent roundTrackPane;

    @FXML
    private Parent playerBoardPane;

    private MainBoardPaneController mainBoardPaneController;

    private ActionQueuePaneController actionQueuePaneController;

    private RoundTrackPaneController roundTrackPaneController;

    private PlayerBoardPaneController[] playerBoardPaneControllers = null;

    private IObserver<MainBoardData> mainBoardIObserver = ogg -> Platform.runLater(this::update);


    private int getCurrentPlayer() {

        return currentPlayer;

    }

    private int getDisplayedPlayersCount(){

        if(playerBoardPaneControllers == null){

            return 0;

        }

        return playerBoardPaneControllers.length;

    }

    public void setCurrentPlayer(int currentPlayer) {

        this.currentPlayer = currentPlayer;

    }

    //ECCEZIONE?
    public void setUp(Game game){

        if(oldGame == game){

            return;

        }

        MainBoard mainBoard = game.getMainBoard();

        if(oldGame != null){

            oldGame.getMainBoard().removeObserver(mainBoardIObserver);

        }

        mainBoard.addObserver(mainBoardIObserver);

        playerBoardPaneControllers = new PlayerBoardPaneController[mainBoard.getMainBoardData().getPlayerCount()];

        fxmlLoader.setRoot(mainBoardPane);
        mainBoardPaneController = fxmlLoader.<MainBoardPaneController>getController();
        mainBoardPaneController.setObservable(mainBoard);

        fxmlLoader.setRoot(actionQueuePane);
        actionQueuePaneController = fxmlLoader.<ActionQueuePaneController>getController();
        actionQueuePaneController.setActionQueue(game.getActionQueue());

        fxmlLoader.setRoot(roundTrackPane);
        roundTrackPaneController = fxmlLoader.<RoundTrackPaneController>getController();
        roundTrackPaneController.setObservable(game.getRoundTrack());

        fxmlLoader.setRoot(playerBoardPane);
        playerBoardPaneControllers[getCurrentPlayer()] = fxmlLoader.<PlayerBoardPaneController>getController();
        playerBoardPaneControllers[getCurrentPlayer()].setObservable(game.getPlayerBoard(getCurrentPlayer()));

        vBox.getChildren().clear();

        for (int i=0; i<mainBoard.getMainBoardData().getPlayerCount(); i++){

            if (i!=getCurrentPlayer()){

                addPlayerBoard(i);

            }

        }

        Platform.runLater(this::update);

    }

    protected void update(){

        if(getDisplayedPlayersCount() == oldGame.getMainBoard().getMainBoardData().getPlayerCount()){

            return;

        }

        for (int i = getDisplayedPlayersCount() + 1; i<oldGame.getMainBoard().getMainBoardData().getPlayerCount(); i++){

            addPlayerBoard(i);

        }



    }

    private void addPlayerBoard(int i){

        Pane pane;

        fxmlLoader.setRoot(GamePaneController.class.getResource("playerBoardPane.fxml"));

        playerBoardPaneControllers[i] = fxmlLoader.<PlayerBoardPaneController>getController();

        playerBoardPaneControllers[i].setObservable(oldGame.getPlayerBoard(i));

        try{

            pane = (Pane) fxmlLoader.load();

        }catch (IOException e){

            pane = null;
            System.out.println("IOEXception in GamePaneController"); //METTI LOGGER
        }

        vBox.getChildren().add(pane);

    }
}
