package progetto.integration.client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SocketRMIChoicePaneController extends AbstractClientStateController{

    @FXML
    private Label errorMessage;

    @FXML
    private TextField iPAddress;

    @FXML
    private CheckBox socket;

    @FXML
    private CheckBox rmi;

    @FXML
    private Button connectButton;

    @FXML
    private AnchorPane myPane;

    public void onSocketBoxSelected(){

        rmi.setSelected(!socket.isSelected());

    }

    public void onRmiBoxSelected(){

        socket.setSelected(!rmi.isSelected());

    }

    @FXML
    public void onConnectButtonClicked() {

        boolean success = getController().createConnection(iPAddress.getText(), rmi.isSelected());

        if (!success)
        {
            errorMessage.setText("Failed to connect");
            return;
        }

        getViewStateMachine().getStateFromName("RoomsPane.fxml").show();

    }


}
