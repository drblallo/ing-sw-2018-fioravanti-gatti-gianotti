package progetto.clientintegration;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import progetto.gui.CommandLinePane;


public class ClientWindow extends Application {

    private static ClientWindow clientWindow;

    public static void launchWindow(String[] args){

            launch(args);

    }

    @Override
    public synchronized void start(Stage primaryStage) {

        primaryStage.setTitle("Client Window");

        CommandLinePane commandLinePane = new CommandLinePane(ClientCommandProcessor.getCommandProcessor());

        Scene scene = new Scene(commandLinePane.getLayout());
        primaryStage.setScene(scene);
        primaryStage.show();

        clientWindow = this;

    }

     static synchronized ClientWindow getWindow(){

        return clientWindow;
    }

    public synchronized void closeWindow(){

            clientWindow = null;
            Platform.exit();

    }
}
