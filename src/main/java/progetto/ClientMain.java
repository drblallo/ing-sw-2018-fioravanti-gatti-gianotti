package progetto;

import javafx.application.Application;
import javafx.stage.Stage;
import progetto.integration.client.ClientController;
import progetto.integration.client.view.GUIView;


public class ClientMain extends Application {

    public static void main(String[] args){

        ServerMain.main(args);
        launch(args);

    }

    @Override
    public synchronized void start(Stage primaryStage)
    {
        primaryStage.setTitle("Client Window");

        primaryStage.setMaximized(true);


        ClientController controller = new ClientController();
        GUIView view = new GUIView(primaryStage);
        controller.addView(view);
        view.setVisible(true);

    }

}
