package progetto.clientintegration;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import progetto.gui.AbstractStateController;
import progetto.gui.GamePaneController;
import progetto.network.ClientConnection;
import progetto.network.ServerStateView;
import progetto.utils.Callback;
import progetto.utils.IObserver;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomsPaneController extends AbstractClientStateController{

    private static final Logger LOGGER = Logger.getLogger(RoomsPaneController.class.getName());

    private List<ServerStateView.SimpleRoomState> simpleRoomStateList;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField roomNameTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label errorLabel2;

    @FXML
    private TextField usernameTextField;

    @FXML
    private AnchorPane myPane;

    private IObserver<ServerStateView> serverStateViewIObserver = ogg -> Platform.runLater(this::update);


    @Override
    public void onPreShow(){

        ClientConnection clientConnection = getClientViewStateMachine().getCurrentClientGame().getClientConnection();

        if(clientConnection!=null){

            clientConnection.getServerStateViewCallback().removeObserver(serverStateViewIObserver);

        }

        clientConnection = getClientViewStateMachine().getCurrentClientGame().getClientConnection();

        clientConnection.getServerStateViewCallback().addObserver(serverStateViewIObserver);

    }

    private void update(){

        ClientConnection clientConnection = getClientViewStateMachine().getCurrentClientGame().getClientConnection();

        listView.getItems().clear();

        if(getClientViewStateMachine().getCurrentClientGame()==null){

            return;
        }

        ServerStateView serverStateView = clientConnection.getServerState();

        simpleRoomStateList = serverStateView.asList();

        for (ServerStateView.SimpleRoomState s: simpleRoomStateList) {

            listView.getItems().add(s.roomName + " " + s.playerSize);

        }

    }

    @FXML
    public void onUpdateButtonClicked(){

        ClientConnection clientConnection = getClientViewStateMachine().getCurrentClientGame().getClientConnection();

        clientConnection.fetchServerState();

    }

    @FXML
    public void onCreateButtonClicked(){

        ClientConnection clientConnection = getClientViewStateMachine().getCurrentClientGame().getClientConnection();

        if(roomNameTextField.getText().length()==0){

            errorLabel.setText("Inserire un nome valido per la stanza");
            return;

        }

        clientConnection.createGame(roomNameTextField.getText());

        clientConnection.fetchServerState();

        roomNameTextField.clear();
    }

    @FXML
    public void onEnterButtonClicked(){

        ClientConnection clientConnection = getClientViewStateMachine().getCurrentClientGame().getClientConnection();

        if(usernameTextField.getText().length()==0){

            errorLabel2.setText("Inserire un username valido");
            return;

        }

        if(listView.getSelectionModel().getSelectedItem()==null){

            errorLabel2.setText("Selezionare una stanza");
                return;

            }

        if(simpleRoomStateList.get(listView.getSelectionModel().getSelectedIndex()).roomID == -1){

            errorLabel2.setText("Selezionare una stanza valida");
            return;

        }

        clientConnection.joinGame(simpleRoomStateList.get(listView.getSelectionModel().getSelectedIndex()).roomID, usernameTextField.getText());
        getClientViewStateMachine().getClientCommandProcessor().setGame(getClientViewStateMachine().getCurrentClientGame());
        getClientViewStateMachine().getStateFromName("GamePane.fxml").show();
    }

}
