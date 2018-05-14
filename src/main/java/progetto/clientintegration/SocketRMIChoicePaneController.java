package progetto.clientintegration;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class SocketRMIChoicePaneController {

    @FXML
    private Label errorMessage;

    @FXML
    private Label iPAddress;

    @FXML
    private CheckBox socket;

    @FXML
    private CheckBox rmi;

    @FXML
    private Button connectButton;

    public void onCheckBoxSelected(){

        socket.setSelected(rmi.isSelected());

    }


}
