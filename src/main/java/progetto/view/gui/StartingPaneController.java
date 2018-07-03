package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public final class StartingPaneController extends AbstractStateController {

    @FXML
    private AnchorPane myPane;
    @FXML
    private Button continueButton;

    @FXML
    public void onNewGameButtonClicked(){
        getStateManager().<SocketRMIChoicePaneController>getStateFromName("SocketRMIChoicePane.fxml").show(false);
    }
    @FXML
    public void onContinueButtonClicked(){
        getStateManager().<ExistingGamesPaneController>getStateFromName("ExistingGamesPane.fxml").show(false);
    }

    @FXML
    private void onExitGameButtonClicked(){
        AlertExitBoxPaneController.display();
    }

    @Override
    public void onPreShow(){

        Image image = new Image(getClass().getResourceAsStream("toolcard_large.png"));
        BackgroundSize backgroundSize = new BackgroundSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE,
                true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        myPane.setBackground(background);

        if(getController().getConnectionCount() != 0){
            continueButton.setDisable(false);
        }
        else {
            continueButton.setDisable(true);
        }
    }

}
