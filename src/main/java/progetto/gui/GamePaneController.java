package progetto.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import progetto.commandline.CommandProcessor;
import progetto.game.IExecuibleGame;
import progetto.game.MainBoard;
import progetto.game.MainBoardData;
import progetto.utils.IObserver;

import java.io.IOException;

public class GamePaneController {

    private IExecuibleGame game = null;

    private int currentPlayer = 0;

    private int displayedPlayersCount = 1;

    @FXML
    private GridPane gridPane;

    int col = 0, row = 0;

    @FXML
    private Parent mainBoardPane;

    @FXML
    private Parent actionQueuePane;

    @FXML
    private Parent roundTrackPane;

    @FXML
    private Parent playerBoardPane;

    @FXML Parent commandLinePane;

    @FXML
    private MainBoardPaneController mainBoardPaneController;

    @FXML
    private ActionQueuePaneController actionQueuePaneController;

    @FXML
    private RoundTrackPaneController roundTrackPaneController;

    @FXML
    private CommandLinePaneController commandLinePaneController;

    @FXML
    private PlayerBoardPaneController playerBoardPaneController;

    private PlayerBoardPaneController[] playerBoardPaneControllers = new PlayerBoardPaneController[MainBoardData.MAX_NUM_PLAYERS-1];

    private IObserver<MainBoardData> mainBoardIObserver = ogg -> Platform.runLater(this::update);


    private int getCurrentPlayer() {

        return currentPlayer;

    }

    private int getDisplayedPlayersCount(){

        if(playerBoardPaneControllers == null){

            return 0;

        }

        return displayedPlayersCount;

    }

    public void setCurrentPlayer(int currentPlayer) {

        this.currentPlayer = currentPlayer;

    }

    //ECCEZIONE?
    public void setUp(IExecuibleGame newGame, CommandProcessor commandProcessor){

        if(game == newGame){

            return;

        }

        MainBoard mainBoard = newGame.getMainBoard();

        if(game != null){

            game.getMainBoard().removeObserver(mainBoardIObserver);

        }

        game = newGame;
        
        mainBoard.addObserver(mainBoardIObserver);

        playerBoardPaneControllers = new PlayerBoardPaneController[mainBoard.getMainBoardData().getPlayerCount()];

        commandLinePaneController.setCommandProcessor(commandProcessor);

        mainBoardPaneController.setObservable(mainBoard);

        actionQueuePaneController.setActionQueue(newGame.getActionQueue());

        roundTrackPaneController.setObservable(newGame.getRoundTrack());

        playerBoardPaneController.setObservable(newGame.getPlayerBoard(getCurrentPlayer()));

        playerBoardPaneControllers[getCurrentPlayer()] = playerBoardPaneController;

        gridPane.getChildren().clear();

        /*for (int i=0; i<mainBoard.getMainBoardData().getPlayerCount(); i++){

            if (i!=getCurrentPlayer()&&game.getPlayerBoard(i)!=null){

                addPlayerBoard(i);

            }

        }


*/
        //Platform.runLater(this::update);

    }

    protected void update(){

        int currentNumberOfPlayer = game.getMainBoard().getMainBoardData().getPlayerCount();

        if(getDisplayedPlayersCount() == currentNumberOfPlayer){

            return;

        }

        gridPane.getChildren().clear();

        col = 0;
        row = 0;

        for (int i = 0; i<currentNumberOfPlayer; i++){

            if(i!=currentPlayer) {

                addPlayerBoard(i);

            }

        }

        displayedPlayersCount = currentNumberOfPlayer;



    }

    private void addPlayerBoard(int i){

        Pane pane;

        FXMLLoader fxmlLoader = new FXMLLoader(GamePaneController.class.getResource("playerBoardPane.fxml"));

        try{

            pane = (Pane) fxmlLoader.load();

        }catch (IOException e){

            pane = null;
            System.out.println("IOEXception in GamePaneController"); //METTI LOGGER
        }

        playerBoardPaneControllers[i] = fxmlLoader.<PlayerBoardPaneController>getController();

        playerBoardPaneControllers[i].setObservable(game.getPlayerBoard(i));

        gridPane.add(pane, col, row);

        if(col == 1){

            row++;
            col = 0;

        }

        else {

            col++;

        }

    }
}
