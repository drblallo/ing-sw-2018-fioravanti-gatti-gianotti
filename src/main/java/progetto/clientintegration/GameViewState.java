package progetto.clientintegration;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import progetto.gui.GamePaneController;
import progetto.gui.ViewState;

import java.io.IOException;

public class GameViewState extends ViewState <GamePaneController> {

    private Pane pane;
    private ChatPaneController chatPaneController;
    private ClientViewStateMachine clientViewStateMachine;

    public GameViewState(ClientViewStateMachine clientViewStateMachine, String fxml, Class c) {

        super(clientViewStateMachine, fxml, c);
        this.clientViewStateMachine = clientViewStateMachine;

        Pane pane;

        FXMLLoader fxmlLoader = new FXMLLoader(RoomsPaneController.class.getResource("ChatPane.fxml"));

        try{

            pane = (Pane) fxmlLoader.load();

        }catch (IOException e){

            pane = null;
            System.out.println("IOEXception in GamePaneController"); //METTI LOGGER
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
