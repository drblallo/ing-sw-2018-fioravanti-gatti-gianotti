package progetto.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import progetto.commandline.CommandProcessor;
import progetto.game.IExecuibleGame;
import progetto.game.MainBoard;
import progetto.game.MainBoardData;
import progetto.utils.IObserver;

import java.io.IOException;

public class GamePaneController extends AbstractStateController{

    private IExecuibleGame game = null;

    private int myChair = 0;

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

    @FXML
    private TabPane tabPane;

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


    private int getMyChair() {

        return myChair;

    }

    private int getDisplayedPlayersCount(){

        if(playerBoardPaneControllers == null){

            return 0;

        }

        return displayedPlayersCount;

    }

    public void setMyChair(int myChair) {

        this.myChair = myChair;

    }


    public void onPreShow(CommandProcessor commandProcessor){

        if(game == getViewStateMachine().getCurrentGame()){

            return;

        }


        MainBoard mainBoard = getViewStateMachine().getCurrentGame().getMainBoard();

        if(game != null){

            game.getMainBoard().removeObserver(mainBoardIObserver);

        }

        game = getViewStateMachine().getCurrentGame();
        
        mainBoard.addObserver(mainBoardIObserver);

        playerBoardPaneControllers = new PlayerBoardPaneController[mainBoard.getMainBoardData().getPlayerCount()];

        commandLinePaneController.setCommandProcessor(commandProcessor);

        mainBoardPaneController.setObservable(mainBoard);

        actionQueuePaneController.setActionQueue(game.getActionQueue());

        roundTrackPaneController.setObservable(game.getRoundTrack());

        playerBoardPaneController.setObservable(game.getPlayerBoard(getMyChair()));
        playerBoardPaneController.setup();

        playerBoardPaneControllers[getMyChair()] = playerBoardPaneController;

        gridPane.getChildren().clear();

        update();

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

            if(i!=myChair) {

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
        playerBoardPaneController.setup();

        gridPane.add(pane, col, row);

        if(col == 1){

            row++;
            col = 0;

        }

        else {

            col++;

        }

    }

    public void addChatPane(Pane pane){

        Tab tab = new Tab("Room Pane");

        tab.setContent(pane);

        tabPane.getTabs().add(tab);

    }

    @FXML
    private void onBackButtonClicked(){

        getViewStateMachine().getStateFromName("StartingPane.fxml").show();
        commandLinePaneController.clearTextArea();

    }

}
