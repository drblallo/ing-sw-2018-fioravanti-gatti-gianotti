package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class OtherPlayersPaneController extends AbstractStateController {

    @FXML
    private HBox otherPlayersHBox;
    private int displayedNumberOfPlayers = -1;
    private int myChair = -2;
    /*@FXML
    private TextArea otherPlayersActions; */

    @Override
    public void setStateManager(StateManager stateManager)
    {
       super.setStateManager(stateManager);
       getStateManager().getClientController().getRoomViewCallback()
               .addObserver(ogg -> Platform.runLater(this::update));
       getStateManager().getObsModel().getMainBoard().addObserver(ogg -> Platform.runLater(this::update));
       //getViewStateMachine().getObsModel().getCommandQueue().addObserver(ogg -> updateOtherPlayersActions());
    }

    private void update() {
        int numberOfPlayers = getStateManager().getModel().getMainBoard().getData().getPlayerCount();
        int currentChair = getChair();
        if (numberOfPlayers != displayedNumberOfPlayers || currentChair != myChair) {
            displayedNumberOfPlayers = numberOfPlayers;
            myChair = currentChair;
            otherPlayersHBox.getChildren().clear();
            Region region = new Region();
            otherPlayersHBox.getChildren().add(region);
            HBox.setHgrow(region, Priority.ALWAYS);
            for (int i = 0; i < displayedNumberOfPlayers; i++){
                if (i!=myChair) {
                    otherPlayersHBox.getChildren().add(PlayerBoardPaneController
                            .getPlayerBoard(i, getStateManager().getGuiView()));
                    Region newRegion = new Region();
                    otherPlayersHBox.getChildren().add(newRegion);
                    HBox.setHgrow(newRegion, Priority.ALWAYS);
                }
            }
        }
    }

    /*private void updateOtherPlayersActions(){
        CommandQueueData commandQueueData = getController().getModel().getCommandQueue().getData();
        RoomView roomView = getController().getCurrentRoom();
        AbstractGameAction abstractGameAction = commandQueueData.getPastItem(0);
        if (abstractGameAction == null || abstractGameAction.getCallerID() == -1)
            return;
        String nameOfCaller;
        if (roomView.getPlayerOfChair(abstractGameAction.getCallerID()).getName()!=null){
            nameOfCaller = roomView.getPlayerOfChair(abstractGameAction.getCallerID()).getName();
        }
        else nameOfCaller = "Giocatore n° " + abstractGameAction.getCallerID();

        if (abstractGameAction.getCallerID()!= getController().getChair()){
            if (abstractGameAction.getClass() == ExecuteToolCardAction.class){
                otherPlayersActions.appendText(nameOfCaller + "ha usato la carta: "
                + abstractGameAction.getName());
            }
            else if (abstractGameAction.getClass() == PlaceDiceAction.class){
                otherPlayersActions.appendText(nameOfCaller + "ha piazzato un dado");
            }
        }
    } */

    @FXML
    private void onBackButtonClicked(){
        getStateManager().getStateFromName("GamePane.fxml").show(true);
    }
}
