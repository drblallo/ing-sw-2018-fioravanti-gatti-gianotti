package progetto;

import javafx.application.Application;
import javafx.stage.Stage;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.StreamProcessor;
import progetto.view.gui.AlertExitBoxPaneController;
import progetto.view.gui.GUIView;

import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class ClientMain extends Application {

    public static void main(String[] args){

       // Logger.getLogger(RMIClient.class.getPackage().getName()).getParent().getHandlers()[0].setLevel(Level.ALL);
        //Logger.getLogger(RMIClient.class.getPackage().getName()).setLevel(Level.ALL);
        //Logger.getLogger(RMIClient.class.getPackage().getName()).getParent().setLevel(Level.ALL);
        ServerMain.main(args);
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
        CommandLineView cl = new CommandLineView(controller, System.out);
        view.setVisible(true);
        cl.setVisible(true);
        new Thread(cl).start();
        StreamProcessor streamProcessor =
        new StreamProcessor(new InputStreamReader(System.in, Charset.defaultCharset()), null, cl);

        new Thread(streamProcessor).start();
    }

}
