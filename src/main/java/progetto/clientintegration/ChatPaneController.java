package progetto.clientintegration;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import progetto.gui.AbstractStateController;
import progetto.network.PlayerView;
import progetto.network.RoomView;
import progetto.utils.IObserver;

import java.util.List;

public class ChatPaneController {

    private ClientGame clientGame;

    private List<PlayerView> playerViewList;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField messageToSend;

    @FXML
    private ChoiceBox<Integer> chairs;

    private IObserver<RoomView> roomViewIObserver = ogg -> Platform.runLater(this::update);

    private IObserver<String> stringIObserver = ogg -> Platform.runLater(()-> chatArea.appendText(ogg));

    public void onPreShow(ClientGame clientGame){

        if(clientGame!=null){

            clientGame.getClientConnection().getRoomViewCallback().removeObserver(roomViewIObserver);
            clientGame.getClientConnection().getMessageCallback().removeObserver(stringIObserver);

        }

        this.clientGame = clientGame;

        clientGame.getClientConnection().getRoomViewCallback().addObserver(roomViewIObserver);

        clientGame.getClientConnection().getMessageCallback().addObserver(stringIObserver);

        chatArea.clear();

        update();

        }

    private void update(){

        int playerCount;
        RoomView roomView;

        roomView = clientGame.getClientConnection().getRoom();

        playerViewList = roomView.asList();

        listView.getItems().clear();

        for (PlayerView p : playerViewList) {

            listView.getItems().add(p.getChairID() + " " + p.getName());

        }

        playerCount = clientGame.getMainBoard().getMainBoardData().getPlayerCount();


        chairs.getItems().clear();
        chairs.getItems().add(-1);

        for(int i=0; i<playerCount; i++){

            if(roomView.getPlayerOfChair(i)==null){

                chairs.getItems().add(i);

            }

        }

    }

    @FXML
    private void onEnterKeyPressed(KeyEvent keyEvent){

        if(keyEvent.getCode() == KeyCode.ENTER){

            String message = messageToSend.getText();

            messageToSend.clear();

            for (PlayerView p : playerViewList) {

                clientGame.getClientConnection().sendPrivateMessage(message + "\n", p.getId());

            }

        }

    }

    @FXML
    private void onSitButtonPressed(){

        if (chairs.getSelectionModel().selectedItemProperty()!=null){

            clientGame.getClientConnection().pickChair(chairs.getSelectionModel().selectedItemProperty().get());
            update();

        }

    }


}
