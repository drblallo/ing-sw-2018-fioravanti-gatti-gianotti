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
import progetto.gui.*;

import java.io.IOException;


public class ClientWindow extends Application {

    private static ClientWindow clientWindow;
    private ClientViewStateMachine clientViewStateMachine;

    public static void launchWindow(String[] args){

            launch(args);

    }

    @Override
    public synchronized void start(Stage primaryStage) throws IOException{

        primaryStage.setTitle("Client Window");

        clientViewStateMachine = new ClientViewStateMachine(primaryStage);
        clientViewStateMachine.setClientCommandProcessor(new ClientCommandProcessor());

        ViewState<StartingPaneController> startingPaneControllerViewState =
                new ViewState<>(clientViewStateMachine, "StartingPane.fxml", StartingPaneController.class);

        startingPaneControllerViewState.show();

        new ClientViewState<SocketRMIChoicePaneController>(clientViewStateMachine, "SocketRMIChoicePane.fxml", SocketRMIChoicePaneController.class);
        new ClientViewState<ExistingGamesPaneController>(clientViewStateMachine, "ExistingGamesPane.fxml", ExistingGamesPaneController.class);
        new ClientViewState<RoomsPaneController>(clientViewStateMachine, "RoomsPane.fxml", RoomsPaneController.class);
        new GameViewState(clientViewStateMachine, "GamePane.fxml", GamePaneController.class);

        /*FXMLLoader loader = new FXMLLoader(SocketRMIChoicePaneController.class.getResource("SocketRMIChoicePane.fxml"));

        Pane pane = (Pane) loader.load();

        SocketRMIChoicePaneController socketRMIChoicePaneController = loader.<SocketRMIChoicePaneController>getController();

        socketRMIChoicePaneController.setUp(primaryStage);

        FXMLLoader fxmlLoader = new FXMLLoader(StartingPaneController.class.getResource("StartingPane.fxml"));

        Pane pane = (Pane) fxmlLoader.load();

        StartingPaneController startingPaneController = fxmlLoader.<StartingPaneController>getController();

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);

        primaryStage.show();*/

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
