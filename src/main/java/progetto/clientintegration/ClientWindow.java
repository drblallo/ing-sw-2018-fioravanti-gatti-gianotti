package progetto.clientintegration;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import progetto.commandline.CommandProcessor;
import progetto.game.Game;
import progetto.game.PlayerBoard;
import progetto.gui.CommandLinePaneController;
import progetto.gui.GamePaneController;
import progetto.gui.PlayerBoardPaneController;

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

        FXMLLoader loader = new FXMLLoader(GamePaneController.class.getResource("GamePane.fxml"));

        Pane pane = (Pane) loader.load();

        GamePaneController gamePaneController = loader.<GamePaneController>getController();

        gamePaneController.setUp(ClientMain.getGame(), commandProcessor);

        /*FXMLLoader loader = new FXMLLoader(SocketRMIChoicePaneController.class.getResource("SocketRMIChoicePane.fxml"));

        Pane pane = (Pane) loader.load(); */

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
