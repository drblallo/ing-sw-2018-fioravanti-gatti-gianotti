package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * this is the class that handles the other players fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 * @author Federica
 */
public class OtherPlayersPaneController extends AbstractStateController {

    @FXML
    private AnchorPane myPane;
    @FXML
    private HBox otherPlayersHBox;
    private int displayedNumberOfPlayers = -1;
    private int myChair = -2;

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     */
    @Override
    public void setup() {

        Image image = new Image(getClass().getResourceAsStream("background.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE,
                true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        myPane.setBackground(background);

        getController().getRoomViewCallback()
                .addObserver(ogg -> Platform.runLater(this::update));
        getView().getStateManager().getObsModel().getMainBoard().addObserver(ogg -> Platform.runLater(this::update));
    }

    /**
     * update and show the playerboard of the other players
     */
    private void update() {
        int numberOfPlayers = getModel().getMainBoard().getData().getPlayerCount();
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
                    otherPlayersHBox.getChildren().add(PlayerBoardPaneController.getPlayerBoard(i, getView()));
                    Region newRegion = new Region();
                    otherPlayersHBox.getChildren().add(newRegion);
                    HBox.setHgrow(newRegion, Priority.ALWAYS);
                }
            }
        }
    }

    /**
     * called when backButton is clicked
     * change the scene to Game Pane
     */
    @FXML
    private void onBackButtonClicked(){
        getView().getStateManager().getStateFromName("GamePane.fxml").show(true);
    }
}
