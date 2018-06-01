package progetto.integration.client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public final class StartingPaneController extends AbstractClientStateController{

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

        if(getController().getConnectionCount() != 0){

            continueButton.setDisable(false);

        }

        else {

            continueButton.setDisable(true);

        }

    }

}
