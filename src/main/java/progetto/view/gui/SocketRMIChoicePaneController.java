package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * this is the class that handles the socket rmi choice fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 * @author Federica
 */
public class SocketRMIChoicePaneController extends AbstractStateController {

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

    /**
     * called when the fxml is loaded for the first time
     * load the background
     */
    @Override
    public void setup() {

        Image image = new Image(getClass().getResourceAsStream("toolcard_large.png"));
        BackgroundSize backgroundSize = new BackgroundSize(
                Control.USE_COMPUTED_SIZE,
                Control.USE_COMPUTED_SIZE,
                true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);

    }

    /**
     * called when socket box is selected
     * unselect rmi box
     */
    public void onSocketBoxSelected(){
        rmi.setSelected(!socket.isSelected());
    }

    /**
     * called when rmi box is selected
     * unselect socket box
     */
    public void onRmiBoxSelected(){
        socket.setSelected(!rmi.isSelected());
    }

    /**
     * called when connectButton is clicked
     * tries to create a new connection and if positive change the scene to Rooms Pane
     */
    @FXML
    public void onConnectButtonClicked() {

        boolean success = getController().createConnection(iPAddress.getText(), rmi.isSelected());

        if (!success)
        {
            errorMessage.setText("Connessione fallita");
            return;
        }

        getStateManager().getStateFromName("RoomsPane.fxml").show(false);

    }


}
