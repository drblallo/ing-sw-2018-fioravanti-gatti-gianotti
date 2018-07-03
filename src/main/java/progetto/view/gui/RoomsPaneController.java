package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import progetto.network.ServerStateView;
import progetto.utils.IObserver;

import java.util.List;

public class RoomsPaneController extends AbstractStateController {

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
    private AnchorPane anchorPane;
    @FXML
    private TextField usernameTextField;
    private IObserver<ServerStateView> serverStateViewIObserver = ogg -> Platform.runLater(this::update);

    @Override
    public void onPreShow(){
        Image image = new Image(getClass().getResourceAsStream("toolcard_large.png"));
        BackgroundSize backgroundSize = new BackgroundSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE,true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);
        getController().getServerViewCallback().addObserver(serverStateViewIObserver);
        update();
    }

    private void update(){
        listView.getItems().clear();
        ServerStateView serverStateView = getController().getCurrentServerState();
        simpleRoomStateList = serverStateView.asList();
        for (int i = 1; i<simpleRoomStateList.size(); i++) {
            listView.getItems().add(simpleRoomStateList.get(i).roomName
                    + " - Ci sono " + simpleRoomStateList.get(i).playerSize + " partecipanti");
        }
    }

    @FXML
    public void onUpdateButtonClicked(){
        getController().fetchCurrentState();
    }

    @FXML
    public void onCreateButtonClicked(){
        if(roomNameTextField.getText().length()==0){
            errorLabel.setText("Inserire un nome valido per la stanza");
            return;
        }
        getController().createGame(roomNameTextField.getText());
        getController().fetchCurrentState();
        roomNameTextField.clear();
    }

    @FXML
    public void onEnterButtonClicked(){
        if(usernameTextField.getText().length()==0){
            errorLabel2.setText("Inserire un username valido");
            return;
        }
        if(listView.getSelectionModel().getSelectedItem()==null){
            errorLabel2.setText("Selezionare una stanza");
                return;
            }
        int roomID = simpleRoomStateList.get(listView.getSelectionModel().getSelectedIndex()+1).roomID;
        getController().joinGame(roomID, usernameTextField.getText());
        getStateManager().getStateFromName("GamePane.fxml").show(true);
    }

}
