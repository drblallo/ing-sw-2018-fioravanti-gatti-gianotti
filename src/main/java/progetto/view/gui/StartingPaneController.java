package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * this is the class that handles the starting fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 * @author Federica
 */
public final class StartingPaneController extends AbstractStateController {

    @FXML
    private AnchorPane myPane;
    @FXML
    private Button continueButton;

    /**
     * called when the fxml is loaded for the first time
     * load the background
     */
    @Override
    public void setup(){
        Image image = new Image(getClass().getResourceAsStream("toolcard_large.png"));
        BackgroundSize backgroundSize = new BackgroundSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE,
                true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        myPane.setBackground(background);
    }

    /**
     * called every time this window is displayed
     * able or disable continueButton
     */
    @Override
    public void onPreShow(){

        if(getController().getConnectionCount() != 0){
            continueButton.setDisable(false);
        }
        else {
            continueButton.setDisable(true);
        }
    }

    /**
     * called when newGameButton is clicked
     * changes the scene to Socket Rmi Choice Pane
     */
    @FXML
    public void onNewGameButtonClicked(){
        getStateManager().<SocketRMIChoicePaneController>getStateFromName("SocketRMIChoicePane.fxml").show(false);
    }

    /**
     * called when continueButton is clicked
     * changes the scene to Existing Games Pane
     */
    @FXML
    public void onContinueButtonClicked(){
        getStateManager().<ExistingGamesPaneController>getStateFromName("ExistingGamesPane.fxml").show(false);
    }

    /**
     * called when exitGameButton is clicked
     * show an AlertExitBox
     */
    @FXML
    private void onExitGameButtonClicked(){
        AlertExitBoxPaneController.display();
    }

}
