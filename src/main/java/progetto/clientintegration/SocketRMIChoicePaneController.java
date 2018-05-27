package progetto.clientintegration;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import progetto.gui.AbstractStateController;
import progetto.network.ClientConnection;
import progetto.network.INetworkClient;
import progetto.network.NetworkServer;
import progetto.network.ServerStateView;
import progetto.network.rmi.RMIClient;
import progetto.network.socket.SocketClient;
import progetto.serverintegration.ServerMain;
import progetto.utils.IObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketRMIChoicePaneController extends AbstractClientStateController{

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

        ClientGame clientGame = createClientConnection();

        if (clientGame == null)
        {
            errorMessage.setText("Failed to connect");
            return;
        }

        getClientViewStateMachine().setCurrentClientGame(clientGame);
        ExistingGames.getExistingGames().addClientGame(clientGame);
        getClientViewStateMachine().setCurrentClientGame(clientGame);

        getViewStateMachine().getStateFromName("RoomsPane.fxml").show();


    }

    private ClientGame createClientConnection(){

        INetworkClient c;

        if(rmi.isSelected()) {

            c = new RMIClient(iPAddress.getText());
        }

        else {

            c = new SocketClient(iPAddress.getText(), ServerMain.DEFAULT_PORT);
        }

        ClientGame clientConnection = new ClientGame(c);


        clientConnection.getClientConnection().fetchServerState();

        if (!c.isRunning())
            return null;

        return clientConnection;

    }


}
