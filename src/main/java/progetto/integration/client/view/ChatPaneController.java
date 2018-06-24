package progetto.integration.client.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import progetto.integration.client.IClientController;
import progetto.network.PlayerView;
import progetto.network.RoomView;
import progetto.utils.IObserver;

import java.util.List;

/**
 * this is the class that handles the chat pane fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 */
public class ChatPaneController {

    private IClientController clientGame;

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

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     * @param controller the controller that will be observed
     */
    public void setUp(IClientController controller)
    {
    	clientGame = controller;
        controller.getMessageCallback().addObserver(stringIObserver);
        controller.getRoomViewCallback().addObserver(roomViewIObserver);
    }

    /**
     * called every time this window is displayed
     * reset the chat area
     */
    public void onPreShow()
    {
        chatArea.clear();
        update();
    }

    /**
     * displayes all the players in the room.
     */
    private void update(){

        int playerCount;
        RoomView roomView;

        roomView = clientGame.getCurrentRoom();

        playerViewList = roomView.asList();

        listView.getItems().clear();

        for (PlayerView p : playerViewList) {

            listView.getItems().add(p.getChairID() + " " + p.getName());

        }

        playerCount = clientGame.getModel().getMainBoard().getData().getPlayerCount();


        chairs.getItems().clear();
        chairs.getItems().add(-1);

        for(int i=0; i<playerCount; i++){

            if(roomView.getPlayerOfChair(i)==null){

                chairs.getItems().add(i);

            }

        }

    }

    /**
     * called when enter is pressed
     */
    @FXML
    private void onEnterKeyPressed(KeyEvent keyEvent){

        if(keyEvent.getCode() == KeyCode.ENTER){

            String message = messageToSend.getText();

            messageToSend.clear();

            for (PlayerView p : playerViewList) {

                clientGame.sendPrivateMessage(message + "\n", p.getId());

            }

        }

    }

    /**
     * when the player clicks on the button it tries to take a chair
     */
    @FXML
    private void onSitButtonPressed(){

        if (chairs.getSelectionModel().getSelectedItem()!=null){

            clientGame.pickChair(chairs.getSelectionModel().selectedItemProperty().get());
            update();

        }

    }


}
