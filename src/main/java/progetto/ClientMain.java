package progetto;

import javafx.application.Application;
import javafx.stage.Stage;
import progetto.integration.client.ClientController;
import progetto.integration.client.view.GUIView;
import progetto.integration.client.view.cl.CommandLineView;
import progetto.view.commandline.StreamProcessor;

import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class ClientMain extends Application {

    public static void main(String[] args){

        ServerMain.main(args);
        launch(args);

    }

    @Override
    public synchronized void start(Stage primaryStage)
    {
        primaryStage.setTitle("Client Window");
        primaryStage.centerOnScreen();


        ClientController controller = new ClientController();
        GUIView view = new GUIView(primaryStage, controller);
        CommandLineView cl = new CommandLineView(controller, System.out);
        view.setVisible(true);
        cl.setVisible(true);
        new Thread(cl).start();
        StreamProcessor streamProcessor =
        new StreamProcessor(new InputStreamReader(System.in, Charset.defaultCharset()), null, cl);

        new Thread(cl).start();
        new Thread(streamProcessor).start();
    }

}
