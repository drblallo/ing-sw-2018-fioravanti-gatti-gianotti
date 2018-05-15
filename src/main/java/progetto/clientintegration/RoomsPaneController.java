package progetto.clientintegration;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import progetto.network.ServerStateView;

public class RoomsPaneController {

    @FXML
    private ListView<String> listView;

    @FXML
    private Button enterButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private RoomsPaneController roomsPaneController;

    public void update(){

        Platform.runLater(()->{

            listView.getItems().clear();

            if(ClientMain.getGame()==null){

                return;

            }

            ServerStateView serverStateView = ClientMain.getGame().getClientConnection().getServerState();

            for(int i = 0; i< serverStateView.getRoomCount(); i++){

                listView.getItems().add(serverStateView.getRoom(i).roomName + Integer.toString(serverStateView.getRoom(i).playerSize));

            }

        });



    }



}
