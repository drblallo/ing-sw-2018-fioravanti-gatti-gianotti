package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import progetto.network.PlayerView;
import progetto.network.RoomView;

import java.util.List;

/**
 * this is the class that handles the chat pane fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 */
public class ChatPaneController extends AbstractStateController {

    private List<PlayerView> playerViewList;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextArea chatArea;
    @FXML
    private TextField messageToSend;

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     */
    @Override
    public void setup()
    {
        getController().getMessageCallback().addObserver(ogg -> Platform.runLater(()->chatArea.appendText(ogg)));
        getController().getRoomViewCallback().addObserver(ogg -> Platform.runLater(this::update));
    }

    /**
     * called every time this window is displayed
     * reset the chat area
     */
    @Override
    public void onPreShow()
    {
        chatArea.clear();
        update();
    }

    /**
     * displayes all the players in the room.
     */
    private void update(){
        RoomView roomView;

        roomView = getController().getCurrentRoom();
        playerViewList = roomView.asList();
        listView.getItems().clear();

        for (PlayerView p : playerViewList) {
            if (p.getChairID()!=-1){
                listView.getItems().add("Giocatore " + p.getChairID() + ": " + p.getName());
            }
            else listView.getItems().add(p.getName());
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
                getController().sendPrivateMessage(message + "\n", p.getId());
            }
        }
    }


    @FXML
    private void onReturnButtonClicked(){
        getStateManager().getStateFromName("GamePane.fxml").show(true);
    }
}
