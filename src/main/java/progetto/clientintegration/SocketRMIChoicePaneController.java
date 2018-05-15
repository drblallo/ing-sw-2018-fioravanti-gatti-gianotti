package progetto.clientintegration;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import progetto.network.ClientConnection;
import progetto.network.INetworkClient;
import progetto.network.NetworkServer;
import progetto.network.rmi.RMIClient;
import progetto.network.socket.SocketClient;
import progetto.serverintegration.ServerMain;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketRMIChoicePaneController {

    private static final Logger LOGGER = Logger.getLogger(SocketRMIChoicePaneController.class.getName());

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

    private Stage stage;

    public void setUp(Stage primaryStage){

        stage = primaryStage;

    }

    public void onSocketBoxSelected(){

        rmi.setSelected(!socket.isSelected());

    }

    public void onRmiBoxSelected(){

        socket.setSelected(!rmi.isSelected());

    }

    public void onConnectButtonClicked(){

        INetworkClient c;

        Pane pane;

        if(rmi.isSelected()) {

            c = new RMIClient(iPAddress.getText());
        }

        else {

            c = new SocketClient(iPAddress.getText(), ServerMain.DEFAULT_PORT);
        }

        if(c.isRunning()){

            ClientGame clientGame = new ClientGame(c);

            ClientMain.setClientGame(clientGame);

            FXMLLoader loader = new FXMLLoader(RoomsPaneController.class.getResource("RoomsPane.fxml"));
            try{

                pane = (Pane) loader.load();

            } catch (IOException e){

                LOGGER.log(Level.SEVERE, "IOException {0}", e.getMessage());
                pane = null;

            }

            RoomsPaneController roomsPaneController = loader.<RoomsPaneController>getController();

            roomsPaneController.update();

            stage.setScene(new Scene(pane));

            return;

        }

        errorMessage.setText("Failed to connect");

    }


}
