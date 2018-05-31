package progetto.integration.client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import progetto.ServerMain;
import progetto.integration.client.ClientGame;
import progetto.network.INetworkClient;
import progetto.network.rmi.RMIClient;
import progetto.network.socket.SocketClient;

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

        ClientGame clientGame = createClientConnection();

        if (clientGame == null)
        {
            errorMessage.setText("Failed to connect");
            return;
        }

        getController().setCurrentClientGame(clientGame);
        getController().getExistingGames().addClientGame(clientGame);

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

        if(!c.isRunning()){

            return null;

        }

        ClientGame clientConnection = new ClientGame(c);


        clientConnection.getClientConnection().fetchServerState();


        return clientConnection;

    }


}
