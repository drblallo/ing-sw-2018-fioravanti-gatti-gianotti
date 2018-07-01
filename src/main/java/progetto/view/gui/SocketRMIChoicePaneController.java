package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

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
    private AnchorPane anchorPane;

    private static final int BACKGROUND_SIZE = 300;

    @Override
    public void onPreShow() {

        Image image = new Image(getClass().getResourceAsStream("toolcard_large.png"));
        BackgroundSize backgroundSize = new BackgroundSize(BACKGROUND_SIZE,BACKGROUND_SIZE,true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);

    }

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
            errorMessage.setText("Connessione fallita");
            return;
        }

        getViewStateMachine().getStateFromName("RoomsPane.fxml").show(false);

    }


}
