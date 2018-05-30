package progetto.clientintegration;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import progetto.gui.GamePaneController;
import progetto.gui.ViewState;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameViewState extends ViewState <GamePaneController> {

    private ChatPaneController chatPaneController;
    private ClientViewStateMachine clientViewStateMachine;
    private static final Logger LOGGER = Logger.getLogger(GameViewState.class.getName());

    public GameViewState(ClientViewStateMachine clientViewStateMachine, String fxml, Class c) {

        super(clientViewStateMachine, fxml, c);
        this.clientViewStateMachine = clientViewStateMachine;

        Pane pane;

        FXMLLoader fxmlLoader = new FXMLLoader(RoomsPaneController.class.getResource("ChatPane.fxml"));

        try{

            pane = (Pane) fxmlLoader.load();

        }catch (IOException e){

            pane = null;
            LOGGER.log(Level.SEVERE, e.getMessage());
        }

        chatPaneController = fxmlLoader.<ChatPaneController>getController();
        getController().addChatPane(pane);

    }

    @Override
    public void show(){

        super.show();
        getController().onPreShow(clientViewStateMachine.getClientCommandProcessor());
        chatPaneController.onPreShow(clientViewStateMachine.getCurrentClientGame());
    }

}
