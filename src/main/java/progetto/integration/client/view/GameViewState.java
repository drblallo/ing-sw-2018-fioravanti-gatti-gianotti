package progetto.integration.client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import progetto.view.gui.GamePaneController;
import progetto.view.gui.ViewState;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameViewState extends ViewState <GamePaneController> {

    private ChatPaneController chatPaneController;
    private static final Logger LOGGER = Logger.getLogger(GameViewState.class.getName());
    private GUIView guiView;

    public GameViewState(GUIView view, String fxml, Class c) {

        super(view.getViewStateMachine(), fxml, c);
        this.guiView = view;
        LOGGER.log(Level.FINE, "loading fxml file {0}", fxml);

        Pane pane;

        FXMLLoader fxmlLoader = new FXMLLoader(RoomsPaneController.class.getResource("ChatPane.fxml"));

        try{

            pane = fxmlLoader.load();

        }catch (IOException e){ pane = null; }

        chatPaneController = fxmlLoader.getController();
        getController().addChatPane(pane);
        chatPaneController.setUp(view.getController());

    }

    @Override
    public void show(){
        super.show();
        getController().onPreShow(guiView.getCommandProcessor());
        chatPaneController.onPreShow();
    }
}
