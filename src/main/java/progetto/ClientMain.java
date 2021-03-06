package progetto;

import javafx.application.Application;
import javafx.stage.Stage;
import progetto.view.gui.AlertExitBoxPaneController;
import progetto.view.gui.GUIView;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientMain extends Application {

    public static void main(String[] args){
        Logger.getLogger(ClientMain.class.getPackage().getName()).setLevel(Level.OFF);
        launch(args);

    }

    @Override
    public synchronized void start(Stage primaryStage)
    {

        Settings.getSettings();
        primaryStage.setTitle("Sagrada");
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            AlertExitBoxPaneController.display();
        });

        ClientController controller = new ClientController();
        GUIView view = new GUIView(primaryStage, controller);
        AlertExitBoxPaneController.setup(view);
        view.setVisible(true);

    }

}
