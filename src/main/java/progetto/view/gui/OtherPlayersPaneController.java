package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import progetto.integration.client.view.AbstractClientStateController;

public class OtherPlayersPaneController extends AbstractClientStateController {

    @FXML
    private HBox otherPlayersHBox;
    private int displayedNumberOfPlayers = -1;
    private int myChair = -2;

    @Override
    public void setViewStateMachine(ViewStateMachine viewStateMachine)
    {
       super.setViewStateMachine(viewStateMachine);
       getViewStateMachine().getClientController().getRoomViewCallback()
               .addObserver(ogg -> Platform.runLater(this::update));
       getViewStateMachine().getObsModel().getMainBoard().addObserver(ogg -> Platform.runLater(this::update));
    }

    private void update() {
        int numberOfPlayers = getViewStateMachine().getModel().getMainBoard().getData().getPlayerCount();
        int currentChair = getViewStateMachine().getClientController().getChair();
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
                            .getPlayerBoard(i, getViewStateMachine().getGuiView()));
                    Region newRegion = new Region();
                    otherPlayersHBox.getChildren().add(newRegion);
                    HBox.setHgrow(newRegion, Priority.ALWAYS);
                }
            }
        }
    }

    @FXML
    private void onBackButtonClicked(){
        getViewStateMachine().getStateFromName("GamePane.fxml").show();
    }
}
