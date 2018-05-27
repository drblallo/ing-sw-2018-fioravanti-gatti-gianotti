package progetto.clientintegration;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import progetto.gui.AbstractStateController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class StartingPaneController extends AbstractStateController{

    private static final Logger LOGGER = Logger.getLogger(StartingPaneController.class.getName());

    @FXML
    private AnchorPane myPane;

    @FXML
    private Button continueButton;

    @FXML
    public void onNewGameButtonClicked(){

        getViewStateMachine().<SocketRMIChoicePaneController>getStateFromName("SocketRMIChoicePane.fxml").show();
    }

    @FXML
    public void onContinueButtonClicked(){

        getViewStateMachine().<ExistingGamesPaneController>getStateFromName("ExistingGamesPane.fxml").show();

    }

    @Override
    public void onPreShow(){

        if(ExistingGames.getExistingGames().getExistingGamesList().size()!=0){

            continueButton.setDisable(false);

        }

        else {

            continueButton.setDisable(true);

        }

    }

}
