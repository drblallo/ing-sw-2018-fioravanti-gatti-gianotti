package progetto.clientintegration;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import progetto.gui.AbstractStateController;

public final class StartingPaneController extends AbstractStateController{

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

        if(!ExistingGames.getExistingGames().getExistingGamesList().isEmpty()){

            continueButton.setDisable(false);

        }

        else {

            continueButton.setDisable(true);

        }

    }

}
