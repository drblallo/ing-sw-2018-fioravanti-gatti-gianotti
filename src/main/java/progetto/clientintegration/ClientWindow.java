package progetto.clientintegration;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import progetto.commandline.CommandProcessor;
import progetto.gui.CommandLinePaneController;

import java.io.IOException;


public class ClientWindow extends Application {

    private static ClientWindow clientWindow;

    public static void launchWindow(String[] args){

            launch(args);

    }

    @Override
    public synchronized void start(Stage primaryStage) throws IOException{

        primaryStage.setTitle("Client Window");

        CommandProcessor commandProcessor = ClientCommandProcessor.getCommandProcessor();

        FXMLLoader loader = new FXMLLoader(CommandLinePaneController.class.getResource("CommandLinePane.fxml"));

        Pane pane = (Pane) loader.load();

        CommandLinePaneController commandLinePaneController = loader.<CommandLinePaneController>getController();

        commandLinePaneController.setCommandProcessor(commandProcessor);

        Scene scene = new Scene(pane);

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
