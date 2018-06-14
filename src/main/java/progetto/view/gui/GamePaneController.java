package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import progetto.model.AbstractMainBoard;
import progetto.model.IModel;
import progetto.model.MainBoardData;
import progetto.utils.IObserver;
import progetto.view.commandline.ICommandProcessor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GamePaneController extends AbstractStateController{

    private static final Logger LOGGER = Logger.getLogger(GamePaneController.class.getName());

    private IModel game = null;

    private int displayedPlayersCount = -1;

    @FXML
    private GridPane gridPane;

    @FXML
    private TabPane tabPane;

    @FXML
    private MainBoardPaneController mainBoardPaneController;

    @FXML
    private ActionQueuePaneController actionQueuePaneController;

    @FXML
    private CommandLinePaneController commandLinePaneController;

    @FXML
    private RoundTrackPaneController roundTrackPaneController;

    private PlayerBoardPaneController[] playerBoardPaneControllers =
            new PlayerBoardPaneController[MainBoardData.MAX_NUM_PLAYERS];

    private IObserver<MainBoardData> mainBoardIObserver = ogg -> Platform.runLater(this::update);

    public void onPreShow(ICommandProcessor commandProcessor){

        if(game == getViewStateMachine().getCurrentGame()){

            return;

        }

        AbstractMainBoard mainBoard = getViewStateMachine().getCurrentGame().getModel().getMainBoard();

        if(game != null){

            game.getMainBoard().removeObserver(mainBoardIObserver);

        }

        game = getViewStateMachine().getCurrentGame().getModel();

        mainBoard.addObserver(mainBoardIObserver);

        commandLinePaneController.setCommandProcessor(commandProcessor);

        mainBoardPaneController.onGameChanged(game);

        actionQueuePaneController.setObservable(game.getCommandQueue());

        roundTrackPaneController.setObservable(game.getRoundTrack());
        roundTrackPaneController.setup();


        update();

    }

    protected void update(){

        int currentNumberOfPlayer = game.getMainBoard().getData().getPlayerCount();

        if(displayedPlayersCount == currentNumberOfPlayer){

            return;

        }

        gridPane.getChildren().clear();

        for (int i = 0; i<currentNumberOfPlayer; i++){

                addPlayerBoard(i);

        }

        displayedPlayersCount = currentNumberOfPlayer;



    }

    private void addPlayerBoard(int i){

        Pane pane;

        FXMLLoader fxmlLoader = new FXMLLoader(GamePaneController.class.getResource("PlayerBoardPane.fxml"));

        try{

            pane = fxmlLoader.load();

        }catch (IOException e){

            pane = null;
            LOGGER.log(Level.SEVERE, e.getMessage());
        }

        playerBoardPaneControllers[i] = fxmlLoader.getController();
        playerBoardPaneControllers[i].setObservers
                (game.getPlayerBoard(i).getDicePlacedFrame(), game.getPlayerBoard(i));

        gridPane.add(pane, i/2, i%2);

    }

    public void addChatPane(Pane pane){

        Tab tab = new Tab("Chat Pane");

        tab.setId("chatPane");

        tab.setContent(pane);

        tabPane.getTabs().add(tab);

    }

    @FXML
    private void onBackButtonClicked(){

        getViewStateMachine().getStateFromName("StartingPane.fxml").show();
        commandLinePaneController.clearTextArea();

    }

}
