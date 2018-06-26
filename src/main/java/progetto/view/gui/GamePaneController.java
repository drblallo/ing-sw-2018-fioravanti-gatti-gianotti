package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import progetto.model.IModel;
import progetto.model.PreGameState;
import progetto.view.commandline.ICommandProcessor;

public class GamePaneController extends AbstractStateController{

    private int displayedPlayersCount = -1;
    private int lastChair = -1;
    @FXML
    private HBox hBox;
    @FXML
    private TabPane tabPane;
    @FXML
    private VBox mainVBox;
    @FXML
    private VBox roundVBox;
    @FXML
    private VBox preGameVBox;
    @FXML
    private MainBoardPaneController mainBoardPaneController;
    @FXML
    private PreGamePaneController preGamePaneController;
    @FXML
    private ActionQueuePaneController actionQueuePaneController;
    @FXML
    private CommandLinePaneController commandLinePaneController;
    @FXML
    private RoundTrackPaneController roundTrackPaneController;
    @FXML
    private PlayerMenuPaneController playerMenuPaneController;

    @Override
    public void setup() {
        super.setup();
        mainBoardPaneController.setup(getViewStateMachine().getGuiView());
        playerMenuPaneController.setup(getViewStateMachine().getGuiView());
        roundTrackPaneController.setup(getViewStateMachine().getGuiView());
        preGamePaneController.setup(getViewStateMachine().getGuiView());
        mainVBox.getChildren().removeAll(preGameVBox, roundVBox);

        getViewStateMachine().getObsModel().getMainBoard().addObserver(ogg -> Platform.runLater(this::update));
        getViewStateMachine().getClientController().getRoomViewCallback().addObserver(ogg -> {
            if(getViewStateMachine().getClientController().getChair()!=lastChair){
                Platform.runLater(this::update);
            }
        });
    }

    public void onPreShow(ICommandProcessor commandProcessor){

        commandLinePaneController.setCommandProcessor(commandProcessor);
        actionQueuePaneController.setObservable(getObsModel().getCommandQueue());
        update();
    }

    protected void update(){
        IModel model = getModel();
        if(model.getMainBoard().getData().getGameState().getClass() == PreGameState.class){
            mainVBox.getChildren().remove(roundVBox);
            if (!mainVBox.getChildren().contains(preGameVBox))
                mainVBox.getChildren().add(preGameVBox);
        }else {
            mainVBox.getChildren().remove(preGameVBox);
            if (!mainVBox.getChildren().contains(roundVBox))
                mainVBox.getChildren().add(roundVBox);
        }

        int currentNumberOfPlayer = model.getMainBoard().getData().getPlayerCount();
        int currentChair = getViewStateMachine().getClientController().getChair();
        if(displayedPlayersCount == currentNumberOfPlayer && lastChair ==
               currentChair){
            return;
        }
        hBox.getChildren().clear();
        if(currentChair == -1){
            addPlayerBoard(0);
        }
        else {
            addPlayerBoard(currentChair);
        }
        displayedPlayersCount = currentNumberOfPlayer;
        lastChair = currentChair;
    }

    private void addPlayerBoard(int i){
        Pane pane = PlayerBoardPaneController.getPlayerBoard(i, getViewStateMachine().getGuiView());
        hBox.getChildren().add(pane);
        Region region = new Region();
        hBox.getChildren().add(region);
        HBox.setHgrow(region, Priority.ALWAYS);
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
